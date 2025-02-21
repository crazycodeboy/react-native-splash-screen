package org.devio.rn.splashscreen;

import android.app.Activity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author: CrazyCodeBoy
 * GitHub: https://github.com/crazycodeboy
 * Email: crazycodeboy@gmail.com
 */
public class SplashScreenModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext mContext;

    public SplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }

    @ReactMethod
    public void show() {
        Activity currentActivity = mContext.getCurrentActivity();
        SplashScreen.show(currentActivity);
    }

    @ReactMethod
    public void hide() {
        Activity currentActivity = mContext.getCurrentActivity();
        SplashScreen.hide(currentActivity);
    }
}
