package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Calendar;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.R;

public class Utils {

    private static AlertDialog dialog;

    public static void showDialog(Context context) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setMessage(context.getString(R.string.message_loading));
        dialog.show();
    }

    public static void tamañoDialogo(Dialog dialog) {
        ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public static void hideNotificationBar(Activity activity){
        if (Build.VERSION.SDK_INT > 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public static String diaSemana (int dia, int mes, int ano) {
        String letraD = "";
        int nD = -1;
        Calendar c = Calendar.getInstance();

        c.set(ano, mes, dia);
        nD = c.get(Calendar.DAY_OF_WEEK);
        switch (nD) {
            case 1:
                letraD = "Lunes";
                break;
            case 2:
                letraD = "Martes";
                break;
            case 3:
                letraD = "Miercoles";
                break;
            case 4:
                letraD = "Jueves";
                break;
            case 5:
                letraD = "Viernes";
                break;
            case 6:
                letraD = "Sabado";
                break;
            case 7:
                letraD = "Domingo";
                break;
        }

        return letraD;

    }

}
