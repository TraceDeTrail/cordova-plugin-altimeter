# cordova-plugin-altimeter
Cordova Plugin for the Altimeter to gather altitude and barometric pressure related data. [![npm version](https://badge.fury.io/js/cordova-plugin-altimeter.svg)](http://badge.fury.io/js/cordova-plugin-altimeter) [![bitHound Overall Score](https://www.bithound.io/github/jameslegue/cordova-plugin-altimeter/badges/score.svg)](https://www.bithound.io/github/jameslegue/cordova-plugin-altimeter)


## Altimeter Plugin for Cordova

**Fetch altimeter-related altitude data, such as relative altitude and barometric pressure.**


## Platform and device support

- iOS (8+), but not supported on all hardware, so use the isAltimeterAvailable method to verify presence of an altimeter before using.
- Android: To be implemented.

## Install

#### Latest published version on npm (with Cordova CLI >= 5.0.0)

```
cordova plugin add cordova-plugin-altimeter
```

#### Latest version from GitHub

```
cordova plugin add https://github.com/jameslegue/cordova-plugin-altimeter.git
```

You **do not** need to reference any JavaScript, the Cordova plugin architecture will add an altimeter object to your root automatically when you build.

## Check feature support

### isAltimeterAvailable

```js
altimeter.isAltimeterAvailable(successCallback, failureCallback);
```
- => `successCallback` is called with true if the feature is supported, otherwise false
- => `failureCallback` is called if there was an error determining if the feature is supported

## Live altimeter data

### startAltimeterUpdates

Starts the delivery of altimeter updates to your Cordova app.

```js
var successHandler = function (altimeterData) {
    // altimeterData.pressure; -> in kilopascals
    // altimeterData.relativeAltitude; -> starts at 0 and then reports delta on successive updates
};
altimeter.startAltimeterUpdates(successHandler, onError);
```

The success handler is executed when data is available and is called repeatedly from a background thread as new data arrives.

When the app is suspended, the delivery of updates stops temporarily. Upon returning to foreground or background execution, the altimeter object begins updates again.

### stopAltimeterUpdates

Stops the delivery of altimeter updates to your Cordova app.

```js
altimeter.stopAltimeterUpdates(successCallback, failureCallback);
```

## License

[MIT License](http://ilee.mit-license.org)