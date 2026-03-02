#import "RNSplashScreen.h"
#import <React/RCTBridgeModule.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import <rnsplashscreen/rnsplashscreen.h>
#endif

@interface RNSplashScreenManager : NSObject
+ (void)show;
+ (void)hide;
+ (void)showSplash:(NSString *)splashScreen inRootView:(UIView *)rootView;
@end

#ifdef RCT_NEW_ARCH_ENABLED
using namespace facebook::react;
@interface RNSplashScreen () <NativeSplashScreenSpec>
@end
#endif

@implementation RNSplashScreen

- (dispatch_queue_t)methodQueue{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(SplashScreen)

+ (void)show {
    [RNSplashScreenManager show];
}

+ (void)showSplash:(NSString*)splashScreen inRootView:(UIView*)rootView {
    [RNSplashScreenManager showSplash:splashScreen inRootView:rootView];
}

+ (void)hide {
    [RNSplashScreenManager hide];
}

RCT_EXPORT_METHOD(hide) {
    [RNSplashScreenManager hide];
}

RCT_EXPORT_METHOD(show) {
    [RNSplashScreenManager show];
}

#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<TurboModule>)getTurboModule:(const ObjCTurboModule::InitParams &)params
{
    return std::make_shared<NativeSplashScreenSpecJSI>(params);
}
#endif

@end
