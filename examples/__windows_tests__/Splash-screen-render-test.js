import { driver, By2 } from 'selenium-appium';
import { until } from 'selenium-webdriver';

const setup = require('../jest-windows/driver.setup');
jest.setTimeout(60000);

beforeAll(() => {
  return driver.startWithCapabilities(setup.capabilites);
});

afterAll(() => {
  return driver.quit();
});

describe('Control Renders', () => {

  test('Renders Splash Screen', async () => {
    await driver.wait(until.elementLocated(By2.nativeAccessibilityId('SplashImage')));
  });

});
