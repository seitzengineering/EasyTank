package com.seitzengineering.android.easytank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kevin on 12/28/2016.
 */

public class FeederFragment extends Fragment {

    public static FeederFragment newInstance() {
        return new FeederFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeder, container, false);

        return view;
    }
}
