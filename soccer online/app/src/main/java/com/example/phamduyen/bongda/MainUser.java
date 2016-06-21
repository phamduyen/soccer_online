package com.example.phamduyen.bongda;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import adapter.Menu_Iteam_Adapter;
import bean.menuItem;

public class MainUser extends Activity {
    GridView gridView;
    ArrayAdapter<menuItem> adapter ;
    ArrayList<menuItem> menuItemArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        gridView = (GridView) findViewById(R.id.menuViewUser);
        createMenu();
        adapter = new Menu_Iteam_Adapter(this,R.layout.menu_main_user,menuItemArrayList);
        gridView.setAdapter(adapter);
    }
    public  void createMenu(){
        menuItemArrayList = new ArrayList<>();
        menuItemArrayList.add(new menuItem("create_new","CREATE"));
        menuItemArrayList.add(new menuItem("pitch1","MANAGEMENT"));
        menuItemArrayList.add(new menuItem("change_profile","PROFILE"));
    }
}
