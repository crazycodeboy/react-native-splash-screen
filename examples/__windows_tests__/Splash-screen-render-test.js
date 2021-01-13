import { driver, By2 } from 'selenium-appium';
import { until } from 'selenium-webdriver';
import { PNG } from 'pngjs';
import pixelmatch from 'pixelmatch';

const setup = require('../jest-windows/driver.setup');
jest.setTimeout(60000);

function pngFromBase64(base64) {
  const pngBuffer = Buffer.from(base64, 'base64');
  return PNG.sync.read(pngBuffer);
};

const pixelThreshold = 30; // Allow 30 pixel difference, to account for minor differences.
function pixelDiffPNGs(img1, img2) {
  return pixelmatch(img1.data, img2.data, null, img1.width, img1.height);
}

beforeAll(() => {
  return driver.startWithCapabilities(setup.capabilites);
});

afterAll(() => {
  return driver.quit();
});

describe('Control Renders', () => {

  test('Renders Splash Screen', async () => {
    await driver.wait(until.elementLocated(By2.nativeAccessibilityId('SplashImage')));
    
    // Set window to specific size, so that screenshots are not too flaky.
    await driver.manage().window().setRect({x:0, y:0, width:512, height:512});

    await(driver.sleep(5000)); // Expect 5s to be enough for the app to load.
  });

  test('Shows Splash Screen again', async() => {
    let screenshot_before_splash_screen = pngFromBase64(await driver.takeScreenshot());

    By2.nativeAccessibilityId('ShowSplashScreenButton').click();

    await(driver.sleep(1000)); // 1s should be enough for splash screen to appear.
    let screenshot_during_splash_screen = pngFromBase64(await driver.takeScreenshot());

    // Screen showing splash screen should be different than screen without splash screen.
    expect(pixelDiffPNGs(screenshot_before_splash_screen, screenshot_during_splash_screen)).toBeGreaterThanOrEqual(pixelThreshold);

    await(driver.sleep(5000)); // 5s should be enough for splash screen to disappear.
    let screenshot_after_splash_screen = pngFromBase64(await driver.takeScreenshot());
    
    // Screen after splash screen disappears should be equal to screen before splash screen appears.
    expect(pixelDiffPNGs(screenshot_before_splash_screen, screenshot_after_splash_screen)).toBeLessThanOrEqual(pixelThreshold);

  })

});
