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

    // Store the React application context in a member variable.
    private final ReactApplicationContext mContext;

    public SplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;  // Save the context for later use.
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }

    /**
     * 打开启动屏 (Show the splash screen)
     */
    @ReactMethod
    public void show() {
        // Retrieve the current activity using the stored context.
        Activity currentActivity = mContext.getCurrentActivity();
        SplashScreen.show(currentActivity);
    }

    /**
     * 关闭启动屏 (Hide the splash screen)
     */
    @ReactMethod
    public void hide() {
        // Retrieve the current activity using the stored context.
        Activity currentActivity = mContext.getCurrentActivity();
        SplashScreen.hide(currentActivity);
    }
}
