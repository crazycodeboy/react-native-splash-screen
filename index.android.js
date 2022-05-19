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
  show: (opts = {}) => SplashScreen.show(opts),
  hide: (opts = {}) => SplashScreen.hide(opts),
  showVideo: (opts = {}) => SplashScreen.showVideo(opts),
  hideVideo: () => SplashScreen.hideVideo(),
  resumeVideo: () => SplashScreen.resumeVideo(),
  removeVideoPauseOption: () => SplashScreen.removeVideoPauseOption(),
}

export default Wrapper
