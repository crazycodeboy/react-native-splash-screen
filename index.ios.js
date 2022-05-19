/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 * @flow
 */
'use strict'

import { NativeModules } from 'react-native'

const { SplashScreen } = NativeModules

const Wrapper = {
  ...SplashScreen,
  // no opts supported
  show: () => SplashScreen.show(),
  hide: () => SplashScreen.hide(),
  showVideo: () => SplashScreen.showVideo(),
  hideVideo: () => SplashScreen.hideVideo(),
}

export default Wrapper
