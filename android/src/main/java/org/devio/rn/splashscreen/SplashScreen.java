package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.graphics.Color;
import android.util.Log;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.view.ViewGroup;
import android.widget.VideoView;

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
    private static boolean loopVideo = false;
    private static boolean isVideoActive = false;
    private static boolean isImageActive = false;
    private static VideoView lastVideoView = null;
    private static Runnable videoPauseRunnable = null;

    public static void showVideo(final Activity activity) {
        showVideo(activity, Arguments.createMap());
    }

    public static void showVideo(final Activity activity, final ReadableMap options) {
        if (activity == null) return;
        if (mSplashDialog != null) return;
        if (isImageActive || isVideoActive) return;
        isVideoActive = true;

        mActivity = new WeakReference<Activity>(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    mSplashDialog = new Dialog(activity, R.style.SplashScreen_Fullscreen);
                    mSplashDialog.setContentView(R.layout.video_view);
                    mSplashDialog.setCancelable(false);

                    Context context = activity.getApplicationContext();

                    String videoPath = "android.resource://" + context.getPackageName() + "/" + R.raw.splashscreen;

                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(context, Uri.parse(videoPath));
                    int videoWidth = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                    int videoHeight = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
                    retriever.release();


                    VideoView videoView = (VideoView) mSplashDialog.findViewById(R.id.video_view);
                    float viewHeight = videoView.getHeight();
                    float viewWidth = videoView.getWidth();

                    float ratioWidth = viewWidth / videoWidth;
                    float ratioHeight = viewHeight / videoHeight;
                    int fullWidth;
                    int fullHeight;
                    if (ratioWidth < ratioHeight) {
                        fullWidth = (int) (videoWidth * ratioWidth);
                        fullHeight = (int) (videoHeight * ratioWidth);
                    } else {
                        fullWidth = (int) (videoWidth * ratioHeight);
                        fullHeight = (int) (videoHeight * ratioHeight);
                    }

                    videoView.getLayoutParams().width = fullWidth;
                    videoView.getLayoutParams().height = fullHeight;

                    videoView.setVideoPath(videoPath);

                    boolean allowAudio = options.hasKey("allowAudio") ? options.getBoolean("allowAudio") : false;
                    if (!allowAudio) {
                        videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
                    }

                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            try {
                                if (!allowAudio) {
                                    mp.setVolume(0f, 0f);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    videoView.start();

                    lastVideoView = videoView;

                    loopVideo = options.hasKey("loopVideo") ? options.getBoolean("loopVideo") : false;
                    int pauseAfterMs = options.hasKey("pauseAfterMs") ? options.getInt("pauseAfterMs") : 0;
                    if (pauseAfterMs > 0) {
                        videoPauseRunnable = new Runnable() {
                            @Override
                            public void run() {
                                if (lastVideoView != null) {
                                    lastVideoView.pause();
                                }
                            }
                        };
                        videoView.postDelayed(videoPauseRunnable, pauseAfterMs);
                    }

                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                    }

                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            manageVideoEnd(activity);
                        }
                    });

                    videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.d("splashscreen video", "onError" + Integer.toString(what) + ", " + Integer.toString(extra));
                            hideVideo(activity);
                            return true;
                        }
                    });
                }
            }
        });
    }

    public static void removeVideoPauseOption(Activity activity) {
        if (isImageActive) return;
        if (lastVideoView == null || videoPauseRunnable == null) return;

        lastVideoView.removeCallbacks(videoPauseRunnable);
        videoPauseRunnable = null;
    }

    public static void manageVideoEnd(Activity activity) {
        if (loopVideo) {
            resumeVideo(activity);
        } else {
            hideVideo(activity);
        }
    }

    public static void resumeVideo(Activity activity) {
        if (isImageActive) return;
        if (lastVideoView == null) return;
        removeVideoPauseOption(activity);

        lastVideoView.start();
    }

    public static void hideVideo(Activity activity) {
        if (isImageActive) return;

        removeVideoPauseOption(activity);
        lastVideoView = null;
        _hide(activity, Arguments.createMap());
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final ReadableMap options) {
        if (activity == null) return;
        if (mSplashDialog != null) return;
        if (isImageActive || isVideoActive) return;
        isImageActive = true;

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
        if (isVideoActive) return;
        _hide(activity, options);
    }

    private static void _hide(Activity activity, final ReadableMap options) {
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
                isImageActive = false;
                isVideoActive = false;
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
