package com.example.phamduyen.bongda;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import connect.MyReader;

public class MainActivity extends AppCompatActivity {
    EditText edName, edPass;
    Button loginBtn, registerBtn, guessBtn;
    String url,name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        url = " http://football.somee.com/api/User";
        edName = (EditText)findViewById(R.id.userText);
        edPass = (EditText)findViewById(R.id.passText);
        loginBtn = (Button)findViewById(R.id.login_action);
        registerBtn = (Button)findViewById(R.id.register_action);
        guessBtn = (Button)findViewById(R.id.guess_action);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new RefreshData().execute( " http://football.somee.com/api/Rent");
                //new DownloadJsonTask().execute(url + "?email=ldt@abc&pass=123456&name=jang&phone=123456789");

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString();
                pass = edPass.getText().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadJsonTask().execute(url + "?email="+name+"&pass="+pass);
                        //new DownloadJsonTask().execute(url + "?email=ldt@abc&pass=123456&name=jang&phone=123456789");

                    }
                });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(in1);
            }
        });

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(MainActivity.this,FindPitchActivity.class);
                startActivity(in1);
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
            //Toast.makeText(MainActivity.this, loi, Toast.LENGTH_LONG).show();
            if (result.equals("false")) {
                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
            } else {
                Intent in = new Intent(MainActivity.this,TabMenu.class);
                in.putExtra("idUser",result);
                startActivity(in);
            }
        }
    }

    public class RefreshData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            String loi;
            String method = "DELETE";
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

        }
    }
}
