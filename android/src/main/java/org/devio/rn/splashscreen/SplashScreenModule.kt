package org.devio.rn.splashscreen

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod


class SplashScreenModule internal constructor(context: ReactApplicationContext) :
  SplashScreenSpec(context) {
  private val reactcontect = context

  @ReactMethod
  override fun hide() {
    SplashScreen.hide(reactcontect.currentActivity)
  }

  @ReactMethod
  override fun show(fullScreen: Boolean?) {
    SplashScreen.show(reactcontect.currentActivity,fullScreen!==null)
  }

  override fun getName(): String {
    return NAME
  }



  companion object {
    const val NAME = "RNSplashScreen"
  }
}
