//
//  CPProtocol.h
//  x5
//
//  Created by chenwen on 11/12/14.
//  Copyright (c) 2014 com.shev.x5. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol CPProtocol <NSObject>

- (void) cpFailedFetch:(NSString *)errorMsg;
- (void) cpDidFecth;

@end
