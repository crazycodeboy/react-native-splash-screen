package org.devio.rn.splashscreen;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeSplashScreenSpec extends ReactContextBaseJavaModule
    implements ReactModuleWithSpec, TurboModule {
  public NativeSplashScreenSpec(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @ReactMethod
  public abstract void show();

  @ReactMethod
  public abstract void hide();
}
