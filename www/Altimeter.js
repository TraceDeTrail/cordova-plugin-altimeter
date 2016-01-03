/********* Altimeter.js Cordova Plugin Implementation *******/

var exec = require("cordova/exec");

var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isAltimeterAvailable = function (onSuccess, onError) {
	console.log('plugin: isAltimeterAvailable');
    exec(onSuccess, onError, "Altimeter", "isAltimeterAvailable", []);
};

Altimeter.prototype.startAltimeterUpdates = function (onSuccess, onError) {
	console.log('plugin: startAltimeterUpdates');
    exec(onSuccess, onError, "Altimeter", "startAltimeterUpdates", []);
};

Altimeter.prototype.stopAltimeterUpdates = function (onSuccess, onError) {
	console.log('plugin: stopAltimeterUpdates');
    exec(onSuccess, onError, "Altimeter", "stopAltimeterUpdates", []);
};

module.exports = new Altimeter();