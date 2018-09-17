package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.content.pm.ActivityInfo;

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
    public static void show(final Activity activity, final int themeResId, final String newScreenOrientation) {
        if (activity == null) return;
        mActivity = new WeakReference<Activity>(activity);

        switch (newScreenOrientation) {
            case "portrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case "landscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case "sensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;
            case "behind":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                break;
            case "reverseLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case "reversePortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                break;
            case "sensorLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                break;
            case "sensorPortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                break;
            case "userLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
                break;
            case "userPortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
                break;
            case "fullSensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                break;
            case "nosensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                break;
            case "user":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
            case "fullUser":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
                break;
            case "locked":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                break;
            default:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                break;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
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
    public static void show(final Activity activity, final boolean fullScreen, final String newScreenOrientation) {
        int resourceId = fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;

        show(activity, resourceId, newScreenOrientation);
    }

    public static void show(final Activity activity, final boolean fullScreen) {
        int resourceId = fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;

        show(activity, resourceId, "");
    }

    public static void show(final Activity activity, final String newScreenOrientation) {
        show(activity, false, newScreenOrientation);
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        show(activity, false, "");
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity, String newScreenOrientation) {
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
                        mSplashDialog.dismiss();
                    }
                    mSplashDialog = null;
                }
            }
        });

        switch (newScreenOrientation) {
            case "portrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case "landscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case "sensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;
            case "behind":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                break;
            case "reverseLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case "reversePortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                break;
            case "sensorLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                break;
            case "sensorPortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                break;
            case "userLandscape":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
                break;
            case "userPortrait":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
                break;
            case "fullSensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                break;
            case "nosensor":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
                break;
            case "user":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
            case "fullUser":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
                break;
            case "locked":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
                break;
            default:
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                break;
        }
    }

    public static void hide(Activity activity) {
        hide(activity, "");
    }
}
