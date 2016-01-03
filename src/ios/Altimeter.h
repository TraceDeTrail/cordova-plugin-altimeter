/********* Altimeter.h Cordova Plugin Header *******/

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@interface Altimeter : CDVPlugin

- (void) isRelativeAltitudeAvailable:(CDVInvokedUrlCommand*)command;

/*
- (void) startRelativeAltitudeUpdates:(CDVInvokedUrlCommand*)command;
- (void) stopRelativeAltitudeUpdates:(CDVInvokedUrlCommand*)command;

- (void) queryData:(CDVInvokedUrlCommand*)command;
*/
@end