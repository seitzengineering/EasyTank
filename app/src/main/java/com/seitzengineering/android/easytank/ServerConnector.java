package com.seitzengineering.android.easytank;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Kevin on 8/12/2017.
 */

public class ServerConnector extends AsyncTask <String, Void, String> {

    String TAG = "SeverConnector";

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL("http://72.198.125.136:4041/?" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String inputLine;
            while((inputLine = in.readLine()) != null)
                result.append(inputLine).append("00");
            in.close();
            connection.disconnect();

            if(params[0].equals("temp")) {
                char temp[] = result.toString().toCharArray();
                String theResult;
                theResult = "" + temp[0] + temp[1] + temp[2] + temp[3] + temp[4] + '°';
                return theResult;
            }
            else if(params[0].equals("loadpump1") || params[0].equals("loadpump2") || params[0].equals("loadpump3") || params[0].equals("loadpump4")) {
                char temp[] = result.toString().toCharArray();
                String theResult;
                theResult = "" + temp[0] + temp[1] + temp[2] + temp[3];
                return theResult;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

/*    @Override
    protected void onPostExecute(String result) {
        if(result != null) {
            try {
                char thing[] = result.toCharArray();
                result = "" + thing[0] + thing[1] + thing[2] + thing[3] + thing[4] + '°';
                //result = testResult;
            } catch(Exception e) {
                Log.d(TAG, "TEMP SENSOR ERROR");
            }
        }
    } */
}





