//
//  DetailViewController.h
//  App
//
//  Created by Nick Gallimore on 12/9/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (strong, nonatomic) id detailItem;

@property (weak, nonatomic) IBOutlet UILabel *detailDescriptionLabel;
@end
