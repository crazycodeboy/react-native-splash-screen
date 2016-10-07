# react-native-splash-screen

![China.png](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/examples/Screenshots/China.png)**[简体中文](https://github.com/crazycodeboy/react-native-splash-screen/blob/master/README.zh.md) | [原理解析](https://github.com/crazycodeboy/RNStudyNotes/blob/master/React%20Native%20%E9%97%AE%E9%A2%98%E5%8F%8A%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88%E5%90%88%E9%9B%86/React%20Native%20%E5%90%AF%E5%8A%A8%E7%99%BD%E5%B1%8F%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3%E6%95%99%E7%A8%8B/React%20Native%20%E5%90%AF%E5%8A%A8%E7%99%BD%E5%B1%8F%E9%97%AE%E9%A2%98%E8%A7%A3%E5%86%B3%E6%95%99%E7%A8%8B.md)**


A splash screen for react-native, hide when application loaded ,it works on iOS and Android.

## Content

- [Installation](#installation)
- [Demo](#demo)
- [Getting started](#getting-started)
- [API](#api)
- [Contribution](#contribution)

## Installation

### First step(Download):
Run `npm i react-native-splash-screen --save`

### Second step(Plugin Installation):

#### Automatic installation

`react-native link react-native-splash-screen` or `rnpm link react-native-splash-screen`

#### Manual installation  

**Android:**

1.In your android/settings.gradle file, make the following additions:
```
include ':react-native-splash-screen'   
project(':react-native-splash-screen').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-splash-screen/android')
```

2.In your android/app/build.gradle file, add the `:react-native-splash-screen` project as a compile-time dependency:

```
...
dependencies {
    ...
    compile project(':react-native-splash-screen')
}
```

3.Update the MainApplication.java file to use `react-native-splash-screen` via the following changes:   

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
            new SplashScreenReactPackage()  //here
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

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-splash-screen` and add `SplashScreen.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libSplashScreen.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`



### Third step(Plugin Configuration):

**Android:**

Update the MainActivity.java file to use `react-native-splash-screen` via the following changes:

```java
public class MainActivity extends ReactActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.show(this);  // here
        super.onCreate(savedInstanceState);
    }
    // ...other code
}
```

**iOS:**

You should add following code to AppDelegate.m for keeping launch image:


```obj-c

#import "AppDelegate.h"
#import "RCTRootView.h"
#import "SplashScreen.h"  // here

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // ...other code

    [SplashScreen show];  // here
    return YES;
}

@end

```

## Demo  
* [Examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)

![react-native-splash-screen-Android](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/examples/Screenshots/react-native-splash-screen-Android.gif)
![react-native-splash-screen-iOS](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/examples/Screenshots/react-native-splash-screen-iOS.gif)

## Getting started  

Import `react-native-splash-screen` in your JS file.

`import SplashScreen from 'react-native-splash-screen'`    

**Android:**

Add a file called launch_screen.xml in the layout as the splash screen.

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/launch_screen">
</LinearLayout>
```
**Learn more to see [examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)**


**iOS**   

iOS can be used to customize your startup screen via LaunchImage or LaunchScreen.xib.

**Learn more to see [examples](https://github.com/crazycodeboy/react-native-splash-screen/tree/master/examples)**

Then you can use it like this:

```JavaScript
import SplashScreen from 'react-native-splash-screen'

export default class WelcomePage extends Component {

    componentDidMount() {
    	 // do anything while splash screen keeps, use await to wait for an async task.
        SplashScreen.hide();
    }
}
```

## API


Method            | Type     | Optional | Description
----------------- | -------- | -------- | -----------
show()   | function | false | Open splash screen
hide() |  function  | false  |  Close splash screen     

## Contribution

Issues are welcome. Please add a screenshot of bug and code snippet. Quickest way to solve issue is to reproduce it on one of the examples.

Pull requests are welcome. If you want to change API or making something big better to create issue and discuss it first.

---

**MIT Licensed**
