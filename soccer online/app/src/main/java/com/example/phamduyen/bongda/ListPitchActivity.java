package com.example.phamduyen.bongda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.MyAdapterRent;
import bean.Pitch;
import bean.User;

/**
 * Created by Nara on 05/05/2016.
 */
public class ListPitchActivity extends Activity {

    String url = "http://football.somee.com/api/Pitch";
    Button btAdd;
    Gson gson = new Gson();
    ArrayList<Pitch> pitchs = new ArrayList<Pitch>();
    ListView listLegend;
    MyAdapterRent adapter;
    Bundle extras;
    String result, day, time,idTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pitch);
        extras = getIntent().getExtras();
        String loi;
        JSONObject j = null, jj = null;
        Pitch pitch;
        User user;
        result = extras.getString("result");
        day = extras.getString("day");
        time = extras.getString("time");
        idTime = extras.getString("idTime");

        try {
            JSONArray a = null;
            a = new JSONArray(result);
            loi = "true";
            for (int i = 0; i < a.length(); i++) {
                j = a.getJSONObject(i);
                jj = j.getJSONObject("User");
                pitch = gson.fromJson(j.toString(), Pitch.class);
                user = gson.fromJson(jj.toString(), User.class);
                pitch.setUser(user);
                pitchs.add(pitch);
            }


            listLegend = (ListView) findViewById(R.id.listfootballegen);
            //tạo adapter
            adapter = new MyAdapterRent(ListPitchActivity.this, R.layout.listitem_pitch, pitchs, day, time,idTime);
            //đổ dữ liệu lên list view
            listLegend.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
