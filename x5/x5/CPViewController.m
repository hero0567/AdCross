//
//  CPViewController.m
//  x5
//
//  Created by Shev Yan on 10/11/14.
//  Copyright (c) 2014 com.shev.x5. All rights reserved.
//

#import "CPViewController.h"
#import "ZipArchive.h"

enum ShowType {
    None = 0,
    FromTop,
    FromBottom,
    FromLeft,
    FromRight
};

@interface CPViewController ()
@property (nonatomic, strong) UIWebView *webView;
@property (nonatomic, copy) NSString *appID;
@property NSURL *webUrl;
@property CGRect webViewFrame;
@property int showType;
@property float transparency;
@end

#define REQ_URI @"http://www.eyeshang.com/tmp.json"

@implementation CPViewController

+ (instancetype) sharedInstance {
    static CPViewController *instance = nil;
    
    if (nil == instance) {
        instance = [[self alloc] init];
    }
    
    return instance;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}


//The event handling method
- (void)handleSingleTap:(UITapGestureRecognizer *)recognizer {
    CGPoint location = [recognizer locationInView:[recognizer.view superview]];
    
    //Do stuff here...
    [self cpHide];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.webView = [[UIWebView alloc] init];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


///////////
- (void) cpInit:(NSString *)appID delegate:(id<CPProtocol>)delegate{
    self.appID = appID;
    self.delegate = delegate;

    [self cpFetchAsync];
}

- (void) cpUninit {
    self.appID = nil;
}

- (void) cpFetchAsync {
    self.webUrl = nil;
    // need to change request uri
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        // get json
        NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:REQ_URI]];
        NSData *response = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:nil];
        NSError *jsonParsingError = nil;
        NSDictionary *json = [NSJSONSerialization JSONObjectWithData:response options:0 error:&jsonParsingError];
        if (nil != jsonParsingError) {
            [self.delegate cpFailedFetch:@"Failed to parse json."];
            return ;
        }
        
        // get download link
        NSDictionary *fetchResp = [json objectForKey:@"fetchResp"];
        NSString *downloadLink = [fetchResp objectForKey:@"DownloadLink"];
        
        // download zip
        NSString *tempPath = NSTemporaryDirectory();
        NSData *zipData = [NSData dataWithContentsOfURL:[NSURL URLWithString:downloadLink]];
        NSString *zipFile = [tempPath stringByAppendingPathComponent: @"CpSpace.zip"];
        [zipData writeToFile:zipFile atomically:TRUE];

        // decompress zip
        ZipArchive *za = [[ZipArchive alloc] init];
        [za UnzipOpenFile:zipFile];
        NSString *zipPath = [tempPath stringByAppendingPathComponent: @"CpSpace"];
        [za UnzipFileTo:tempPath overWrite:TRUE];
        [za UnzipCloseFile];
        za = nil;
        
        // show
        NSURL *url = [NSURL fileURLWithPath:[zipPath stringByAppendingPathComponent:@"cpSpace.html"]];
        
        if (url != nil) {
            dispatch_async(dispatch_get_main_queue(), ^{
                if (nil != self.delegate) {
                    self.webUrl = url;
                    [self parseShowOptions:[fetchResp objectForKey:@"CpSpace"]];
                    [self.delegate cpDidFecth];
                }
            });
        }
    });
}

- (void) parseShowOptions:(NSDictionary *)cpSpace{
    self.showType = [[cpSpace objectForKey:@"showType"] integerValue];
    self.transparency = [[cpSpace objectForKey:@"transparency"] integerValue] / 100.0f;
    NSArray *strArr = [(NSString *)[cpSpace objectForKey:@"position"] componentsSeparatedByString:@", "];
    CGFloat x = [[strArr objectAtIndex:0] floatValue]/100.0f;
    CGFloat y = [[strArr objectAtIndex:1] floatValue]/100.0f;
    CGFloat w = [[strArr objectAtIndex:2] floatValue]/100.0f;
    CGFloat h = [[strArr objectAtIndex:3] floatValue]/100.0f;
    self.webViewFrame = CGRectMake(x, y, w, h);
}

