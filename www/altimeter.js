/********* Altimeter.js Cordova Plugin Implementation *******/


var altimeter = function () {
    this.name = "altimeter";
};

altimeter.prototype.isAltimeterAvailable = function (onSuccess, onError) {
  cordova.exec(onSuccess, onError, "altimeter", "isAltimeterAvailable", []);
};

altimeter.prototype.startAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "altimeter", "startAltimeterUpdates", []);
};

altimeter.prototype.stopAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "altimeter", "stopAltimeterUpdates", []);
};

module.exports = new altimeter();
