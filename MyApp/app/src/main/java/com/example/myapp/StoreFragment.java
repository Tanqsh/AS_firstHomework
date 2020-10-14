package com.example.myapp;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */

//将R.layout.tab02压缩到StoreFragment类中，将一个fragment对象化，就可通过调用对象的形式来调用fragment
public class StoreFragment extends Fragment {


    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab02, container, false);
    }

}
