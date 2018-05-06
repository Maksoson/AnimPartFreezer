package com.example.a1.animpartfreezer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.animpartfreezer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpgradeFragment extends Fragment {


    public UpgradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upgrade, container, false);
    }

}
