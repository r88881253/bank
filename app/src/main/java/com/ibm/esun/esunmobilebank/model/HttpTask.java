package com.ibm.esun.esunmobilebank.model;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpTask extends AsyncTask<String, Void, Response> {

    private final String TAG = HttpTask.class.getCanonicalName();

    public interface ICallback {
        public void onHttpResult(int statusCode, String jsonData);
    }

    private ICallback mCallback;

    public void setCallback(ICallback callback) {
        mCallback = callback;
    }

    @Override
    protected Response doInBackground(String... aParams) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

        Request request = new Request.Builder()
                .url(aParams[0]+"1")
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(Response s) {
        super.onPostExecute(s);
        Log.d(TAG, "RESP = " + s);
        
        String body = "";
        try {
            body = s.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mCallback != null) {
            mCallback.onHttpResult(Integer.valueOf(s.code()), body);
        }
    }
}