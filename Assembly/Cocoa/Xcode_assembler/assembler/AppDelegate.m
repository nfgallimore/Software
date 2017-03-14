//
//  AppDelegate.m
//  assembler
//
//  Created by Nick Gallimore on 12/9/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    @autoreleasepool
    {
        uint8_t i;
        for( i = 0; i < 16; i++ ) printf("i = %d\n", i);
    }
}

@end
