//
//  CPViewController.h
//  x5
//
//  Created by Shev Yan on 10/11/14.
//  Copyright (c) 2014 com.shev.x5. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CPProtocol.h"

@interface CPViewController : UIViewController
+ (instancetype) sharedInstance;
@property (nonatomic, assign) id<CPProtocol> delegate;

- (void) cpInit:(NSString *)appToken delegate:(id<CPProtocol>)delegate;
- (void) cpUninit;
- (void) cpFetchAsync;
- (BOOL) cpIsReady;
- (void) cpShow:(UIViewController *)parentCtrl;
- (void) cpHide;

@end
