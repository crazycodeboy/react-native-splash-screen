import { windowsAppDriverCapabilities } from 'selenium-appium'

switch (platform) {
  case "windows":
    const webViewWindowsAppId = 'RNSplashScreenExample_tzd3rs38zxb7w!App';
    module.exports = {
      capabilites: windowsAppDriverCapabilities(webViewWindowsAppId)
    }
    break;
  default:
    throw "Unknown platform: " + platform;
}
