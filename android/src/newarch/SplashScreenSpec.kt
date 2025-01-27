package org.devio.rn.splashscreen

import com.facebook.react.bridge.ReactApplicationContext

abstract class SplashScreenSpec internal constructor(context: ReactApplicationContext) :
  NativeSplashScreenSpec(context) {
  private val reactcontect = context
  override fun hide() {
    SplashScreen.hide(reactcontect.currentActivity)
  }

  override fun show(fullScreen: Boolean?) {
    SplashScreen.show(reactcontect.currentActivity,fullScreen!==null)
  }
}
