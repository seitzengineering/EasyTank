package com.seitzengineering.android.easytank;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by Kevin on 12/28/2016.
 */

public class FertilizerFragment extends Fragment {

    private Button mPump1;
    private Button mPump2;
    private Button mPump3;
    private Button mPump4;
    private float seekBarDivided;
    private TextView doseSize;
    private SeekBar seekBar;
    private static SharedPreferences prefs;
    private static final String Pump1Key = "pump1key";
    private static final String Pump2Key = "pump2key";
    private static final String Pump3Key = "pump3key";
    private static final String Pump4Key = "pump4key";
    private static final String TAG = "FertilizerFragment";

    public static FertilizerFragment newInstance() {
        return new FertilizerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = this.getActivity().getPreferences(Activity.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fertilizers, container, false);
        doseSize = (TextView) view.findViewById(R.id.dosingSize);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        float a = seekBar.getProgress();
        seekBarDivided = a / 10;
        doseSize.setText("Dose size: " + seekBarDivided + " mL");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float a = seekBar.getProgress();
                seekBarDivided = a / 10;
                doseSize.setText("Dose size: " + seekBarDivided + " mL");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mPump1 = (Button) view.findViewById(R.id.pump1);
        mPump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pump 1 clicked!");
                updateFertsUI(1);
                new ServerConnector().execute("pump1=" + seekBarDivided);
            }
        });
        mPump2 = (Button) view.findViewById(R.id.pump2);
        mPump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pump 2 clicked!");
                updateFertsUI(2);
                new ServerConnector().execute("pump2=" + seekBarDivided);
            }
        });
        mPump3 = (Button) view.findViewById(R.id.pump3);
        mPump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pump 3 clicked!");
                updateFertsUI(3);
                new ServerConnector().execute("pump3=" + seekBarDivided);
            }
        });
        mPump4 = (Button) view.findViewById(R.id.pump4);
        mPump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pump 4 clicked!");
                updateFertsUI(4);
                new ServerConnector().execute("pump4=" + seekBarDivided);
            }
        });

        return view;
    }

    private void updateFertsUI(int pump) {
        SharedPreferences.Editor editor = prefs.edit();
        float currentPumpDoses;
        switch(pump) {
            case 1: currentPumpDoses = prefs.getFloat(Pump1Key, 0);
                    currentPumpDoses = currentPumpDoses - (float) seekBar.getProgress() / 10;
                    editor.putFloat(Pump1Key, currentPumpDoses);
                    editor.apply();
                    break;
            case 2:
                    currentPumpDoses = prefs.getFloat(Pump2Key, 0);
                    currentPumpDoses = currentPumpDoses - (float) seekBar.getProgress() / 10;
                    editor.putFloat(Pump2Key, currentPumpDoses);
                    editor.apply();
                    break;
            case 3:
                    currentPumpDoses = prefs.getFloat(Pump3Key, 0);
                    currentPumpDoses = currentPumpDoses - (float) seekBar.getProgress() / 10;
                    editor.putFloat(Pump3Key, currentPumpDoses);
                    editor.apply();
                    break;
            case 4:
                    currentPumpDoses = prefs.getFloat(Pump4Key, 0);
                    currentPumpDoses = currentPumpDoses - (float) seekBar.getProgress() / 10;
                    editor.putFloat(Pump4Key, currentPumpDoses);
                    editor.apply();
                    break;
        }
    }

/*    private class ServerConnector extends AsyncTask<String, Void, String> {

        String TAG = "ServerConnector";

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://72.198.125.136:4041/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            fromServer = result;
        }
    } */
}