- (void) showBackground:(UIViewController *)parentCtrl {
    [parentCtrl addChildViewController:self];
    [parentCtrl.view addSubview:self.view];
    self.view.alpha = self.transparency;
}

- (void) showWebView:(UIViewController *)parentCtrl{
    [parentCtrl.view addSubview:self.webView];
    NSURLRequest *req = [NSURLRequest requestWithURL:self.webUrl];
    [self.webView loadRequest:req];
    CGRect srcFrame;
    CGRect desFrame = CGRectMake(
                                 self.webViewFrame.origin.x * parentCtrl.view.frame.size.width,
                                 self.webViewFrame.origin.y * parentCtrl.view.frame.size.height,
                                 self.webViewFrame.size.width * parentCtrl.view.frame.size.width,
                                 self.webViewFrame.size.height * parentCtrl.view.frame.size.height);
    switch (self.showType) {
        case None:
            self.webView.frame = desFrame;
            break;
        case FromTop:
        {
            srcFrame = CGRectMake(desFrame.origin.x, 0-desFrame.origin.y, desFrame.size.width, desFrame.size.height);
        }
            break;
        case FromBottom:
        {
            srcFrame = CGRectMake(desFrame.origin.x, parentCtrl.view.frame.size.height + desFrame.origin.y, desFrame.size.width, desFrame.size.height);
        }
            break;
        case FromLeft:
        {
            srcFrame = CGRectMake(0-desFrame.origin.x, desFrame.origin.y, desFrame.size.width, desFrame.size.height);
        }
            break;
        case FromRight:
        {
            srcFrame = CGRectMake(parentCtrl.view.frame.size.width + desFrame.origin.x, desFrame.origin.y, desFrame.size.width, desFrame.size.height);
        }
            break;
            
        default:
            break;
    }
    
    // animation
    self.webView.frame = srcFrame;
    [UIView animateWithDuration:1.0 animations:^{
        self.webView.frame = desFrame;
    }];

}

- (BOOL) cpIsReady {
    return nil != self.webUrl;
}

- (void) cpShow:(UIViewController *)parentCtrl
{
    if (![self cpIsReady]) {
        NSLog(@"CP is not ready! Return directly.");
        return ;
    }
    [self showBackground:parentCtrl];
    [self showWebView:parentCtrl];
    
    UITapGestureRecognizer *singleFingerTap =
    [[UITapGestureRecognizer alloc] initWithTarget:self
                                            action:@selector(handleSingleTap:)];
    //singleFingerTap.delegate = nil;
    [self.view addGestureRecognizer:singleFingerTap];
//    [UIView animateWithDuration:10.0 animations:^{
//        webView.alpha = 0.0;
//        webView.alpha = 1.0;
//    }];
//
//    [UIView animateWithDuration:1.0 animations:^{
//        webView.frame = CGRectMake(50-20, 50, parent.frame.size.width-100, parent.frame.size.height-100);
//    } completion:^(BOOL finished) {
//        [UIView animateWithDuration:0.2 animations:^{
//            webView.frame = CGRectMake(50+15, 50, parent.frame.size.width-100, parent.frame.size.height-100);
//        } completion:^(BOOL finished) {
//            [UIView animateWithDuration:0.2 animations:^{
//                webView.frame = CGRectMake(50-10, 50, parent.frame.size.width-100, parent.frame.size.height-100);} completion:^(BOOL finished) {
//                    [UIView animateWithDuration:0.2 animations:^{
//                        webView.frame = CGRectMake(50+5, 50, parent.frame.size.width-100, parent.frame.size.height-100);
//                    } completion:^(BOOL finished) {
//                        [UIView animateWithDuration:0.2 animations:^{
//                            webView.frame = CGRectMake(50, 50, parent.frame.size.width-100, parent.frame.size.height-100);
//                        }];
//                    }];
//                }];
//        }];
//    }];
}

- (void) cpHide
{
    [self.webView removeFromSuperview];
    [self.view removeFromSuperview];
    [self removeFromParentViewController];
}

- (IBAction)onHideBtn:(id)sender {
    [self cpHide];
}

@end
