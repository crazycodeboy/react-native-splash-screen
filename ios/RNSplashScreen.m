#import "RNSplashScreen.h"
#import <React/RCTBridge.h>

@implementation RNSplashScreen

RCT_EXPORT_MODULE(SplashScreen)

+ (id)allocWithZone:(NSZone *)zone {
  static RNSplashScreen *sharedInstance = nil;
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
    sharedInstance = [super allocWithZone:zone];

    // Init.
    [[NSNotificationCenter defaultCenter]
        addObserver:sharedInstance
           selector:@selector(jsLoadError:)
               name:RCTJavaScriptDidFailToLoadNotification
             object:nil];
    sharedInstance.launchScreen =
        [[UIStoryboard storyboardWithName:@"LaunchScreen"
                                   bundle:[NSBundle mainBundle]]
            instantiateViewControllerWithIdentifier:@"LaunchScreen"];
  });
  return sharedInstance;
}

- (void)setup {
  UIViewController *viewController =
      [[[[UIApplication sharedApplication] delegate] window]
          rootViewController];
  [viewController.view addSubview:self.launchScreen.view];
}

RCT_EXPORT_METHOD(show) {
  dispatch_async(dispatch_get_main_queue(), ^{
    self.launchScreen.view.alpha = 0.0;
    UIViewController *viewController =
        [[[[UIApplication sharedApplication] delegate] window]
            rootViewController];
    [viewController.view addSubview:self.launchScreen.view];
    [UIView animateWithDuration:0.5
                          delay:0.0
                        options:UIViewAnimationOptionCurveEaseIn
                     animations:^{
                       self.launchScreen.view.alpha = 1.0;
                     }
                     completion:^(BOOL finished){
                     }];
  });
}

RCT_EXPORT_METHOD(hide) {
  dispatch_async(dispatch_get_main_queue(), ^{
    [UIView animateWithDuration:0.5
        delay:0.0
        options:UIViewAnimationOptionCurveEaseIn
        animations:^{
          self.launchScreen.view.alpha = 0;
        }
        completion:^(BOOL finished) {
          [self.launchScreen.view removeFromSuperview];
        }];
  });
}

- (void)jsLoadError:(NSNotification *)notification {
  // If there was an error loading javascript, hide the splash screen so it can
  // be shown.
  // Otherwise the splash screen will remain forever, which is a hassle to
  // debug.
  [self hide];
}

@end
