/********* Altimeter.js Cordova Plugin Implementation *******/


var Altimeter = function () {
    this.name = "Altimeter";
};

Altimeter.prototype.isAltimeterAvailable = function (onSuccess, onError) {
    cordova.exec(
      function(rep){
        alert('test');
      }, function(){
        alert('erreur');
      }, "Altimeter", "isAltimeterAvailable", []);
};

Altimeter.prototype.startAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "Altimeter", "startAltimeterUpdates", []);
};

Altimeter.prototype.stopAltimeterUpdates = function (onSuccess, onError) {
    cordova.exec(onSuccess, onError, "Altimeter", "stopAltimeterUpdates", []);
};

module.exports = new Altimeter();
