# [module name here] Windows Implementation

## Module Installation
You can either use autolinking on react-native-windows 0.63 and later or manually link the module on earlier releases.

## Automatic install with autolinking on RNW
RNSplashScreen supports autolinking. Just call: `yarn add react-native-splash-screen`

## Manual installation on RNW
1. `yarn add react-native-splash-screen`
2. Open your solution in Visual Studio 2019 (eg. `windows\yourapp.sln`)
3. Right-click Solution icon in Solution Explorer > Add > Existing Project...
4. Add `node_modules\[module name here]\windows\RNSplashScreen\RNSplashScreen.vcxproj`
5. Right-click main application project > Add > Reference...
6. Select `RNSplashScreen` in Solution Projects
7. In app `pch.h` add `#include "winrt/RNSplashScreen.h"`
8. In `App.cpp` add `PackageProviders().Append(winrt::RNSplashScreen::ReactPackageProvider());` before `InitializeComponent();`

## Windows splash screen visual element

On Windows, `react-native-splash-screen` will use an element in the visual tree to show the splash screen, a XAML `RNSplashScreen::RNSplashScreenControl`.

To add this element, follow the following steps:

1. In the application's `pch.h` file, add `#include "winrt/RNSplashScreen.h"` if you haven't already.

2. In the application's main XAML file, add a `RNSplashScreen::RNSplashScreenControl` element right beneath the `react:ReactRootView` element.

As an example, in `windows/myapp/MainPage.xaml` from the `react-native-windows` app template this can be done by adding a XAML `Grid` with a `RNSplashScreen::RNSplashScreenControl` alongside the `ReactRootView`. Change `windows/myapp/MainPage.xaml` from:
```xaml
<Page
  ...
  >
  <react:ReactRootView
    x:Name="ReactRootView"
    ...
  />
</Page>
```
to
```xaml
<Page
  ...
  xmlns:rnsplashscreen="using:RNSplashScreen"
  >
  <Grid>
    <react:ReactRootView
      x:Name="ReactRootView"
      ...
    />
    <rnsplashscreen:RNSplashScreenControl/>
  </Grid>
</Page>
```

3. `react-native-splash-screen` will use the splash screen image and background color defined in the application's `Package.appxmanifest` file.

Open `windows/myapp/Package.appxmanifest` in Visual Studio, open the `Visual Assets` tab and the `Splash Screen` horizontal tab. Add a Splash Screen image and define a background color.

## Module development

If you want to contribute to this module Windows implementation, first you must install the [Windows Development Dependencies](https://aka.ms/rnw-deps).

You must temporarily install the `react-native-windows` package. Versions of `react-native-windows` and `react-native` must match, e.g. if the module uses `react-native@0.62`, install `yarn add react-native-windows@^0.62 --dev`.

Now, you will be able to open corresponding `RNSplashScreen...sln` file, e.g. `RNSplashScreen62.sln` for `react-native-windows@0.62`.