package com.example.phamduyen.bongda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import bean.Pitch;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListPitchManagemnet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListPitchManagemnet#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ListPitchManagemnet extends Fragment {


    String url = "http://football.somee.com/api/Pitch";
    Button btAdd;
    Gson gson = new Gson();
    ArrayList<Pitch> pitchs = new ArrayList<Pitch>();
    public ListPitchManagemnet() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_pitch_management, container, false);

    }


}
