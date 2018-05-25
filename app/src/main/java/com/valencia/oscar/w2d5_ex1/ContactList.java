package com.valencia.oscar.w2d5_ex1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactList extends AppCompatActivity {

    EditText nameET,emailET,ageET,lastNameET;
    Button photoBtn, saveBtn, listBtn;
    TextView alertsTV;
    ContactDB MyContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        nameET = findViewById(R.id.nameET);
        lastNameET = findViewById(R.id.lastNameET);
        emailET = findViewById(R.id.emailET);
        ageET = findViewById(R.id.ageET);
        photoBtn = findViewById(R.id.photoBtn);
        saveBtn = findViewById(R.id.saveBtn);
        listBtn = findViewById(R.id.listBtn);
        alertsTV = findViewById(R.id.alertsTV);
    }

    public void saveRecord(){
        MyContacts = new ContactDB(this, "MyContacts", null, 1);
        SQLiteDatabase db = MyContacts.getWritableDatabase();
        String tmpPicturePath ="";

        if(nameET.getText().toString().length()>0 &&
                emailET.getText().toString().length()>0 &&
                ageET.getText().toString().length()>0){
            if(db!=null){
                String sqlInsert = "INSERT INTO "+MyContacts.getTABLE_NAME()+
                        "("+MyContacts.MyContacts()+","+MyContacts.MyContacts()+","+MyContacts.getCOLUMN_4()+","+MyContacts.getCOLUMN_5()+") " +
                        "VALUES('"+nameET.getText().toString()+
                        "','"+emailET.getText().toString()+
                        "',"+Integer.parseInt(ageET.getText().toString())+
                        ",'"+tmpPicturePath+"')";
                Log.d(TAG,sqlInsert);
                try{
                    db.execSQL(sqlInsert);
                    db.close();
                    nameET.setText("");
                    emailET.setText("");
                    ageET.setText("");

                }catch(Exception e){
                    Log.d(TAG,e.toString());
                }
                Log.d(TAG,"Record stored!");
                alertsTV.setText("Record stored!");
            }else{
                Log.d(TAG,"DB has not been opened");
            }
        }else{
            alertsTV.setText("Fill out all the fields.");
        }
    }
}
