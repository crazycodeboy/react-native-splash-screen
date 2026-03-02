# react-native-splash-screen

[![Download](https://img.shields.io/badge/Download-v4.0.0-ff69b4.svg)](https://www.npmjs.com/package/react-native-splash-screen)
[![License MIT](http://img.shields.io/badge/license-MIT-orange.svg?style=flat)](https://raw.githubusercontent.com/crazycodeboy/react-native-check-box/master/LICENSE)

A splash screen library for React Native. It lets you show/hide a custom splash view during app initialization on Android and iOS.

> This is the upgraded `v4` line of `react-native-splash-screen`, and it only supports React Native New Architecture.

## Compatibility

- `react-native-splash-screen@4.x`
  - React Native `>= 0.84.0`
  - New Architecture (TurboModule / Codegen)
- Use `v3.x` for legacy architecture projects: [README-v3.md](./README-v3.md)

`v4.x` does not support Legacy Architecture.

## Installation

```bash
npm i react-native-splash-screen
```

> `4.x` does not require `react-native link` or manual edits to `settings.gradle` / `MainApplication`.

## Usage

```ts
import SplashScreen from 'react-native-splash-screen';

// Call when app initialization is done
SplashScreen.hide();
```

## Android Setup

### 1. Show splash in `MainActivity.kt`

Call before `super.onCreate`:

```kotlin
import android.os.Bundle
import com.facebook.react.ReactActivity
import org.devio.rn.splashscreen.SplashScreen

class MainActivity : ReactActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    SplashScreen.show(this, true)
    super.onCreate(savedInstanceState)
  }
}
```

### 2. Create `launch_screen.xml`

Path: `android/app/src/main/res/layout/launch_screen.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#000000">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop"
        android:src="@mipmap/launch_screen" />
</RelativeLayout>
```

Place your splash image into density folders (for example `mipmap-xhdpi`, `mipmap-xxhdpi`, etc.).

### 3. Optional: translucent window workaround

If you want to reduce the visible Android 12+ system icon phase, add this to your **launch theme** (for example `LaunchTheme`):

```xml
<item name="android:windowIsTranslucent">true</item>
```

Recommendations:

- Apply only in launch theme, not global `AppTheme`
- Behavior can vary slightly across OEM ROMs

## iOS Setup

### 1. Configure `LaunchScreen.storyboard`

Use static layout and image assets from `Assets.xcassets`.

### 2. Call in `AppDelegate.swift`

```swift
import react_native_splash_screen
```

Call after React Native startup:

```swift
RNSplashScreen.show()
```

Example (`AppDelegate.swift`, RN 0.84 template):

```swift
import UIKit
import React
import React_RCTAppDelegate
import ReactAppDependencyProvider
import react_native_splash_screen // <--- 添加react_native_splash_screen

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
  var window: UIWindow?
  var reactNativeDelegate: ReactNativeDelegate?
  var reactNativeFactory: RCTReactNativeFactory?

  func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
  ) -> Bool {
    let delegate = ReactNativeDelegate()
    let factory = RCTReactNativeFactory(delegate: delegate)
    delegate.dependencyProvider = RCTAppDependencyProvider()

    reactNativeDelegate = delegate
    reactNativeFactory = factory
    window = UIWindow(frame: UIScreen.main.bounds)

    factory.startReactNative(
      withModuleName: "YourAppName",
      in: window,
      launchOptions: launchOptions
    )
    RNSplashScreen.show() // <--- 确保在 React Native 启动后调用
    return true
  }
}

class ReactNativeDelegate: RCTDefaultReactNativeFactoryDelegate {
  override func sourceURL(for bridge: RCTBridge) -> URL? {
    self.bundleURL()
  }

  override func bundleURL() -> URL? {
#if DEBUG
    RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index")
#else
    Bundle.main.url(forResource: "main", withExtension: "jsbundle")
#endif
  }
}
```

## API

- `show()`: show splash screen (native side)
- `hide()`: hide splash screen (JS side)

## FAQ

### Why do I still see a system splash phase on Android 12+?

Android 12+ always has a system-level splash phase. This is platform behavior. This library controls your in-app splash show/hide behavior.


## Legacy

For legacy architecture projects, see [README-v3.md](./README-v3.md).

---

MIT License
