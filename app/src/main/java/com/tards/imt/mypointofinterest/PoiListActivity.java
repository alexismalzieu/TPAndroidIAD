package com.tards.imt.mypointofinterest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.tards.imt.mypointofinterest.list.PoiAdapter;
import com.tards.imt.mypointofinterest.model.Poi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PoiListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Poi> mPoiList;
    private Poi testPoi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_list);

        permissionRequest();

        testPoi = new Poi(
                "labelTest",
                "50.610388",
                "3.1308817",
                "descriptionTest");

        mPoiList = new ArrayList<>();

        mPoiList.add(testPoi);
        mPoiList.add(testPoi);
        mPoiList.add(testPoi);
        mPoiList.add(testPoi);

        mRecyclerView = (RecyclerView) findViewById(R.id.poi_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PoiAdapter(this, mPoiList);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity(null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poi_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Poi newPoi = (Poi) data.getSerializableExtra("newPoi");
                Boolean edition = data.getBooleanExtra("edtion", false);

                if(edition){
                    for(Poi poi : mPoiList){
                        if(poi.getCreatedAt().equals(newPoi.getCreatedAt())) {

                            //TODO Edition
                            poi.setLabel(newPoi.getLabel());
                            poi.setLatitude(newPoi.getLatitude());
                            poi.setLongitude(newPoi.getLongitude());
                            poi.setDescription(newPoi.getDescription());

                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                    mPoiList.add(newPoi);
                    mAdapter.notifyDataSetChanged();

                } else {
                    mPoiList.add(newPoi);
                    mAdapter.notifyDataSetChanged();
                }


                Log.d("newPoi: ", newPoi.toString());
            }
        }
    }

    public void startEditActivity(Poi poi){

        Intent intent = new Intent(PoiListActivity.this, EditPoiActivity.class);

        if (poi != null) {
            intent.putExtra("poi", poi);
        }

        startActivityForResult(intent, 1);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void permissionRequest(){

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET};

        requestPermissions(permissions,1);
    }
}
