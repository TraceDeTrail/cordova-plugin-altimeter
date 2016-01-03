# cordova-plugin-altimeter
Cordova Plugin for the Altimeter to gather altitude and barometric pressure related data.


## Altimeter Plugin for Cordova [![npm version](https://badge.fury.io/js/cordova-plugin-pedometer.svg)](http://badge.fury.io/js/cordova-plugin-pedometer)

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

### isRelativeAltitudeAvailable

```js
altimeter.isStepCountingAvailable(successCallback, failureCallback);
```
- => `successCallback` is called with true if the feature is supported, otherwise false
- => `failureCallback` is called if there was an error determining if the feature is supported

## Live altimeter data

### startRelativeAltitudeUpdates

Starts the delivery of relative altitude updates to your Cordova app.

```js
var successHandler = function (altimeterData) {
    // altimeterData.startDate; -> ms since 1970
    // altimeterData.endDate; -> ms since 1970
    // altimeterData.numberOfSteps;
    // altimeterData.distance;
    // altimeterData.floorsAscended;
    // altimeterData.floorsDescended;
};
altimeter.startRelativeAltitudeUpdates(successHandler, onError);
```

The success handler is executed when data is available and is called repeatedly from a background thread as new data arrives.

When the app is suspended, the delivery of updates stops temporarily. Upon returning to foreground or background execution, the pedometer object begins updates again.

### stopRelativeAltitudeUpdates

Stops the delivery of relative altitude updates to your Cordova app.

```js
altimeter.stopRelativeAltitudeUpdates(successCallback, failureCallback);
```

## Historical altimeter data (iOS only)

### queryData

Retrieves the data between the specified start and end dates.

The `startDate` and `endDate` options are required and can be constructed in any valid JavaScript way (e.g. `new Date(2015, 4, 1, 15, 20, 00)` is also valid, as is milliseconds).

```js
var successHandler = function (altimeterData) {
    // altimeterData.numberOfSteps;
    // altimeterData.distance;
    // altimeterData.floorsAscended;
    // altimeterData.floorsDescended;
};
var options = {
    "startDate": new Date("Fri May 01 2015 15:20:00"),
    "endDate": new Date("Fri May 01 2015 15:25:00")
};
altimeter.queryData(successHandler, onError, options);
```

## Platform and device support

- iOS 8+. These capabilities are not supported on all devices, even with iOS 8, so please ensure you use the *check feature support* functions.
- Android (when associated hardware is available). Only live pedometer data is supported.

## License

[MIT License](http://ilee.mit-license.org)