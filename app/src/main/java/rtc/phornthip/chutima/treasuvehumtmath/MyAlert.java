package rtc.phornthip.chutima.treasuvehumtmath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by masterUNG on 1/17/2017 AD.
 */

public class MyAlert {

    private Context context;
    private String titleString, messageString;
    private Class<?> aClass;

    public MyAlert(Context context, String titleString, String messageString, Class<?> aClass) {
        this.context = context;
        this.titleString = titleString;
        this.messageString = messageString;
        this.aClass = aClass;
    }

    public void myDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.key11);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, aClass);
                context.startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}   // Main Class
