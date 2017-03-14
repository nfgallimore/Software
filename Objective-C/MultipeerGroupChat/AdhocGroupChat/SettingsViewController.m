@import MultipeerConnectivity;

#import "SettingsViewController.h"

static NSUInteger const MCNearbyServiceMaxServiceTypeLength = 15;

@interface SettingsViewController () <UITextFieldDelegate>

@property (retain, nonatomic) IBOutlet UITextField *displayNameTextField;
@property (retain, nonatomic) IBOutlet UITextField *serviceTypeTextField;

@end

@implementation SettingsViewController

- (void)viewDidLoad
{
    self.displayNameTextField.text = self.displayName;
    self.serviceTypeTextField.text = self.serviceType;
}

#pragma mark - private 

// RFC 6335 text:
//   5.1. Service Name Syntax
//
//     Valid service names are hereby normatively defined as follows:
//
//     o  MUST be at least 1 character and no more than 15 characters long
//     o  MUST contain only US-ASCII [ANSI.X3.4-1986] letters 'A' - 'Z' and
//        'a' - 'z', digits '0' - '9', and hyphens ('-', ASCII 0x2D or
//        decimal 45)
//     o  MUST contain at least one letter ('A' - 'Z' or 'a' - 'z')
//     o  MUST NOT begin or end with a hyphen
//     o  hyphens MUST NOT be adjacent to other hyphens
//
- (BOOL)isDisplayNameAndServiceTypeValid
{
    MCPeerID *peerID;
    @try {
        peerID = [[MCPeerID alloc] initWithDisplayName:self.displayNameTextField.text];
    }
    @catch (NSException *exception) {
        NSLog(@"Invalid display name [%@]", self.displayNameTextField.text);
        return NO;
    }

    // Check if using this service type string causes a framework exception
    MCNearbyServiceAdvertiser *advertiser;
    @try {
        advertiser = [[MCNearbyServiceAdvertiser alloc] initWithPeer:peerID discoveryInfo:nil serviceType:self.serviceTypeTextField.text];
    }
    @catch (NSException *exception) {
        NSLog(@"Invalid service type [%@]", self.serviceTypeTextField.text);
        return NO;
    }
    NSLog(@"Room Name [%@] (aka service type) and display name [%@] are valid", advertiser.serviceType, peerID.displayName);
    // all exception checks passed
    return YES;
}

#pragma mark - IBAction methods

- (IBAction)doneTapped:(id)sender
{
    if ([self isDisplayNameAndServiceTypeValid]) {
        // Fields are set.  send the values back to the delegate
        [self.delegate controller:self didCreateChatRoomWithDisplayName:self.displayNameTextField.text serviceType:self.serviceTypeTextField.text];
    }
    else {
        // These are mandatory fields.  Alert the user
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error" message:@"You must set a valid room name and your display name" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [alert show];
    }
}

#pragma mark - UITextFieldDelegate methods

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [self.view endEditing:YES];
    return YES;
}

- (void)textFieldDidEndEditing:(UITextField *)textField
{
    [self.view endEditing:YES];
}

@end
