package com.valencia.oscar.w2d5_ex1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Contact extends AppCompatActivity {
    static final int CAM_REQUEST = 6666;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);



    }


    public void launchCamera(View view) {
        requestCameraPermissions();
        Intent launchCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(launchCamera,CAM_REQUEST);
    }

    public void requestCameraPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }
    }
}
