package com.example.phamduyen.bongda;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import adapter.ManagePitchAdapter;
import bean.Pitch;
import bean.Rent;
import connect.MyReader;



public class ManageMatch extends Fragment {
    //DownloadJsonTask list = new DownloadJsonTask();
    String url = "http://football.somee.com/api/Rent";
    // Button btAdd;
    final Gson gson = new Gson();
    final ArrayList<Rent> matchs = new ArrayList<Rent>();
    ListView listLegend;
    ManagePitchAdapter adapter;
   // private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_match, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DownloadJsonTask().execute(url+"?idUser=3");
            }
        });

    }

    public class DownloadJsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            String loi;
            String method = "GET";
            // try {
            // jsonObj = new JSONObject(url);

            try {
                loi = MyReader.readFromUrl(url, method);

            } catch (IOException e) {
                e.printStackTrace();
                loi = e.toString();
            }
            return loi;
        }

        @Override
        protected void onPostExecute(String result) {
            String loi;
            JSONObject j=null,jj =null;
            Pitch pitch;
            Rent rent;
            try {
                JSONArray a = null;
                try {
                    a = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                loi = "true";
                for (int i = 0;i<a.length();i++)
                {
                    j = a.getJSONObject(i);
                    jj = j.getJSONObject("Pitch");
                    rent = gson.fromJson(j.toString(),Rent.class);
                    pitch = gson.fromJson(jj.toString(),Pitch.class);
                    rent.setPitch(pitch);
                    matchs.add(rent);
                }

                listLegend = (ListView) getActivity().findViewById(R.id.listfootballmanage);
                //tạo adapter
                adapter = new ManagePitchAdapter(ManageMatch.this.getActivity(), R.layout.listitem_match_management, matchs);
                //đổ dữ liệu lên list view
                listLegend.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                loi = "false";
            }

           // Toast.makeText(ManageMatch.this.getActivity(), matchs.get(1).getPitch().getName(), Toast.LENGTH_LONG).show();
        }
    }




}
