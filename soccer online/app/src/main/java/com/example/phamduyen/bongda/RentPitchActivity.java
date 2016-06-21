package com.example.phamduyen.bongda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import bean.Pitch;
import connect.MyReader;

/**
 * Created by Pham Duyen on 23/04/2016.
 */
public class RentPitchActivity extends Activity {

    EditText edName, edPhone;
    Button okBtn;
    String url,name,phone, day,time, address,idTime;
    Bundle extras;
    Pitch pitch;
    TextView tvName, tvAddress,tvCost, tvDay, tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_pitch);

        extras = getIntent().getExtras();
        pitch = (Pitch) extras.getSerializable("pitch");
        day = extras.getString("day");
        time = extras.getString("time");
        address = extras.getString("address");
        idTime = extras.getString("idTime");

        url = " http://football.somee.com/api/Rent";

        edName = (EditText)findViewById(R.id.edNameRentPitch);
        edPhone = (EditText)findViewById(R.id.edPhoneRentPitch);
        tvName = (TextView) findViewById(R.id.tvRentName);
        tvAddress = (TextView) findViewById(R.id.tvRentAddress);
        tvCost = (TextView) findViewById(R.id.tvRentCost);
        tvDay = (TextView) findViewById(R.id.tvRentDay);
        tvTime = (TextView) findViewById(R.id.tvRentTime);


        tvName.setText(pitch.getName());
        tvAddress.setText(address);
        tvCost.setText(Integer.toString(pitch.getCost()));
        tvDay.setText(day);
        tvTime.setText(time);
        okBtn = (Button)findViewById(R.id.btnRentPitch);




        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                phone = edPhone.getText().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadJsonTask().execute(url + "?idTime="+idTime+"&idPitch="+Integer.toString(pitch.getIDPitch())+"&day="+day+"&name="+name+"&sdt="+phone);

                    }
                });
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
                Toast.makeText(RentPitchActivity.this, "Đặt sân thất bại", Toast.LENGTH_LONG).show();
            } else {
                Intent in = new Intent(RentPitchActivity.this,FindPitchActivity.class);
                startActivity(in);
            }
        }
    }

}
