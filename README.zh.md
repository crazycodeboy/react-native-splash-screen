# react-native-splash-screen

**[English](https://github.com/crazycodeboy/react-native-splash-screen) | [原理解析](https://github.com/crazycodeboy/RNStudyNotes/blob/master/React%20Native%20%E9%97%AE%E9%A2%98%E5%8F%8A%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88%E5%90%88%E9%9B%86/React%20Native%20%E5%90%AF%E5%8A%A8%E7%99%BD%E5%B1%8F%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3%E6%95%99%E7%A8%8B/React%20Native%20%E5%90%AF%E5%8A%A8%E7%99%BD%E5%B1%8F%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3%E6%95%99%E7%A8%8B.md)**

React Native启动屏，解决iOS，Android启动白屏问题，支持Android和iOS。

## 目录

- [安装说明](#安装说明)
- [演示](#演示)
- [使用说明](#使用说明)
- [API](#api)
- [贡献](#贡献)

## 安装说明

### 第一步(下载):
在项目根目录打开终端运行 `npm i react-native-splash-screen --save`

### 第二步 (安装):

大家可以通过自动或手动两种方式来安装`react-native-splash-screen`。


#### 自动安装

终端运行：


`react-native link react-native-splash-screen` 或 `rnpm link react-native-splash-screen`

####  手动安装

**Android:**

1.在你的 android/settings.gradle 文件中添加下列代码:
```
include ':react-native-splash-screen'   
project(':react-native-splash-screen').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-splash-screen/android')
```

2.在你的 android/app/build.gradle 文件中添加 `:react-native-splash-screen`：

代码如下：

```
...
dependencies {
    ...
    compile project(':react-native-splash-screen')
}
```

3.更新你的MainApplication.java 文件，如下:   

```java
public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        protected boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
            new SplashScreenReactPackage()  //添加这一句
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
```

**iOS:**

1. 在 XCode的项目导航视图中单击 `Libraries` ➜ `Add Files to [your project's name]`
2. 将 `SplashScreen.xcodeproj`添加到你的项目中,`node_modules` ➜ `react-native-splash-screen`  ➜ `SplashScreen.xcodeproj`

3.  在XCode中打开`Build Phases` ➜ `Link Binary With Libraries`将`libSplashScreen.a` 添加到你的项目中。



### 第三步(配置):

**Android:**

更新你的 MainActivity.java 文件如下：
```java
public class MainActivity extends ReactActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.show(this);  // 添加这一句
        super.onCreate(savedInstanceState);
    }
    // ...other code
}
```

**iOS:**

更新你的AppDelegate.m 文件如下：


```obj-c

#import "AppDelegate.h"
#import "RCTRootView.h"
#import "SplashScreen.h"  // here

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // ...other code

    [SplashScreen show];  // 添加这一句，这一句一定要在最后
    return YES;
}

@end

```

## 演示  
* [Examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)

![react-native-splash-screen-Android](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/examples/Screenshots/react-native-splash-screen-Android.gif)
![react-native-splash-screen-iOS](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/examples/Screenshots/react-native-splash-screen-iOS.gif)

## 使用说明  

将 `react-native-splash-screen` 导入你的JS 文件。


`import SplashScreen from 'react-native-splash-screen'`    

**Android:**

创建一个名为 launch_screen.xml 的布局文件来自定义你的启动屏幕。

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/launch_screen">
</LinearLayout>
```

**更详细的介绍，可以查看 [examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)**

**iOS**  

iOS可以通过LaunchImage或LaunchScreen.xib来自定义你的启动屏幕。

**更详细的介绍，可以查看 [examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)**

最后，你可以在适当的时候关闭启动屏幕（如：启动初始化完成后）:

```JavaScript
import SplashScreen from 'react-native-splash-screen'

export default class WelcomePage extends Component {

    componentDidMount() {
    	 // do anything while splash screen keeps, use await to wait for an async task.
        SplashScreen.hide();//关闭启动屏幕
    }
}
```

## API


方法            | 类型     | 可选 | 描述
----------------- | -------- | -------- | -----------
show()   | function | false | 打开启动屏幕
hide() |  function  | false  |  关闭启动屏幕   

## 贡献

欢迎大家提问题，如果能给问题加上一个截图，则是极好的。另外欢迎`Pull requests`贡献你的代码。

---

**MIT Licensed**
