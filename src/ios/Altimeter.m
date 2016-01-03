/********* Altimeter.m Cordova Plugin Implementation *******/

#import "Cordova/CDV.h"
#import "Cordova/CDVViewController.h"
#import "CoreMotion/CoreMotion.h"
#import "Altimeter.h"

@interface Altimeter ()
    @property (nonatomic, strong) CMAltimeter *altimeter;
@end

@implementation Altimeter

- (CMAltimeter*) altimeter {
    if (_altimeter == nil) {
        _altimeter = [[CMAltimeter alloc] init];
    }
    return _altimeter;
}

- (void) isRelativeAltitudeAvailable:(CDVInvokedUrlCommand*)command;
{
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:[CMAltimeter isRelativeAltitudeAvailable]];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}