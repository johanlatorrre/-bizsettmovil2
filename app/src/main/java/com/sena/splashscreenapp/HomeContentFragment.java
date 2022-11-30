package com.sena.splashscreenapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeContentFragment extends Fragment {
    private static final String TEXT ="text";

    public static HomeContentFragment newInstance(String text){
        HomeContentFragment frag = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        frag.setArguments(args);
        return frag;
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.home_content_fragment, container, false);
        if (getArguments() !=null){
            ((TextView)layout.findViewById(R.id.text)).setText(getArguments().getString(TEXT));
        }

        return inflater.inflate(R.layout.home_content_fragment, container, false);

    }
}