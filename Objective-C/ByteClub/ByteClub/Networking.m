//
//  Networking.m
//  ByteClub
//
//  Created by Nick Gallimore on 12/10/13.
//  Copyright (c) 2013 Razeware. All rights reserved.
//

#import "Networking.h"

@implementation Networking

NSURLSessionConfiguration *sessionConfig =
NSURLSessionConfiguration defaultSessionConfiguration];

// 1
sessionConfig.allowsCellularAccess = NO;

// 2
sessionConfig setHTTPAdditionalHeaders
 @{@"Accept": @"application/json"}];

// 3
sessionConfig.timeoutIntervalForRequest = 30.0;
sessionConfig.timeoutIntervalForResource = 60.0;
sessionConfig.HTTPMaximumConnectionsPerHost = 1;

@end

