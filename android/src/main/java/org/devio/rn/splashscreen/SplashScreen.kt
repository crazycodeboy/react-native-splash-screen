package org.devio.rn.splashscreen


import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.WindowManager
import java.lang.ref.WeakReference


object SplashScreen {

  private var mSplashDialog: Dialog? = null

  private var mActivity: WeakReference<Activity>? = null

  fun show(activity: Activity?, themeResId: Int, fullScreen: Boolean) {
    if (activity == null) return
    mActivity = WeakReference(activity)
    activity.runOnUiThread(Runnable {
      if (!activity.isFinishing) {
        mSplashDialog = Dialog(activity, themeResId)
        mSplashDialog!!.setContentView(R.layout.launch_screen)
        mSplashDialog!!.setCancelable(false)
        if (fullScreen) {
          setActivityAndroidP(mSplashDialog)
        }
        if (!mSplashDialog!!.isShowing) {
          mSplashDialog!!.show()
        }
      }
    })
  }

  fun show(activity: Activity?, fullScreen: Boolean) {
    val resourceId: Int =
      if (fullScreen) R.style.SplashScreen_Fullscreen else R.style.SplashScreen_SplashTheme

    show(activity, resourceId, fullScreen)
  }

  fun show(activity: Activity?) {
    show(activity,false)
  }

   fun hide(act: Activity?) {
    var activity = act
    if (activity == null) {
      if (mActivity == null) {
        return
      }
      activity = mActivity!!.get()
    }

    if (activity == null) return

    val bActivity: Activity = activity

    bActivity.runOnUiThread {
      if (mSplashDialog != null && mSplashDialog!!.isShowing) {
        if (!bActivity.isFinishing && !bActivity.isDestroyed) {
          mSplashDialog!!.dismiss()
        }
        mSplashDialog = null
      }
    }
  }

  private fun setActivityAndroidP(dialog: Dialog?) {
    if (Build.VERSION.SDK_INT >= 28) {
      if (dialog != null && dialog.window != null) {
        dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) //全屏显示
        val lp = dialog.window!!.attributes
        lp.layoutInDisplayCutoutMode =
          WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        dialog.window!!.attributes = lp
      }
    }
  }
}
