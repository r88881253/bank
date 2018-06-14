package com.ibm.esun.esunmobilebank.model;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpTask extends AsyncTask<String, Void, ResposeEntity> {

    public final static int STATUS_OK = 200;

    private final String TAG = HttpTask.class.getCanonicalName();

    public interface ICallback {
        public void onHttpResult(int statusCode, String jsonData);
    }

    private ICallback mCallback;

    public void setCallback(ICallback callback) {
        mCallback = callback;
    }

    @Override
    protected ResposeEntity doInBackground(String... aParams) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(aParams[0])
                .build();

        Response response = null;
        ResposeEntity result = null;
        try {
            response = okHttpClient.newCall(request).execute();
            result = new ResposeEntity(Integer.valueOf(response.code()), response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(ResposeEntity s) {
        super.onPostExecute(s);
        Log.d(TAG, "RESP = " + s);
        
        String body = "";
        int statusCode = 0;
        try {
            body = s.getBody();
            statusCode = s.getStatusCode();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if(mCallback != null) {
            mCallback.onHttpResult(statusCode, body);
        }
    }

}

class ResposeEntity{
    protected int statusCode;
    protected String body;

    public ResposeEntity(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}