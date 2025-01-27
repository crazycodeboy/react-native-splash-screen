/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
#ifdef RCT_NEW_ARCH_ENABLED
#import <RNSplashScreenSpec/RNSplashScreenSpec.h>
@interface RNSplashScreen : NSObject <NativeSplashScreenSpec>
#else
#import <React/RCTBridgeModule.h>

@interface RNSplashScreen : NSObject <RCTBridgeModule>
#endif
+ (void)showSplash:(NSString*)splashScreen inRootView:(UIView*)rootView;
+ (void)show;
+ (void)hide;


@end
