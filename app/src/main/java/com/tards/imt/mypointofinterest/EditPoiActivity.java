package com.tards.imt.mypointofinterest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tards.imt.mypointofinterest.model.Poi;


public class EditPoiActivity extends AppCompatActivity {

    private TextView activityLabel;

    private TextInputEditText mTitleInput;
    private TextInputEditText mLatitudeImput;
    private TextInputEditText mLongitudeInput;
    private TextInputEditText mDescriptionInput;
    private Button mSubmitButton;

    private TextView mLatitudeTv;
    private TextView mLongitudeTv;
    private Button mCoordsButton;

    private Poi mCurrentPoi;
    private boolean edition;

    private String mCurrentLatitude;
    private String mCurrentLongitude;

    private ImageView mRefreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poi);

        activityLabel = findViewById(R.id.activityLabel);

        mTitleInput = findViewById(R.id.titleInput);
        mLatitudeImput = findViewById(R.id.latitudeInput);
        mLongitudeInput = findViewById(R.id.longitudeInput);
        mDescriptionInput = findViewById(R.id.descriptionInput);
        mSubmitButton = findViewById(R.id.submitButton);

        mLatitudeTv = findViewById(R.id.latitudeTv);
        mLongitudeTv = findViewById(R.id.longitudeTv);

        mCoordsButton = findViewById(R.id.locationButton);
        mRefreshButton = findViewById(R.id.refreshButton);

        try{

            Intent intent = getIntent();
            mCurrentPoi = (Poi) intent.getSerializableExtra("poi");
            if(mCurrentPoi != null){
                edition = true;
                updatePoi();
            } else {
                mCurrentPoi = new Poi(
                        mTitleInput.getText().toString(),
                        mLatitudeImput.getText().toString(),
                        mLongitudeInput.getText().toString(),
                        mDescriptionInput.getText().toString());
                edition = false;
            }
        }catch (Exception e){

        }

        if(edition){
            activityLabel.setText("Edition d'un POI");
        } else {
            activityLabel.setText("Création d'un POI");
        }

        getCurrentLocation();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModelPoi();
                savePoiAndFinishActicity();
            }
        });

        mCoordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentLocationInput();
            }
        });

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });
    }


    protected void savePoiAndFinishActicity(){
        Intent intent = new Intent();
        intent.putExtra("newPoi", mCurrentPoi);
        intent.putExtra("edition",edition);
        Log.d("EditPoiActivity", "passing edition as :" + edition);
        setResult(RESULT_OK, intent);
        finish();
    }

    protected void updatePoi(){

        mTitleInput.setText(this.mCurrentPoi.getLabel());
        mLatitudeImput.setText(this.mCurrentPoi.getLatitude());
        mLongitudeInput.setText(this.mCurrentPoi.getLongitude());
        mDescriptionInput.setText(this.mCurrentPoi.getDescription());

    }

    protected void updateModelPoi(){

        mCurrentPoi.setLabel(mTitleInput.getText().toString());
        mCurrentPoi.setLatitude(mLatitudeImput.getText().toString());
        mCurrentPoi.setLongitude(mLongitudeInput.getText().toString());
        mCurrentPoi.setDescription(mDescriptionInput.getText().toString());

    }

    @SuppressLint("MissingPermission")
    protected void getCurrentLocation(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //Location locationGPS = locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0, (LocationListener) this);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mCurrentLatitude = String.valueOf(location.getLatitude());
                mCurrentLongitude = String.valueOf(location.getLongitude());

                setCurrentLocationTextView();
                Log.d("Current Location", "Lat:" + mCurrentLatitude + ", Long:" + mCurrentLongitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
        setCurrentLocationTextView();
    }

    protected void setCurrentLocationInput(){

        if(mCurrentLatitude != null && mCurrentLongitude != null){
            mLatitudeImput.setText(this.mCurrentLatitude);
            mLongitudeInput.setText(this.mCurrentLongitude);
        }
    }

    protected void setCurrentLocationTextView(){

        if(mCurrentLatitude != null && mCurrentLongitude != null){
            mLatitudeTv.setText("Latitude: " + this.mCurrentLatitude);
            mLongitudeTv.setText("Longitude " + this.mCurrentLongitude);
        } else {
            mLatitudeTv.setText("Latitude: " + "Unknown");
            mLongitudeTv.setText("Longitude " + "Unknown");
        }
    }


}
