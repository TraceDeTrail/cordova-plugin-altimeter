/********* Altimeter.js Cordova Plugin Implementation *******/


var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isAltimeterAvailable = function (onSuccess, onError) {
  cordova.exec(onSuccess, onError, "altimeter", "isAltimeterAvailable", []);
};

Altimeter.prototype.startAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "altimeter", "startAltimeterUpdates", []);
};

Altimeter.prototype.stopAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "altimeter", "stopAltimeterUpdates", []);
};

module.exports = new Altimeter();
