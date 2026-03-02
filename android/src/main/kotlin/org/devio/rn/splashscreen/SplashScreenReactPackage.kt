package org.devio.rn.splashscreen

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.uimanager.ViewManager

class SplashScreenReactPackage : BaseReactPackage() {
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        return when (name) {
            SplashScreenModule.NAME -> SplashScreenModule(reactContext)
            else -> null
        }
    }

    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        val moduleList: Array<Class<out NativeModule>> = arrayOf(SplashScreenModule::class.java)
        val reactModuleInfoMap = HashMap<String, ReactModuleInfo>()

        moduleList.forEach { moduleClass ->
            val reactModule = moduleClass.getAnnotation(ReactModule::class.java) ?: return@forEach
            val isTurbo = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
            reactModuleInfoMap[reactModule.name] =
                ReactModuleInfo(
                    reactModule.name,
                    moduleClass.name,
                    reactModule.canOverrideExistingModule,
                    reactModule.needsEagerInit,
                    reactModule.isCxxModule,
                    isTurbo
                )
        }

        return ReactModuleInfoProvider { reactModuleInfoMap }
    }

    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): List<ViewManager<*, *>> {
        return emptyList()
    }
}
