package com.example.phamduyen.bongda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import bean.Pitch;

/**
 * Created by Thanh Than on 27/04/2016.
 */
public class Detail_PitchActivity extends Activity {
    TextView tvName, tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pitch);

        Bundle bundle = (Bundle) getIntent().getExtras();
        Pitch p  = (Pitch) bundle.getSerializable("pitch");

        tvName = (TextView) findViewById(R.id.textPitchDetail);
        tvAddress = (TextView) findViewById(R.id.textPitchAdr);

        tvName.setText(p.getName());
       // tvAddress.setText(p.getAddress());
    }
}
