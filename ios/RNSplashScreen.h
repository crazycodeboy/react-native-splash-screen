#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>

@interface RNSplashScreen : NSObject <RCTBridgeModule>
@property(nonatomic, strong) UIViewController *launchScreen;
- (void)show;
- (void)hide;
- (void)setup;
@end
