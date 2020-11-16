package com.example.sunshinemedicalclinic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MapFragment extends DialogFragment {
    @Nullable

    public String name ;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.map_fragment,container,false) ;
        TextView t = view.findViewById(R.id.clinicNameMapFragment) ;
        t.setText(name);
        return view ;
    }

    public void setName(String newName) {
        name=newName ;
    }
}
