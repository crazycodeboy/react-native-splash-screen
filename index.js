/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 * @flow
 */
'use strict';

import { NativeModules } from 'react-native';
import NativeSplashScreen from './src/specs/NativeSplashScreen';

export default NativeSplashScreen ?? NativeModules.SplashScreen;
