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

@implementation RNSplashScreen
- (dispatch_queue_t)methodQueue{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE(SplashScreen)

NSInteger const RNSplashScreenOverlayTag = 39293;

+ (void)show {
  NSString* launchImageName = [RNSplashScreen launchImageNameForOrientation:UIDeviceOrientationPortrait];
  UIImage *image = [UIImage imageNamed:launchImageName];
  if (image == nil) return;

  UIImageView *imageView = [[UIImageView alloc] initWithImage:image];
  // Give some decent tagvalue or keep a reference of imageView in self
  imageView.tag = RNSplashScreenOverlayTag;
  imageView.contentMode = UIViewContentModeScaleAspectFill;
  [UIApplication.sharedApplication.keyWindow.subviews.lastObject addSubview:imageView];
}

+ (void)hide {
  UIImageView *imageView = (UIImageView *)[UIApplication.sharedApplication.keyWindow.subviews.lastObject viewWithTag:RNSplashScreenOverlayTag];
  if (imageView != nil) {
    [imageView removeFromSuperview];
  }
}

+ (NSString *)launchImageNameForOrientation:(UIDeviceOrientation)orientation {
  CGSize viewSize = [[UIScreen mainScreen] bounds].size;
  NSString* viewOrientation = @"Portrait";
  if (UIDeviceOrientationIsLandscape(orientation)) {
    viewSize = CGSizeMake(viewSize.height, viewSize.width);
    viewOrientation = @"Landscape";
  }

  NSArray* imagesDict = [[[NSBundle mainBundle] infoDictionary] valueForKey:@"UILaunchImages"];
  for (NSDictionary* dict in imagesDict) {
    CGSize imageSize = CGSizeFromString(dict[@"UILaunchImageSize"]);
    if (CGSizeEqualToSize(imageSize, viewSize) && [viewOrientation isEqualToString:dict[@"UILaunchImageOrientation"]])
      return dict[@"UILaunchImageName"];
  }

  return nil;
}

RCT_EXPORT_METHOD(hide) {
    [RNSplashScreen hide];
}

RCT_EXPORT_METHOD(show) {
    [RNSplashScreen show];
}

@end
