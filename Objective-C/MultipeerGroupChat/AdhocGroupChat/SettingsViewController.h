#import <UIKit/UIKit.h>

@protocol SettingsViewControllerDelegate;

@interface SettingsViewController : UIViewController

@property (assign, nonatomic) id<SettingsViewControllerDelegate> delegate;
@property (copy, nonatomic) NSString *displayName;
@property (copy, nonatomic) NSString *serviceType;

@end

@protocol SettingsViewControllerDelegate <NSObject>

- (void)controller:(SettingsViewController *)controller didCreateChatRoomWithDisplayName:(NSString *)displayName serviceType:(NSString *)serviceType;

@end
