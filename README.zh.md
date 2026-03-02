# react-native-splash-screen

[![Download](https://img.shields.io/badge/Download-v4.0.0-ff69b4.svg)](https://www.npmjs.com/package/react-native-splash-screen)
[![License MIT](http://img.shields.io/badge/license-MIT-orange.svg?style=flat)](https://raw.githubusercontent.com/crazycodeboy/react-native-check-box/master/LICENSE)

React Native 启动屏库，用于在应用初始化期间显示/隐藏自定义启动页，支持 Android 与 iOS。

> 这是 `react-native-splash-screen` 的 `v4` 升级版本，仅支持 React Native 新架构。

## 兼容性

- `react-native-splash-screen@4.x`
  - React Native `>= 0.84.0`
  - New Architecture（TurboModule / Codegen）
- 老项目请使用 `v3.x`（旧架构）：[README.zh-v3.md](./README.zh-v3.md)

`v4.x` 不支持旧架构（Legacy Architecture）。

## 安装

```bash
npm i react-native-splash-screen
```

> `4.x` 不再需要 `react-native link`，也不需要手动修改 `settings.gradle`、`MainApplication`。

## 使用

```ts
import SplashScreen from 'react-native-splash-screen';

// 在初始化完成后调用
SplashScreen.hide();
```

## Android 配置

### 1. 在 `MainActivity.kt` 显示启动页

在 `super.onCreate` 前调用：

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

### 2. 配置 `launch_screen.xml`

文件路径：`android/app/src/main/res/layout/launch_screen.xml`

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

将你的启动图放到对应密度目录（如 `mipmap-xhdpi` / `mipmap-xxhdpi` 等）。

### 3. 可选：透明窗口方案（你当前使用的方式）

如果你希望尽量弱化 Android 12+ 的系统图标阶段，可在 **启动主题**（如 `LaunchTheme`）中加：

```xml
<item name="android:windowIsTranslucent">true</item>
```

建议：

- 只加在启动主题，不要加在全局 `AppTheme`
- 不同 ROM 可能有轻微过渡差异（这是系统层行为）

## iOS 配置

### 1. 配置 `LaunchScreen.storyboard`

使用静态布局，图片放在 `Assets.xcassets`。

### 2. 在 `AppDelegate.swift` 调用

```swift
import react_native_splash_screen
```

在 React Native 启动后调用：

```swift
RNSplashScreen.show()
```

完整示例（`AppDelegate.swift`，RN 0.84 模板）：

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
      withModuleName: "你的应用名",
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

- `show()`：显示启动页（原生调用）
- `hide()`：隐藏启动页（JS 调用）

## 常见问题

### Android 12+ 为什么会先看到系统启动阶段？

Android 12+ 存在系统级启动阶段，这是平台行为。本库控制的是应用内可控的启动页显示/隐藏。


## Legacy 说明

`v3.x` 文档和历史接入方式请参考 [README.zh-v3.md](./README.zh-v3.md)。

---

MIT License
