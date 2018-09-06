package com.tards.imt.mypointofinterest;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tards.imt.mypointofinterest.model.Poi;

import java.util.Calendar;
import java.util.Date;

public class EditPoiActivity extends AppCompatActivity {

    private TextInputEditText mTitleInput;
    private TextInputEditText mLatitudeImput;
    private TextInputEditText mLongitudeInput;
    private TextInputEditText mDescriptionInput;

    private Button mSubmitButton;

    private Poi mCurrentPoi;

    private boolean edition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poi);

        mTitleInput = findViewById(R.id.titleInput);
        mLatitudeImput = findViewById(R.id.latitudeInput);
        mLongitudeInput = findViewById(R.id.longitudeInput);
        mDescriptionInput = findViewById(R.id.descriptionInput);
        mSubmitButton = findViewById(R.id.submitButton);

        edition = false;

        if(edition){

        }

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoi();
                savePoiAndFinishActicity();
            }
        });


    }


    protected void savePoiAndFinishActicity(){
        Intent intent = new Intent();
        intent.putExtra("newPoi", mCurrentPoi);
        setResult(RESULT_OK, intent);
        finish();
    }

    protected void updatePoi(){
        mCurrentPoi = new Poi(
                mTitleInput.getText().toString(),
                mLatitudeImput.getText().toString(),
                mLongitudeInput.getText().toString(),
                mDescriptionInput.getText().toString());
    }


}
