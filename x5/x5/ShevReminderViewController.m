//
//  ShevReminderViewController.m
//  x5
//
//  Created by Shev Yan on 9/11/14.
//  Copyright (c) 2014 com.shev.x5. All rights reserved.
//

#import "ShevReminderViewController.h"
#import "CPViewController.h"

@interface ShevReminderViewController () <CPProtocol>
@property (nonatomic, weak) IBOutlet UIDatePicker *datePicker;
@property (nonatomic, weak) IBOutlet UIWebView *webView;
@end

@implementation ShevReminderViewController

- (IBAction)addReminder:(id)sender
{
    NSDate *date = self.datePicker.date;
    NSLog(@"setting a reminder for %@", date);
    
    UILocalNotification *note = [[UILocalNotification alloc] init];
    note.alertBody = @"Hypnotize me!";
    note.fireDate = date;
    
    [[UIApplication sharedApplication] scheduleLocalNotification:note];
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.tabBarController.title = @"Reminder";
        [[CPViewController sharedInstance] cpInit:@"appID" delegate:self];
    }
    
    return self;
}


- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.

}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (void) cpFailedFetch:(NSString *)errorMsg {
    
}

- (void) cpDidFecth {
    [[CPViewController sharedInstance] cpShow:self];
}

- (IBAction)fetchCP:(id)sender
{
//    CPViewController *cpvc = [[CPViewController alloc] init];
////    [self addChildViewController:cpvc];
////    [self.view addSubview:cpvc.view];
//    [cpvc cpShow:self];
    [[CPViewController sharedInstance] cpFetchAsync];
}
@end
