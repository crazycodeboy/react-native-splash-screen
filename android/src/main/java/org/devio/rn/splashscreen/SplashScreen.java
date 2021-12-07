package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.widget.VideoView;
import android.net.Uri;
import android.util.DisplayMetrics;

import java.lang.ref.WeakReference;
import java.net.URI;

/**
 * SplashScreen 启动屏 from：http://www.devio.org Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final int themeResId, final boolean isvideoSplash) {
        if (activity == null)
            return;
        mActivity = new WeakReference<Activity>(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    mSplashDialog = new Dialog(activity, themeResId);
                    mSplashDialog.setContentView(R.layout.launch_screen);
                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                        if(isvideoSplash){
                            String path = "android.resource://" + activity.getPackageName() + "/" + R.raw.splash_video;
                            VideoView mVideoView = (VideoView) mSplashDialog.findViewById(R.id.simpleVideoView);
                            mVideoView.setVideoURI(Uri.parse(path));
                            mVideoView.start();
                            mSplashDialog.setCancelable(false);
                        }
                    }
                }
            }
        });
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final boolean fullScreen, final boolean isvideoSplash) {
        int resourceId = fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;

        show(activity, resourceId, isvideoSplash);
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        show(activity, false, false);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity) {
        if (activity == null) {
            if (mActivity == null) {
                return;
            }
            activity = mActivity.get();
        }

        if (activity == null)
            return;

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
    }
}
