package com.projects.crc.adc_app.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.projects.crc.adc_app.R;

/**
 * Created by Jose Pablo on 7/6/2018.
 */

public class DialogWarning extends Dialog {
    private Context gvContext;
    public DialogWarning(Context context, String s) {
        super(context);
        gvContext = context;
    }
    public void showDialog(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.webview_error);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog_err);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vintent = new Intent(gvContext, activity.getClass());
                gvContext.startActivity(vintent);
            }
        });

        dialog.show();

    }
}
