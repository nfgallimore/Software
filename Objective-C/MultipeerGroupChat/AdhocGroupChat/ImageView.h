#import <UIKit/UIKit.h>

// TAG used in our custom table view cell to retreive this view
#define IMAGE_VIEW_TAG (100)

@class Transcript;

@interface ImageView : UIView

@property (nonatomic, assign) Transcript *transcript;

// Class method for computing a view height based on a given message transcript
+ (CGFloat)viewHeightForTranscript:(Transcript *)transcript;

@end
