package com.valencia.oscar.w2d5_ex1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact extends AppCompatActivity {
    static final int CAM_REQUEST = 6666;
    private static final String TAG = Contact.class.getSimpleName()+"_TAG";
    private static final String MY_PACKAGE = "com.valencia.oscar.w2d5_ex1";
    EditText nameET,emailET,ageET,lastNameET,phoneET;
    Button photoBtn, saveBtn;
    TextView alertsTV, photoTV;
    ContactDB MyContacts;
    String mCurrentPhotoPath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        nameET = findViewById(R.id.nameET);
        lastNameET = findViewById(R.id.lastNameET);
        emailET = findViewById(R.id.emailET);
        ageET = findViewById(R.id.ageET);
        photoBtn = findViewById(R.id.photoBtn);
        saveBtn = findViewById(R.id.saveBtn);
        alertsTV = findViewById(R.id.alertsTV);
        photoTV = findViewById(R.id.photoTV);
        phoneET = findViewById(R.id.phoneET);

        if(!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            photoBtn.setEnabled(false);
            alertsTV.setText("Your device has not any camera");
        }

    }

    public void launchCamera(View view) {
        requestCameraPermissions();
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePhoto.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                photoFile = createImageFile();
                photoTV.setText(photoFile.getAbsolutePath());
            }catch(IOException ex){
                alertsTV.setText("Unable to create image directory");
                Log.d(TAG, ex.toString());
            }
            if(null!=photoFile){
                Uri photoURI = FileProvider.getUriForFile(this,
                        MY_PACKAGE,
                        photoFile);
                takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePhoto, CAM_REQUEST);
            }
        }else{
            alertsTV.setText("There is not an application in the device to take photos.");
            Log.d(TAG,"There is not an application for implicit intent (camera)");
        }
    }

    public void requestCameraPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, 1);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg",  storageDir );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void saveContact(View v){
        MyContacts = new ContactDB(this, "contacts", null, 1);
        SQLiteDatabase db = MyContacts.getWritableDatabase();

        if(nameET.getText().toString().length()>0 &&
                emailET.getText().toString().length()>0 &&
                ageET.getText().toString().length()>0){
            if(db!=null){
                String sqlInsert = "INSERT INTO "+MyContacts.getTABLE_NAME()+
                        "("+MyContacts.getCOLUMN_2()+
                        ","+MyContacts.getCOLUMN_3()+
                        ","+MyContacts.getCOLUMN_4()+
                        ","+MyContacts.getCOLUMN_5()+
                        ","+MyContacts.getCOLUMN_6()+
                        ","+MyContacts.getCOLUMN_7()+ ") " +
                        "VALUES('"+nameET.getText().toString()+
                        "','"+lastNameET.getText().toString()+
                        "',"+Integer.parseInt(ageET.getText().toString())+
                        ",'"+emailET.getText().toString()+
                        "','"+phoneET.getText().toString()+
                        "','"+mCurrentPhotoPath+"')";
                Log.d(TAG,sqlInsert);
                try{
                    db.execSQL(sqlInsert);
                    db.close();
                    nameET.setText("");
                    lastNameET.setText("");
                    ageET.setText("");
                    emailET.setText("");
                    phoneET.setText("");

                    mCurrentPhotoPath = "";

                    Log.d(TAG,"Record stored!");
                    alertsTV.setText("Record stored!");
                    photoTV.setText("");

                }catch(Exception e){
                    Log.d(TAG,e.toString());
                    alertsTV.setText("Error while saving record.");
                }

            }else{
                Log.d(TAG,"DB has not been opened");
            }
        }else{
            alertsTV.setText("Fill out all the fields.");
        }
    }
}
