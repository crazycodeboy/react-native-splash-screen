package org.devio.rn.splashscreen

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = SplashScreenModule.NAME)
class SplashScreenModule(reactContext: ReactApplicationContext) :
    NativeSplashScreenSpec(reactContext) {

    override fun getName(): String = NAME

    @ReactMethod
    override fun show() {
        SplashScreen.show(reactApplicationContext.currentActivity)
    }

    @ReactMethod
    override fun hide() {
        SplashScreen.hide(reactApplicationContext.currentActivity)
    }

    companion object {
        const val NAME = "SplashScreen"
    }
}
