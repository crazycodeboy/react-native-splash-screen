package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.View;
import android.graphics.Color;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableMap;

import java.lang.ref.WeakReference;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final ReadableMap options) {
        if (activity == null) return;
        if (mSplashDialog != null) return;

        mActivity = new WeakReference<Activity>(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    String bgColor = getBackgroundColorOption(options);
                    if (bgColor != null) {
                        setBackgroundColorSync(activity, bgColor);
                    }

                    int themeResId = getThemeIdOption(options);
                    mSplashDialog = new Dialog(activity, themeResId);
                    mSplashDialog.setContentView(R.layout.launch_screen);
                    mSplashDialog.setCancelable(false);

                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                    }
                }
            }
        });
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        WritableMap options = Arguments.createMap();
        show(activity, options);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity, final ReadableMap options) {
        if (activity == null) {
            if (mActivity == null) {
                return;
            }
            activity = mActivity.get();
        }

        if (activity == null) return;

        final Activity _activity = activity;

        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSplashDialog != null && mSplashDialog.isShowing()) {
                    boolean isDestroyed = false;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        isDestroyed = _activity.isDestroyed();
                    }

                    if (!_activity.isFinishing() && !isDestroyed) {
                        try {
                            mSplashDialog.dismiss();
                        } catch (Exception e) {
                            Log.d("RNSplashScreen error on dismiss", e.getMessage());
                        }
                    }
                    mSplashDialog = null;
                }

                String bgColor = getBackgroundColorOption(options);
                if (bgColor != null) {
                    setBackgroundColorSync(_activity, bgColor);
                }
            }
        });
    }

    public static void setBackgroundColor(final Activity activity, final String color) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBackgroundColorSync(activity, color);
            }
        });
    }

    private static void setBackgroundColorSync(Activity activity, String color) {
        View rootView = activity.getWindow().getDecorView();
        int parsedColor = Color.parseColor(color);
        rootView.getRootView().setBackgroundColor(parsedColor);
    }

    private static String getBackgroundColorOption(ReadableMap options) {
        return options.hasKey("backgroundColor") ? options.getString("backgroundColor") : null;
    }

    private static int getThemeIdOption(ReadableMap options) {
        boolean fullScreen = options.hasKey("fullScreen") ? options.getBoolean("fullScreen") : false;
        return fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;
    }
}
