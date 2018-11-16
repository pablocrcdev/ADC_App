package com.projects.crc.adc_app.utils;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class AppNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    //Metodo que recibe la informacion de la notificacion
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey;
        if(data != null){
            customKey = data.optString("customkey",null);
            if (customKey != null)
                Log.i("OneSignalExample","customkey set with value: "+customKey);
        }
    }
}
