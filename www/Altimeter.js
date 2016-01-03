/********* Altimeter.js Cordova Plugin Implementation *******/

var exec = require("cordova/exec");

var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isRelativeAltitudeAvailable = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "isStepCountingAvailable", []);
};

Altimeter.prototype.startRelativeAltitudeUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "startAltimeterUpdates", []);
};

Altimeter.prototype.stopRelativeAltitudeUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "stopAltimeterUpdates", []);
};

Altimeter.prototype.queryData = function (onSuccess, onError, options) {
    exec(onSuccess, onError, "Altimeter", "queryData", [options]);
};

module.exports = new Altimeter();