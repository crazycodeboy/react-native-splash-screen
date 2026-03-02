import Foundation
import UIKit

@objc(RNSplashScreenManager)
@objcMembers
public final class RNSplashScreenManager: NSObject {
    private static var waiting = true
    private static var addedJsLoadErrorObserver = false
    private static var loadingView: UIView?

    public class func show() {
        if !addedJsLoadErrorObserver {
            NotificationCenter.default.addObserver(
                self,
                selector: #selector(jsLoadError(_:)),
                name: Notification.Name(rawValue: "RCTJavaScriptDidFailToLoadNotification"),
                object: nil
            )
            addedJsLoadErrorObserver = true
        }

        while waiting {
            RunLoop.main.run(until: Date(timeIntervalSinceNow: 0.1))
        }
    }

    public class func showSplash(_ splashScreen: String, inRootView rootView: UIView) {
        if loadingView == nil {
            if let nibView = Bundle.main.loadNibNamed(splashScreen, owner: self, options: nil)?.first as? UIView {
                var frame = rootView.frame
                frame.origin = .zero
                nibView.frame = frame
                loadingView = nibView
            }
        }

        waiting = false

        if let loadingView {
            rootView.addSubview(loadingView)
        }
    }

    public class func hide() {
        if waiting {
            DispatchQueue.main.async {
                waiting = false
            }
        } else {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                loadingView?.removeFromSuperview()
                loadingView = nil
            }
        }
    }

    @objc
    private class func jsLoadError(_ notification: Notification) {
        hide()
    }
}
