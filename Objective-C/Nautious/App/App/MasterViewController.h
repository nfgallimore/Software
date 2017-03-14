//
//  MasterViewController.h
//  App
//
//  Created by Nick Gallimore on 12/9/13.
//  Copyright (c) 2013 Virtual Theologies. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DetailViewController;

@interface MasterViewController : UITableViewController

@property (strong, nonatomic) DetailViewController *detailViewController;

@end
