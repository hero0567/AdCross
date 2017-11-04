//
//  ShevTabViewController.m
//  x5
//
//  Created by Shev Yan on 9/11/14.
//  Copyright (c) 2014 com.shev.x5. All rights reserved.
//

#import "ShevTabViewController.h"
#import "MyView.h"
#import "ShevAppDelegate.h"

@implementation ShevTabViewController

- (void) loadView
{
    CGRect frame = [UIScreen mainScreen].bounds;
    MyView *myView = [[MyView alloc] initWithFrame:frame];
    myView.alpha = 0.2;
    
    CGRect frame2 = CGRectMake(100, 100, 50, 50);
    ShevAppDelegate *delegate = (ShevAppDelegate *)[UIApplication sharedApplication].delegate;
    MyView *subView = [[MyView alloc] initWithFrame:frame2];
    subView.backgroundColor = [UIColor blueColor];
    [delegate.window addSubview:myView];
    [delegate.window addSubview:subView];
    //self.view = myView;
    
}

- (instancetype) initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil
                           bundle:nibBundleOrNil];
    if (self) {
        // Set the tab bar item's title
        self.tabBarItem.title = @"Hypnotize";
        
        // Create a UIImage from a file
        // This will use Hypno@2x.png on retina display devices
        //UIImage *image = [UIImage imageNamed:@"Hypno.png"];
        
        // Put that image on the tab bar item
        //self.tabBarItem.image = image;
    }
    
    return self;
}
@end
