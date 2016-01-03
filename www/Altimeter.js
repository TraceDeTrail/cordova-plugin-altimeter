/********* Altimeter.js Cordova Plugin Implementation *******/

var exec = require("cordova/exec");

var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isAltimeterAvailable = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "isAltimeterAvailable", []);
};

Altimeter.prototype.startAltimeterUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "startAltimeterUpdates", []);
};

Altimeter.prototype.stopAltimeterUpdates = function (onSuccess, onError) {
    exec(onSuccess, onError, "Altimeter", "stopAltimeterUpdates", []);
};

Altimeter.prototype.queryAltimeterData = function (onSuccess, onError, options) {
    exec(onSuccess, onError, "Altimeter", "queryAltimeterData", [options]);
};

module.exports = new Altimeter();