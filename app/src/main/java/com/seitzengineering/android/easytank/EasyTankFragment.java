package com.seitzengineering.android.easytank;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by Kevin on 12/27/2016.
 */

//TODO implement UI for pumps showing amount of mL left
//TODO implement feature to reset mL for a given pump
//TODO fix the file system implementation in FertilizerFragment for mL and not for # of doses


public class EasyTankFragment extends Fragment {

    private ImageButton mFertilizersMenu;
    private ImageButton mScheduleMenu;
    private ImageButton mFeederMenu;
    private Callbacks mCallbacks;
    private TextView mTemperature;
    private static final String TAG ="EasyTankFragment";
    private static SharedPreferences prefs;
    private View pump1Meter;
    private View pump2Meter;
    private View pump3Meter;
    private View pump4Meter;
    private TextView pump1MeterText;
    private TextView pump2MeterText;
    private TextView pump3MeterText;
    private TextView pump4MeterText;
    private static final String Pump1Key = "pump1key";
    private static final String Pump2Key = "pump2key";
    private static final String Pump3Key = "pump3key";
    private static final String Pump4Key = "pump4key";
    private String tempResult = "";
    boolean loadFromServer = false;
    private static String pump1liquid = "0";
    private static String pump2liquid = "0";
    private static String pump3liquid = "0";
    private static String pump4liquid = "0";
    private static String tankTemp = "";
    //private ServerConnector myConn;

    public interface Callbacks {
        void onMenuSelected(ImageButton button);
    }

 /*   public static  EasyTankFragment newInstance(String a, String b, String c, String d, String e) {
        tankTemp = a;
        pump1liquid = b;
        pump2liquid = c;
        pump3liquid = d;
        pump4liquid = e;
        return new EasyTankFragment();
    } */

    public static  EasyTankFragment newInstance() {
        return new EasyTankFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "fragment started");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate Called!");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(!loadFromServer) {
            loadPumpInfo();
            loadFromServer = true;
        }
        View view = inflater.inflate(R.layout.fragment_easytank, container, false);
        prefs = this.getActivity().getPreferences(Activity.MODE_PRIVATE);

        pump1MeterText = (TextView) view.findViewById(R.id.firstPumpText);
        //pump1MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump1Key, 0)) + " mL");
        pump1MeterText.setText("" + pump1liquid + " mL");
        pump1Meter =  view.findViewById(R.id.firstPump);
/*        pump1Meter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "Pump 1 Reset!!!");
                updateFertsUI(1);
                pump1MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump1Key, 0)) + " mL");
                pump1Meter.getLayoutParams().height = (int) prefs.getFloat(Pump1Key, 0) * 10;
                return true;
            }
        });
        if(prefs.getFloat(Pump1Key, 0) < 10) {
            pump1Meter.getLayoutParams().height = 50;
        } else {
            pump1Meter.getLayoutParams().height = (int) prefs.getFloat(Pump1Key, 0) * 10;
        }
        pump1Meter.requestLayout();
*/
        pump2MeterText = (TextView) view.findViewById(R.id.secondPumpText);
        //pump2MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump2Key, 0)) + " mL");
        pump2MeterText.setText("" + pump2liquid + " mL");
        pump2Meter =  view.findViewById(R.id.secondPump);
/*        pump2Meter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "Pump 2 Reset!!!");
                updateFertsUI(2);
                pump2MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump2Key, 0)) + " mL");
                pump2Meter.getLayoutParams().height = (int) prefs.getFloat(Pump2Key, 0) * 10;
                return true;
            }
        });
        if(prefs.getFloat(Pump2Key, 0) < 10) {
            pump1Meter.getLayoutParams().height = 50;
        } else {
            pump2Meter.getLayoutParams().height = (int) prefs.getFloat(Pump2Key, 0) * 10;
        }
        pump2Meter.requestLayout();
*/
        pump3MeterText = (TextView) view.findViewById(R.id.thirdPumpText);
        //pump3MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump3Key, 0)) + " mL");
        pump3MeterText.setText("" + pump3liquid + " mL");
        pump3Meter =  view.findViewById(R.id.thirdPump);
