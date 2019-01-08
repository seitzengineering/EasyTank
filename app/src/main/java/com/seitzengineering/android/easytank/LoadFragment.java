package com.seitzengineering.android.easytank;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutionException;

/**
 * Created by Kevin on 8/13/2017.
 */

public class LoadFragment extends Fragment {

    private String pump1liquid = "";
    private String pump2liquid = "";
    private String pump3liquid = "";
    private String pump4liquid = "";
    private String tankTemp = "";
    private Callbacks mCallBacks;
    private static final String TAG ="LoadFragment";


    public interface Callbacks {
        void applicationLoadInfo(String a, String b, String c, String d, String e);
    }

    public static LoadFragment newInstance() {
        return new LoadFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate Called!");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBacks = (Callbacks) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "we are visible! before loadpumps");
        loadPumpInfo();
        Log.d(TAG, "we are visible! after loadpumps");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load, container, false);

        return view;
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
        mCallBacks.applicationLoadInfo(tankTemp, pump1liquid, pump2liquid, pump3liquid, pump4liquid);
    }
}
