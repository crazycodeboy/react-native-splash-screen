package org.devio.rn.splashscreen;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Arguments;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreenModule extends ReactContextBaseJavaModule{
    public SplashScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "SplashScreen";
    }

    /**
     * 打开启动屏
     */
    @ReactMethod
    public void show() {
        show(Arguments.createMap());
    }

    /**
     * 打开启动屏
     */
    @ReactMethod
    public void show(ReadableMap options) {
        SplashScreen.show(getCurrentActivity(), options);
    }

    /**
     * 关闭启动屏
     */
    @ReactMethod
    public void hide() {
        hide(Arguments.createMap());
    }

    /**
     * 关闭启动屏
     */
    @ReactMethod
    public void hide(ReadableMap options) {
        SplashScreen.hide(getCurrentActivity(), options);
    }

    @ReactMethod
    public void showVideo() {
        SplashScreen.showVideo(getCurrentActivity());
    }

    @ReactMethod
    public void hideVideo() {
        SplashScreen.hideVideo(getCurrentActivity());
    }

    @ReactMethod
    public void setBackgroundColor(String color) {
        SplashScreen.setBackgroundColor(getCurrentActivity(), color);
    }
}
