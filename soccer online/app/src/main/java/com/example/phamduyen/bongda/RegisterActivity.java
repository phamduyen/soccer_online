package com.example.phamduyen.bongda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import connect.MyReader;

/**
 * Created by Pham Duyen on 23/04/2016.
 */
public class RegisterActivity extends Activity {

    EditText edName, edPass, edEmail, edPhone, edAddress;
    Button okBtn;
    String url,name,pass,email,phone,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        url = " http://football.somee.com/api/User";
        edName = (EditText)findViewById(R.id.nameText);
        edPass = (EditText)findViewById(R.id.passText);
        edEmail = (EditText)findViewById(R.id.userText);
        edPhone = (EditText)findViewById(R.id.phoneText);
        edAddress = (EditText)findViewById(R.id.addressText);
        okBtn = (Button)findViewById(R.id.btOk);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                pass = edPass.getText().toString();
                email = edEmail.getText().toString();
                phone = edPhone.getText().toString();
                address = edAddress.getText().toString();
                //final String s = url + "?email="+email+"&pass="+pass+"&name="+name+"&phone="+phone+"&address="+address;
                //Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_LONG).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       new DownloadJsonTask().execute(url + "?email="+email+"&pass="+pass+"&name="+name+"&phone="+phone+"&address="+address);
                        //new DownloadJsonTask().execute(url + "?email=ldt@abc&pass=123456&name=jang&phone=123456789");
                        //new DownloadJsonTask().execute( url + "?email=abc1@abc&pass=123456&name=abc&phone=123&address=ahjhj");
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
                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_LONG).show();
            } else {
                Intent in = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(in);
            }
        }
    }

}
