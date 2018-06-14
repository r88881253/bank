package com.ibm.esun.esunmobilebank.common;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

public class Util {
    //確認wifi有沒有連線
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showNetworkErrorAlert(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("玉山銀行")
                .setMessage("請確認網路狀況後，重新連線");
        // Add the buttons
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }});
        builder.create().show();
    }
}
