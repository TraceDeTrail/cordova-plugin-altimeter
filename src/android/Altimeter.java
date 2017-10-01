// Copyright (c) 2014, the Dart project authors.  Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.

package com.tracedetrail.cordova.altimeter;

import java.util.List;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Handler;
import android.os.Looper;

/**
 * This class listens to the barometer sensor and stores the latest
 * pressure value.
 */
public class Altimeter extends CordovaPlugin implements SensorEventListener {

    private float pressure;  // most recent pressure value
    private float relativeAltitude;  // most recent pressure value
    private float firstAltitude;
    private int accuracy = SensorManager.SENSOR_STATUS_UNRELIABLE;

    private SensorManager sensorManager;  // Sensor manager
    private Sensor mSensor;  // Pressure sensor returned by sensor manager

    private CallbackContext callbackContext;  // Keeps track of the JS callback context.

    /**
     * Create an barometer listener.
     */
    public Altimeter() {
        this.pressure = 0;
        this.relativeAltitude = 0;
        this.firstAltitude = 100000;
     }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The associated CordovaWebView.
     */
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.sensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Executes the request.
     *
     * @param action        The action to execute.
     * @param args          The exec() arguments.
     * @param callbackId    The callback id used when calling back into JavaScript.
     * @return              Whether the action was valid.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if(action.equals("isAltimeterAvailable")){
          // List<Sensor> list = this.sensorManager.getSensorList(Sensor.TYPE_PRESSURE);
          // if ((list != null) && (list.size() > 0)) {
             PluginResult result = new PluginResult(PluginResult.Status.OK, true);
             result.setKeepCallback(true);
             callbackContext.sendPluginResult(result);
          // }
          // else {
          //   PluginResult result = new PluginResult(PluginResult.Status.OK, false);
          //   result.setKeepCallback(true);
          //   callbackContext.sendPluginResult(result);
          // }
          return true;
        }
        else if(action.equals("stopAltimeterUpdates")){
            this.stop();
            PluginResult result = new PluginResult(PluginResult.Status.OK, "");
            result.setKeepCallback(true);
            callbackContext.sendPluginResult(result);
        }
        else if(action.equals("startAltimeterUpdates")){
            this.start();
        }
        return true;
    }

    /**
     * Called by BarometerBroker when listener is to be shut down.
     * Stop listener.
     */
    public void onDestroy() {
        this.stop();
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
    //
    /**
     * Start listening for pressure sensor.
     *
    */
    private void start() {

        List<Sensor> list = this.sensorManager.getSensorList(Sensor.TYPE_PRESSURE);
        this.mSensor = list.get(0);
        this.sensorManager.registerListener(this, this.mSensor, SensorManager.SENSOR_DELAY_UI);

    }

    /**
     * Stop listening to barometer sensor.
     */
    private void stop() {
        this.sensorManager.unregisterListener(this);
        this.accuracy = SensorManager.SENSOR_STATUS_UNRELIABLE;
        this.pressure = 0;
        this.relativeAltitude = 0;
        this.firstAltitude = 100000;
    }

    /**
     * Called when the accuracy of the sensor has changed.
     *
     * @param sensor
     * @param accuracy
     */
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Only look at barometer events
        if (sensor.getType() != Sensor.TYPE_PRESSURE) {
            return;
        }

        this.accuracy = accuracy;
    }

    /**
     * Sensor listener event.
     *
     * @param SensorEvent event
     */
    public void onSensorChanged(SensorEvent event) {
        // Only look at barometer events
        if (event.sensor.getType() != Sensor.TYPE_PRESSURE) {
            return;
        }

        if (this.accuracy >= SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM) {

            // Save time that event was received
            this.pressure = event.values[0];
            if(this.firstAltitude == 100000){
              this.firstAltitude=this.sensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, this.pressure);
              this.relativeAltitude=0;
            }
            else{
              this.relativeAltitude = this.sensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, this.pressure) - this.firstAltitude;
            }
            this.win();
        }
    }

    /**
     * Called when the view navigates.
     */
    @Override
    public void onReset() {
        this.stop();
    }

    // Sends an error back to JS
    private void fail(int code, String message) {
        // Error object
        JSONObject errorObj = new JSONObject();
        try {
            errorObj.put("code", code);
            errorObj.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult err = new PluginResult(PluginResult.Status.ERROR, errorObj);
        err.setKeepCallback(true);
        callbackContext.sendPluginResult(err);
    }

    private void win() {
        // Success return object
        PluginResult result = new PluginResult(PluginResult.Status.OK, this.getPressureJSON());
        result.setKeepCallback(true);
        callbackContext.sendPluginResult(result);
    }


    private JSONObject getPressureJSON() {
        JSONObject r = new JSONObject();
        try {
            r.put("pressure", this.pressure);
            r.put("relativeAltitude", this.relativeAltitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }
}
