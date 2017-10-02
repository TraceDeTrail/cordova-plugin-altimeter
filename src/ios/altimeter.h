/********* Altimeter.h Cordova Plugin Header *******/

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@interface altimeter : CDVPlugin

- (void) isAltimeterAvailable:(CDVInvokedUrlCommand*)command;

- (void) startAltimeterUpdates:(CDVInvokedUrlCommand*)command;
- (void) stopAltimeterUpdates:(CDVInvokedUrlCommand*)command;

@end
