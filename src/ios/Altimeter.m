/********* Altimeter.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import <Cordova/CDVViewController.h>
#import <CoreMotion/CoreMotion.h>
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

- (void) isAltimeterAvailable:(CDVInvokedUrlCommand*)command;
{
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:[CMAltimeter isRelativeAltitudeAvailable]];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void) startAltimeterUpdates:(CDVInvokedUrlCommand*)command;
{
    __block CDVPluginResult* pluginResult = nil;

    [self.altimeter startRelativeAltitudeUpdatesToQueue:[NSOperationQueue mainQueue] withHandler:^(CMAltitudeData *altitudeData, NSError *error) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if (error)
            {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[error localizedDescription]];
            }
            else
            {
                NSDictionary* altitudeDictionary = @{
                   @"pressure": [CMAltimeter isRelativeAltitudeAvailable] && altitudeData.pressure ? altitudeData.pressure : [NSNumber numberWithInt:0],
                   @"relativeAltitude": [CMAltimeter isRelativeAltitudeAvailable] && altitudeData.relativeAltitude ? altitudeData.relativeAltitude : [NSNumber numberWithInt:0]
                };
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:altitudeDictionary];
                [pluginResult setKeepCallbackAsBool:true];
            }

            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        });
    }];
}

- (void) stopAltimeterUpdates:(CDVInvokedUrlCommand*)command;
{
    [self.altimeter stopRelativeAltitudeUpdates];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end