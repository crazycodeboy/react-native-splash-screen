package org.devio.rn.splashscreen

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.NativeModule
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.module.model.ReactModuleInfo
import java.util.HashMap

class SplashScreenPackage : TurboReactPackage() {
  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
    return if (name == SplashScreenModule.NAME) {
      SplashScreenModule(reactContext)
    } else {
      null
    }
  }

  override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
    return ReactModuleInfoProvider {
      val moduleInfos: MutableMap<String, ReactModuleInfo> = HashMap()
      val isTurboModule: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
      val isCxxModule = false
      val needsEagerInit = false
      val canOverrideExistingModule = false
      moduleInfos[SplashScreenModule.NAME] = ReactModuleInfo(
        SplashScreenModule.NAME,
        SplashScreenModule.NAME,
        canOverrideExistingModule,  // canOverrideExistingModule
        needsEagerInit,  // needsEagerInit
        isCxxModule,  // isCxxModule
        isTurboModule // isTurboModule
      )
      moduleInfos
    }
  }
}
