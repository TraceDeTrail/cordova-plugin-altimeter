/********* Altimeter.js Cordova Plugin Implementation *******/

var exec = require("cordova/exec");

var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isRelativeAltitudeAvailable = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "isRelativeAltitudeAvailable", []);
};

Altimeter.prototype.startRelativeAltitudeUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "startRelativeAltitudeUpdates", []);
};

Altimeter.prototype.stopRelativeAltitudeUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "stopRelativeAltitudeUpdates", []);
};

Altimeter.prototype.queryData = function (onSuccess, onError, options) {
    exec(onSuccess, onError, "Altimeter", "queryData", [options]);
};

module.exports = new Altimeter();