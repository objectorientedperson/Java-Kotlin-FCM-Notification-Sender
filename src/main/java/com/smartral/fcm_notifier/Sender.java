package com.smartral.fcm_notifier;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by @author ali on 8/9/19
 */
public class Sender {

    private String mServerKey;

    public void setServerKey(String mServerKey) {
        this.mServerKey = mServerKey;
    }

    public void send(Message mPayload, ResultCallback callback) {
        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + mServerKey);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(new Gson().toJson(mPayload).getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            callback.onResult(resp);
        } catch (Exception e) {
            callback.onResult(e.getLocalizedMessage());
        }
    }

    public interface ResultCallback {
        void onResult(String message);
    }
}
