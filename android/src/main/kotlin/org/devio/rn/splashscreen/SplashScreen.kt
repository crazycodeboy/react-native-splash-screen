package org.devio.rn.splashscreen

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.WindowManager
import java.lang.ref.WeakReference

object SplashScreen {
    private var splashDialog: Dialog? = null
    private var activityRef: WeakReference<Activity>? = null

    @JvmStatic
    fun show(activity: Activity?, themeResId: Int, fullScreen: Boolean) {
        if (activity == null) return

        activityRef = WeakReference(activity)
        activity.runOnUiThread {
            if (!activity.isFinishing) {
                splashDialog = Dialog(activity, themeResId).apply {
                    setContentView(R.layout.launch_screen)
                    setCancelable(false)
                    if (fullScreen) {
                        setActivityAndroidP(this)
                    }
                }

                if (splashDialog?.isShowing == false) {
                    splashDialog?.show()
                }
            }
        }
    }

    @JvmStatic
    fun show(activity: Activity?, fullScreen: Boolean) {
        val resourceId =
            if (fullScreen) R.style.SplashScreen_Fullscreen else R.style.SplashScreen_SplashTheme
        show(activity, resourceId, fullScreen)
    }

    @JvmStatic
    fun show(activity: Activity?) {
        show(activity, false)
    }

    @JvmStatic
    fun hide(activity: Activity?) {
        val resolvedActivity = activity ?: activityRef?.get() ?: return
        resolvedActivity.runOnUiThread {
            val dialog = splashDialog
            if (dialog != null && dialog.isShowing) {
                val isDestroyed =
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                        resolvedActivity.isDestroyed

                if (!resolvedActivity.isFinishing && !isDestroyed) {
                    dialog.dismiss()
                }
            }
            splashDialog = null
        }
    }

    private fun setActivityAndroidP(dialog: Dialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            dialog.window?.let { window ->
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                val layoutParams = window.attributes
                layoutParams.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.attributes = layoutParams
            }
        }
    }
}
