在新版Xcode 中 而LaunchImage已经退出了历史的舞台，要为iOS APP添加启动屏可以通过LaunchScreen.storyboard 或 LaunchScreen.xib两种方式，两种方式思路相同，接下来就介绍下如何通过LaunchScreen.storyboard 来为RN应用添加启动屏。

## 步骤

1. 创建LaunchScreen.storyboard
2. 创建LaunchScreen Image Set
3. 在LaunchScreen.storyboard中添加ImageView并绑定LaunchScreen Image
4. 应用LaunchScreen.storyboard
5. 删除APP，重新运行

### 创建LaunchScreen.storyboard

RN创建的项目默认是不带LaunchScreen.storyboard的，所以我们需要手动创建，用xcode打开项目下的iOS项目然后在左侧文件导航面板右键选择新建文件：

![new-LaunchScreen-storyboard.jpg](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/screenshot/new-LaunchScreen-storyboard.jpg)


### 创建LaunchScreen Image Set

打开`Images.xcassets`然后添加名为`LaunchScreen`的Image Set：

![new-LaunchScreen-image-set.jpg](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/screenshot/new-LaunchScreen-image-set.jpg)

### 在LaunchScreen.storyboard中添加ImageView并绑定LaunchScreen Image

打开`LaunchScreen.storyboard`，然后添加一个ImageView，调整好大小与约束，在为其绑定LaunchScreen Image Set：

![apply-image-set.jpg](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/screenshot/apply-image-set.jpg)

### 应用LaunchScreen.storyboard

然后不要忘记在TARGETS中设置`Launch Screen File`：

![apply-Launchscreen.jpg](https://raw.githubusercontent.com/crazycodeboy/react-native-splash-screen/master/screenshot/apply-Launchscreen.jpg)