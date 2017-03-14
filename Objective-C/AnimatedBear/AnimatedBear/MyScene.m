//
//  MyScene.m
//  AnimatedBear
//
//  Created by Nick Gallimore on 12/10/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//


#import <AVFoundation/AVFoundation.h>
#import "MyScene.h"



@implementation MyScene
{
    
    
    
    
    SKSpriteNode *_bear;
    NSArray *_bearWalkingFrames;
    
    

    
}




/******************************************************************************************/




-(id)initWithSize:(CGSize)size
{
    if (self = [super initWithSize:size])
    {
        
        
        
        /* Setup your scene here */
        self.backgroundColor = [SKColor blackColor];
        
        
        
        
        /* Setup the array to hold the walking frames */
        NSMutableArray *walkFrames = [NSMutableArray array];
        
        
        
        
        /* Load and setup the texture atlas */
        SKTextureAtlas *bearAnimatedAtlas = [SKTextureAtlas atlasNamed: @"BearImages"];
        
        
        
        
        
        /* Gather the list of frames */
        int numImages = bearAnimatedAtlas.textureNames.count;
        for (int i = 1; i <= numImages / 2; i++)
        {
            NSString *textureName = [NSString stringWithFormat:@"bear%d~ipad", i];
            SKTexture *temp = [bearAnimatedAtlas textureNamed:textureName];
            [walkFrames addObject:temp];
        }
        _bearWalkingFrames = walkFrames;
        
        
        
        
        
        /* Create the sprite, set its position to the middle of the screen, and add it to the scene */
        SKTexture *temp = _bearWalkingFrames[0];
        _bear = [SKSpriteNode spriteNodeWithTexture:temp];
        _bear.position = CGPointMake(CGRectGetMidX(self.frame),
                                     CGRectGetMidY(self.frame));
        [self addChild:_bear];
        
        
        

    }
    
    
    return self;
    
    
    
}




/******************************************************************************************/




-(void)walkingBear
{
    
    
    
    
    //This is the general runAction method to make the bear walk.
    [_bear runAction:[SKAction repeatActionForever:
                      [SKAction animateWithTextures:_bearWalkingFrames
                                       timePerFrame:0.1f
                                             resize:NO
                                            restore:YES]]
             withKey:@"walkingInPlaceBear"];
    return;
    
    
    
}




/******************************************************************************************/




-(void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
{
    
    
    
    
    CGPoint location = [[touches anyObject] locationInNode:self];
    CGFloat multiplierForDirection;
    
    
    
    
    if (location.x <= CGRectGetMidX(self.frame))
    {
        /* walk left */
        multiplierForDirection = 1;
    }
    
    
    
    
    else
    {
        /* walk right */
        multiplierForDirection = -1;
    }
    
    
    
    
    _bear.xScale = fabs(_bear.xScale) * multiplierForDirection;
    [self walkingBear];
    
    
}





/******************************************************************************************/





-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
   
    
    
    
    
    /* Determines the touch location and sets up a variable to determine which direction the bear should face */
    CGPoint location = [[touches anyObject] locationInNode:self];
    CGFloat multiplierForDirection;
    
    
    
    
    /* This sets the desired velocity */
    CGSize screenSize = self.frame.size;
    float bearVelocity = screenSize.width / 3.0;
    
    
    
    
    /* Determines the amount moved in X and Y */
    CGPoint moveDifference = CGPointMake(location.x - _bear.position.x, location.y - _bear.position.y);
    
    
    
    
    /* Figures out the actual length moved */
    float distanceToMove = sqrtf(moveDifference.x * moveDifference.x + moveDifference.y * moveDifference.y);
    
    
    
    
    /* Figures out how long it will take to move */
    float moveDuration = distanceToMove / bearVelocity;
    
    
    
    
    /* Flips the animation if necessary */
    if (moveDifference.x < 0)
    {
        multiplierForDirection = 1;
    }
    else
    {
        multiplierForDirection = -1;
    }
    
    
    
    _bear.xScale = fabs(_bear.xScale) * multiplierForDirection;
    
    
    
    
    /* Runs the bear actions */
    if ([_bear actionForKey:@"bearMoving"])
    {
        
        
        /* stops just the moving to a new location and leaves leg movement */
        [_bear removeActionForKey:@"bearMoving"];
        
        
    }
    
    if (![_bear actionForKey:@"walkingInPlaceBear"])
    {
        
        
        /* if legs are not moving go ahead and start them */
        [self walkingBear]; // starts the bear walk
        
        
    }
    
    SKAction *moveAction = [SKAction moveTo:location duration:moveDuration];
    SKAction *doneAction = [SKAction runBlock:(dispatch_block_t)^()
                            {
                                NSLog(@"Animation Completed");
                                [self bearMoveEnded];
                            }];
    
    SKAction *moveActionWithDone = [SKAction sequence:@[moveAction,doneAction]];
    
    [_bear runAction:moveActionWithDone withKey:@"bearMoving"];
    
    
}





/******************************************************************************************/



-(void)bearMoveEnded
{
    
    
    
    /* Removes all actions from the bear */
    [_bear removeAllActions];
    
    
    
    
}





/******************************************************************************************/






-(void)update: (CFTimeInterval)currentTime
{
    /* Called before each frame is rendered */
}

@end
