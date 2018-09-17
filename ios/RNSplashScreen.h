/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
#import <React/RCTBridgeModule.h>

@interface RNSplashScreen : NSObject<RCTBridgeModule>
+ (void)show;
+ (void)show:(NSString *)orientation;
+ (void)hide;
+ (void)hide:(NSString *)orientation;
@end
