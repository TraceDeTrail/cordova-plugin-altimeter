# cordova-plugin-altimeter
Cordova Plugin for the Altimeter to gather altitude and barometric pressure related data.


## Altimeter Plugin for Cordova

**Fetch altimeter-related altitude data, such as relative altitude and barometric pressure.**

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

## Check feature support (iOS only)

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

## Platform and device support

- iOS 8+. These capabilities are not supported on all devices, even with iOS 8, so please ensure you use the *check feature support* functions.
- [TBD] Android (when associated hardware is available). To be implemented.

## License

[MIT License](http://ilee.mit-license.org)