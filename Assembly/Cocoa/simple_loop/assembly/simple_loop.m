//
//  simple_loop.m
//  assembly
//
//  Created by Nick Gallimore on 12/9/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//

#import "simple_loop.h"

@implementation simple_loop

int main(int argc, const char * argv[])
{
    @autoreleasepool
    {
        uint8_t i;
        for( i = 0; i < 16; i++ ) printf("i = %d\n", i);
    }
    return 0;
}

@end