/*        pump3Meter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "Pump 3 Reset!!!");
                updateFertsUI(3);
                pump3MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump3Key, 0)) + " mL");
                pump3Meter.getLayoutParams().height = (int) prefs.getFloat(Pump3Key, 0) * 10;
                return true;
            }
        });
        if(prefs.getFloat(Pump3Key, 0) < 10) {
            pump3Meter.getLayoutParams().height = 50;
        } else {
            pump3Meter.getLayoutParams().height = (int) prefs.getFloat(Pump3Key, 0) * 10;
        }
        pump3Meter.requestLayout();
*/
        pump4MeterText = (TextView) view.findViewById(R.id.fourthPumpText);
        //pump4MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump4Key, 0)) + " mL");
        pump4MeterText.setText("" + pump4liquid + " mL");
        pump4Meter =  view.findViewById(R.id.fourthPump);
/*        pump4Meter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(TAG, "Pump 4 Reset!!!");
                updateFertsUI(4);
                pump4MeterText.setText("" + String.format(java.util.Locale.US, "%.1f", prefs.getFloat(Pump4Key, 0)) + " mL");
                pump4Meter.getLayoutParams().height = (int) prefs.getFloat(Pump4Key, 0) * 10;
                return true;
            }
        });
        if(prefs.getFloat(Pump4Key, 0) < 10) {
            pump4Meter.getLayoutParams().height = 50;
        } else {
            pump4Meter.getLayoutParams().height = (int) prefs.getFloat(Pump4Key, 0) * 10;
        }
        pump4Meter.requestLayout();
*/
        mTemperature = (TextView) view.findViewById(R.id.temp);
        mTemperature.setText(tankTemp);

        mFertilizersMenu = (ImageButton) view.findViewById(R.id.fertilizers_button);
        buttonEffect(mFertilizersMenu);
        mFertilizersMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onMenuSelected(mFertilizersMenu);
            }
        });
        mScheduleMenu = (ImageButton) view.findViewById(R.id.schedule_button);
        buttonEffect(mScheduleMenu);
        mScheduleMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onMenuSelected(mScheduleMenu);
            }
        });
        mFeederMenu = (ImageButton) view.findViewById(R.id.feeder_button);
        buttonEffect(mFeederMenu);
        mFeederMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onMenuSelected(mFeederMenu);
            }
        });


        pump1Meter.getLayoutParams().height = (int) Float.parseFloat(pump1liquid) * 10;
        pump2Meter.getLayoutParams().height = (int) Float.parseFloat(pump2liquid) * 10;
        pump3Meter.getLayoutParams().height = (int) Float.parseFloat(pump3liquid) * 10;
        pump4Meter.getLayoutParams().height = (int) Float.parseFloat(pump4liquid) * 10;

        //pump1Meter.requestLayout();
        //pump2Meter.requestLayout();
        //pump3Meter.requestLayout();
        //pump4Meter.requestLayout();

        return view;
    }

    private void updateFertsUI(int pump) {
        SharedPreferences.Editor editor = prefs.edit();
        float currentPumpDoses = 80;
        switch(pump) {
            case 1:
                editor.putFloat(Pump1Key, currentPumpDoses);
                editor.apply();
                break;
            case 2:
                editor.putFloat(Pump2Key, currentPumpDoses);
                editor.apply();
                break;
            case 3:
                editor.putFloat(Pump3Key, currentPumpDoses);
                editor.apply();
                break;
            case 4:
                editor.putFloat(Pump4Key, currentPumpDoses);
                editor.apply();
                break;
        }
    }
    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
    private void loadPumpInfo() {
        ServerConnector myConn = new ServerConnector();
        myConn.execute("temp");
        try {
             tankTemp = myConn.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (tankTemp == null) {
           tankTemp = "N/A";
        }

        myConn = new ServerConnector();
        myConn.execute("loadpump1");
        try {
            pump1liquid = myConn.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }
        if(pump1liquid == null) {
            pump1liquid = "0";
        }


        myConn = new ServerConnector();
        myConn.execute("loadpump2");
        try {
            pump2liquid = myConn.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }
        if(pump2liquid == null) {
            pump2liquid = "0";
        }


        myConn = new ServerConnector();
        myConn.execute("loadpump3");
        try {
            pump3liquid = myConn.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }
        if(pump3liquid == null) {
            pump3liquid = "0";
        }


        myConn = new ServerConnector();
        myConn.execute("loadpump4");
        try {
            pump4liquid = myConn.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }
        if(pump4liquid == null) {
            pump4liquid = "0";
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
            if(result != null) {
                try {
                    char thing[] = result.toCharArray();
                    String testResult = "" + thing[0] + thing[1] + thing[2] + thing[3] + thing[4] + '°';
                    mTemperature.setText(testResult);
                }catch(Exception e) {
                    Log.d(TAG, "TEMP SENSOR ERROR");
                }
            }
        }
    } */
}
