package com.example.song.myapplication;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout = null;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        /*lv = (ListView) findViewById(R.id.lv);

        ArrayList<HashMap<String, Object>> listitem = new ArrayList<HashMap<String, Object>>();
        for (int i=0; i<8; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("txv1", "the"+i+"song");
            map.put("txv2", "auther"+i);
            listitem.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listitem, R.layout.list_item_view ,
                new String[]{"txv1", "txv2"}, new int[]{R.id.txv1, R.id.txv2});
        lv.setAdapter(simpleAdapter);
        */
    }
    
}
