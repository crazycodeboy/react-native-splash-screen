/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */

const path = require("path");
const blacklist = require("metro-config/src/defaults/blacklist");

const reactNativeSplachScreenRoot = path.resolve(__dirname, "..");

module.exports = {
  projectRoot: path.resolve(__dirname, "."),

  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false
      }
    })
  },
  watchFolders: [
    path.resolve(__dirname, "node_modules"),
    reactNativeSplachScreenRoot
  ],
  resolver: {
    blacklistRE: blacklist([
      new RegExp(`${reactNativeSplachScreenRoot}/node_modules/react-native/.*`)
    ])
  }
};
