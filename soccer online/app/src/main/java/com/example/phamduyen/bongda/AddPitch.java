package com.example.phamduyen.bongda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import bean.Pitch;
import bean.User;
import connect.MyReader;




public class AddPitch extends Fragment {
    EditText edName, edCost, edPlayers;
    Button btAdd,btCancel;
    String url,pitchName, pitchCost, pitchPlayers;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_pitch, container, false);

    }
    public void onStart() {
        super.onStart();
        url = "http://football.somee.com/api/Pitch";
        edName = (EditText)getActivity().findViewById(R.id.txtPitchName);
        edCost = (EditText)getActivity().findViewById(R.id.txtPitchCost);
        edPlayers = (EditText)getActivity().findViewById(R.id.txtPitchPlayers);

        btAdd = (Button)getActivity().findViewById(R.id.btAddPitch);
        btCancel = (Button)getActivity().findViewById(R.id.btCancelPitch);


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pitchName = edName.getText().toString();
                pitchCost = edCost.getText().toString();
                pitchPlayers = edPlayers.getText().toString();

                String s = url + "?name="+pitchName+"&cost="+pitchCost+"&players="+pitchPlayers+"&userID=3";

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadJsonTask().execute(url + "?name=" + pitchName + "&cost=" + pitchCost + "&players=" + pitchPlayers + "&userID=3");
                        //new DownloadJsonTask().execute(url + "?email=ldt@abc&pass=123456&name=jang&phone=123456789");
                        //new DownloadJsonTask().execute( url + "?email=abc1@abc&pass=123456&name=abc&phone=123&address=ahjhj");
                    }
                });
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AddPitch.this.getActivity(), RegisterActivity.class);
                startActivity(in);

            }
        });

    }
    public class DownloadJsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            String loi;
            String method = "POST";

            try {
                loi = MyReader.readFromUrl(url, method);

            } catch (IOException e) {
                e.printStackTrace();
                loi = "aaaaaaaa";

            }
            return loi;
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(MainActivity.this, loi, Toast.LENGTH_LONG).show();
            if (!result.equals("true")) {
                Toast.makeText(AddPitch.this.getActivity(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(AddPitch.this.getActivity(), "ok", Toast.LENGTH_LONG).show();
                Intent in = new Intent(AddPitch.this.getActivity(),RegisterActivity.class);
                startActivity(in);
            }
        }
    }
}
