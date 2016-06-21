package com.example.phamduyen.bongda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import bean.Pitch;
import bean.Rent;

/**
 * Created by Pham Duyen on 07/05/2016.
 */
public class DetailMatchManage extends Activity {
    TextView tvName, tvdate,tvtnamerent,tvtime;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_match_management);

        Bundle bundle = (Bundle) getIntent().getExtras();
        Rent p  = (Rent) bundle.getSerializable("rent");

        tvName = (TextView) findViewById(R.id.txtMatchDetail);
        tvdate = (TextView) findViewById(R.id.txtDateMatch);
        tvtime = (TextView) findViewById(R.id.txtTimeMatch);
        tvtnamerent = (TextView) findViewById(R.id.txtTimeMatch);
        tvName.setText(p.getName());
    }
}
