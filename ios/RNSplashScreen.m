/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */

#import "RNSplashScreen.h"
#import <React/RCTBridge.h>

static bool waiting = true;
static bool addedJsLoadErrorObserver = false;

@implementation RNSplashScreen
- (dispatch_queue_t)methodQueue{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(SplashScreen)

+ (void)show {
    if (!addedJsLoadErrorObserver) {
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(jsLoadError:) name:RCTJavaScriptDidFailToLoadNotification object:nil];
        addedJsLoadErrorObserver = true;
    }

    while (waiting) {
        NSDate* later = [NSDate dateWithTimeIntervalSinceNow:0.1];
        [[NSRunLoop mainRunLoop] runUntilDate:later];
    }
}

+ (void)show:(NSString *)orientation  {
    NSNumber *value=[NSNumber numberWithInt: 0];
    if ([orientation isEqualToString:@"portrait"]) {
        value=[NSNumber numberWithInt: 1];
    } else if ([orientation isEqualToString:@"portraitUpsideDown"]) {
        value=[NSNumber numberWithInt: 2];
    } else if ([orientation isEqualToString:@"landscapeLeft"]) {
        value=[NSNumber numberWithInt: 3];
    } else if ([orientation isEqualToString:@"landscapeRight"]) {
        value=[NSNumber numberWithInt: 4];
    } else if ([orientation isEqualToString:@"faceUp"]) {
        value=[NSNumber numberWithInt: 5];
    } else if ([orientation isEqualToString:@"faceDown"]) {
        value=[NSNumber numberWithInt: 6];
    }
    [[UIDevice currentDevice] setValue:value forKey:@"orientation"];

    [self show];
}

+ (void)hide {
    dispatch_async(dispatch_get_main_queue(),
                   ^{
                       waiting = false;
                   });
}

+ (void)hide:(NSString *)orientation {
    [self hide];

    NSNumber *value=[NSNumber numberWithInt: 0];
    if ([orientation isEqualToString:@"portrait"]) {
        value=[NSNumber numberWithInt: 1];
    } else if ([orientation isEqualToString:@"portraitUpsideDown"]) {
        value=[NSNumber numberWithInt: 2];
    } else if ([orientation isEqualToString:@"landscapeLeft"]) {
        value=[NSNumber numberWithInt: 3];
    } else if ([orientation isEqualToString:@"landscapeRight"]) {
        value=[NSNumber numberWithInt: 4];
    } else if ([orientation isEqualToString:@"faceUp"]) {
        value=[NSNumber numberWithInt: 5];
    } else if ([orientation isEqualToString:@"faceDown"]) {
        value=[NSNumber numberWithInt: 6];
    }
    [[UIDevice currentDevice] setValue:value forKey:@"orientation"];
}

+ (void) jsLoadError:(NSNotification*)notification
{
    // If there was an error loading javascript, hide the splash screen so it can be shown.  Otherwise the splash screen will remain forever, which is a hassle to debug.
    [RNSplashScreen hide];
}

RCT_EXPORT_METHOD(hide) {
    [RNSplashScreen hide];
}

RCT_EXPORT_METHOD(show) {
    [RNSplashScreen show];
}

@end
