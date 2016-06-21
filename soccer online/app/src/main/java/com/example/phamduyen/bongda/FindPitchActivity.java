package com.example.phamduyen.bongda;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import bean.Pitch;
import bean.RentTime;
import bean.User;
import connect.MyReader;

/**
 * Created by Nara on 05/05/2016.
 */
public class FindPitchActivity extends Activity{

    String url = "http://football.somee.com/api/Pitch";
  // String url = "http://localhost:3535/api/Pitch?name=san1&address=haichau&players=5&idTime=6&day=2016-03-20";
    Button btDate, btFindPitch;
    Calendar cal;
    String name, address,players, idTime, dayRent;

    EditText edName, edAddress, edPlayers;
    java.util.Date date;
    TextView txtDate;
    ArrayList<RentTime> rt = new ArrayList<RentTime>();
    Spinner spin;
    ArrayList<Pitch> pitchs ;
    Gson gson = new Gson();
    String time[]={
            "0h30-1h30","1h30-2h30","2h30-3h30","3h30-4h30",
            "4h30-5h30","5h30-6h30","6h30-7h30","7h30-8h30",
            "8h30-9h30","9h30-10h30","10h30-11h30","11h30-12h30",
            "12h30-13h30","13h30-14h30","14h30-15h30","15h30-16h30",
            "16h30-17h30","17h30-18h30","18h30-19h30","19h30-20h30",
            "20h30-21h30","21h30-22h30","22h30-23h30","23h30-0h30"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_pitch);
        btDate = (Button)findViewById(R.id.btDate);
        txtDate = (TextView) findViewById(R.id.txtDate);
        btDate.setOnClickListener(showDatePicker);
        btFindPitch = (Button)findViewById(R.id.btFindPitch);
        edName = (EditText)findViewById(R.id.txtFindPitchName);
        edAddress = (EditText)findViewById(R.id.txtFindPitchAddress);
        edPlayers = (EditText)findViewById(R.id.txtFindPitchPlayers);
        //Set ngày giờ hiện tại khi mới chạy lần đầu
        spin=(Spinner) findViewById(R.id.spinner1);

        setDate();
        setTime();


        btFindPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pitchs = new ArrayList<Pitch>();

                name = edName.getText().toString();
                address = edAddress.getText().toString();
                players = edPlayers.getText().toString();


                //new DownloadJsonTask().execute( "http://football.somee.com/api/Pitch?name=san1&address=haichau&players=5&idTime=6&day=2016-3-20");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadJsonTask().execute(url+"?&address="+address +"&players="+players+"&idTime="+idTime +"&day="+dayRent);
                        //new DownloadJsonTask().execute(url + "?email=ldt@abc&pass=123456&name=jang&phone=123456789");
                        //new DownloadJsonTask().execute( url + "?email=abc1@abc&pass=123456&name=abc&phone=123&address=ahjhj");
                    }
                });
            }
        });
    }


    private void setDate()
    {
        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng kiểu ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        txtDate.setText(strDate);
    }

    private void setTime()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,time
        );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());

    }
    View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                //Sự kiện khi click vào nút Done trên Dialog
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    // Set text cho textView
                    txtDate.setText(day + "/" + (month +1) + "/" + year);
                    dayRent = year +"-"+ (month+1)+ "-"+day;
                    //Lưu vết lại ngày mới cập nhật
                    cal.set(year, month, day);
                    date = cal.getTime();
                }
            };
            String s=txtDate.getText()+"";
            //Lấy ra chuỗi của textView Date
            String strArrtmp[]=s.split("/");
            int ngay=Integer.parseInt(strArrtmp[0]);
            int thang=Integer.parseInt(strArrtmp[1]) - 1;
            int nam=Integer.parseInt(strArrtmp[2]);
            //Hiển thị ra Dialog
            DatePickerDialog pic=new DatePickerDialog(
                    FindPitchActivity.this,
                    callback, nam, thang, ngay);
            pic.setTitle("Chọn ngày hoàn thành");
            pic.show();
        }
    };

    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            idTime = Integer.toString(arg2+1);
            //selection.setText("aaaa");
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {

        }
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
            /*String loi;
            JSONObject j=null,jj =null;
            Pitch pitch;
            User user;
            try {
                JSONArray a = new JSONArray(result);
                loi = "true";
                for (int i = 0;i<a.length();i++)
                {
                    j = a.getJSONObject(i);
                    jj = j.getJSONObject("User");
                    pitch = gson.fromJson(j.toString(),Pitch.class);
                    user = gson.fromJson(jj.toString(),User.class);
                    pitch.setUser(user);
                    pitchs.add(pitch);
                }*/

                Intent in1 = new Intent(FindPitchActivity.this,ListPitchActivity.class);
                in1.putExtra("result",result);
                in1.putExtra("day", dayRent);
                in1.putExtra("time",time[Integer.parseInt(idTime)-1]);
                in1.putExtra("idTime",idTime);

                startActivity(in1);

        }
    }
}
