package com.ibm.esun.esunmobilebank.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpTask extends AsyncTask<String, Void, String> {

    private final String TAG = HttpTask.class.getCanonicalName();

    public interface ICallback {
        public void onHttpResult(String jsonData);
    }

    private ICallback mCallback;

    public void setCallback(ICallback callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... aParams) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(aParams[0]);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while (line != null) {
                sb.append(line);
                line = in.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "RESP = " + s);
        if(mCallback != null) {
            mCallback.onHttpResult(s);
        }
    }
}