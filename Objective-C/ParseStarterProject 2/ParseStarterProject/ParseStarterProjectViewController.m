#import "ParseStarterProjectViewController.h"
#import <Parse/Parse.h>

typedef struct DerbySetup
{
    NSInteger rearWeight;
    NSInteger frontWeight;
    NSInteger rearTorque;
    NSInteger frontTorque;
    
}DerbySetup;


@implementation ParseStarterProjectViewController

- (void)didReceiveMemoryWarning
{
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
}

#pragma mark - UIViewController


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad
{
    
    
//    PFObject *derbySetup = [PFObject objectWithClassName:@"DerbySetup"];
//    derbySetup[@"rearWeight"] = @15;
//    derbySetup[@"rearTorque"] = @300;
//    derbySetup[@"frontTorque"] = @150;
//    derbySetup[@"frontWeight"] = @12;
//    derbySetup[@"division"] = @"M";
//    derbySetup[@"setupName"] = @"Akron";
//    derbySetup[@"wins"] = @12;
//    derbySetup[@"losses"] = @2;
//    derbySetup[@"email"] = @"nfgallimore@gmail.com";
//    
//    [derbySetup saveInBackground];
    
    [self getDerbySetup];
    
    [super viewDidLoad];
}

- (DerbySetup)findCallback:(NSArray *)objects error:(NSError *)error :(DerbySetup)derbySetup
{
    if (!error)
    {
        // The find succeeded.
        NSLog(@"Successfully retrieved %d scores.", objects.count);
        
        // Do something with the found objects
        for (PFObject *object in objects)
        {
            // The get request succeeded. Log the data.
            NSLog(@"Rear Weight: %@", object[@"rearWeight"]);
            NSLog(@"Front Weight: %@", object[@"frontWeight"]);
            NSLog(@"Rear Torque: %@", object[@"rearTorque"]);
            NSLog(@"Front Torque: %@", object[@"frontTorque"]);
            NSLog(@"Division: %@", object[@"division"]);
            NSLog(@"Setup Name: %@", object[@"setupName"]);
            NSLog(@"Wins: %@", object[@"wins"]);
            NSLog(@"Losses: %@", object[@"losses"]);
            NSLog(@"Email: %@", object[@"email"]);
            
        }
    }
    else
    {
        // Log details of the failure
        NSLog(@"Error: %@ %@", error, [error userInfo]);
    }
}

- (DerbySetup)getDerbySetup :(NSString*) email :(NSString*)division
{
    
    // Then, elsewhere in your code...
    PFQuery *query = [PFQuery queryWithClassName:@"DerbySetup"];
    [query whereKey:@"email" equalTo:email];
    [query whereKey:@"division" equalTo: division];
    [query findObjectsInBackgroundWithTarget:self
                                    selector:@selector(findCallback:error:)];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}

@end
