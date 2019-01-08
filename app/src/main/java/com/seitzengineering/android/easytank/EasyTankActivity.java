package com.seitzengineering.android.easytank;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class EasyTankActivity extends SingleFragmentActivity implements EasyTankFragment.Callbacks {

    private static final String TAG ="EasyTankActivity";
    Fragment mFertilizerFragment = null;
    Fragment mScheduleFragment = null;
    Fragment mFeederFragment = null;
    Fragment mEasyTankFragment = null;
    public static SharedPreferences prefs;
    private String pump1liquid = "";
    private String pump2liquid = "";
    private String pump3liquid = "";
    private String pump4liquid = "";
    private String tankTemp = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "App started");
        super.onCreate(savedInstanceState);
        //prefs = getPreferences(Activity.MODE_PRIVATE);
    }

 /*   public void applicationLoadInfo(String a, String b, String c, String d, String e) {
        if(mEasyTankFragment == null)
            mEasyTankFragment = EasyTankFragment.newInstance(a, b, c, d, e);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mEasyTankFragment)
                .addToBackStack(null)
                .commit();
    } */

    @Override
    protected Fragment createFragment() {
        return EasyTankFragment.newInstance();
        //return LoadFragment.newInstance();
    }

    @Override
    public void onMenuSelected(ImageButton button) {
        if (button == findViewById(R.id.fertilizers_button)) {
            Log.d(TAG, "Fertilizers Menu Clicked!");
            if(mFertilizerFragment == null)
                mFertilizerFragment = FertilizerFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFertilizerFragment)
                        .addToBackStack(null)
                        .commit();
        } else if (button == findViewById(R.id.schedule_button)) {
            Log.d(TAG, "Schedule Menu Clicked!");
            if(mScheduleFragment == null)
                mScheduleFragment = ScheduleFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mScheduleFragment)
                        .addToBackStack(null)
                        .commit();
        } else if (button == findViewById(R.id.feeder_button)) {
            Log.d(TAG, "Feeder Menu Clicked!");
            if(mFeederFragment == null)
                mFeederFragment = FeederFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mFeederFragment)
                        .addToBackStack(null)
                        .commit();
        }
    }
}
