package com.projects.crc.adc_app.utils;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

import java.math.BigInteger;

public class ExtensionServicioNotificacion extends NotificationExtenderService{
    // Maneja el procesamiento de la notificacion al llegar al dispositivo
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        OverrideSettings overrideSettings = new NotificationExtenderService.OverrideSettings();
        overrideSettings.extender = new NotificationCompat.Extender() {
            @Override
            public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                return builder.setColor(new BigInteger("FF00FF00", 16).intValue());
            }
        };
        OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
        Log.d("OneSignal", "Notificacion con el id: "+ displayedResult.androidNotificationId);

        return true;
    }
}
