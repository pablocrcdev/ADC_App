package com.projects.crc.adc_app.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.onesignal.OneSignal;
import com.projects.crc.adc_app.R;
import com.projects.crc.adc_app.dialogs.DialogNetwork;
import com.projects.crc.adc_app.utils.AppNotificationOpenedHandler;
import com.projects.crc.adc_app.utils.AppNotificationReceivedHandler;
import com.projects.crc.adc_app.webview.ManagerChromeClient;
import com.projects.crc.adc_app.webview.ManagerWebClient;
import com.projects.crc.adc_app.webview.WebInterface;

public class MainActivity extends AppCompatActivity {

    private WebView gvWebView;
    public static final String gvURL = "https://www.adcarmelita.club/"; // pagina carmelita
    public static String ERROR_WEB = "WEB";
    public static String ERROR_RED = "RED";

    // Metodos de validacion
    //********************************************************************************************//
    // Validacion del estado del acceso a la web
    protected boolean validarEstadoRed() {
        ConnectivityManager vConnectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo vNetworkInfo = vConnectivityManager.getActiveNetworkInfo();
        if (vNetworkInfo != null && vNetworkInfo.isConnectedOrConnecting())
            return true;  // Si encuentra que hay conexion
        else
            return false; // De no encontrar conexion arroja falso
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se valida el estado de la red si esta tiene acceso a internet
        if(validarEstadoRed()) {
            // OneSignal Initialization
            // Inicializacion del servicio de OneSignal
            OneSignal.startInit(this)
                    .setNotificationOpenedHandler(new AppNotificationOpenedHandler())     // Clase para la apertura de notificaciones
                    .setNotificationReceivedHandler(new AppNotificationReceivedHandler()) // Clase para controlar la entrada de notificacion
                    .init();                                                              // Iniciar Servicio
            // Declaracion del elemento xml en la clase para configuraciones
            gvWebView = (WebView) findViewById(R.id.WebView);
            // Seteo de Cliente Web, para manejo de navegador interno
            gvWebView.setWebViewClient(new ManagerWebClient(this));

            // Habilitacion de Javascript en el webview
            gvWebView.getSettings().setJavaScriptEnabled(true);
            // Interfaz Javascript que ejecuta java en webview
            gvWebView.getSettings().setDomStorageEnabled(true);
            gvWebView.getSettings().setLoadsImagesAutomatically(true);
            // Carga de URL en el elemento Webview
            gvWebView.loadUrl(gvURL);
            // Seteo Default de Cliente de Google para el webview (Otras funcionalidades)
            gvWebView.setWebChromeClient(new ManagerChromeClient(this));
        } else {
            // indica que no hay red para accesar la pagina
            new DialogNetwork(this).showDialog(this, "No hay conexión de red.");
        }
    }
}
