package com.valencia.oscar.w2d5_ex1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    private static final String TAG = ContactList.class.getSimpleName()+"_TAG";
    private ContactDB MyContacts;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactAdapter(generateList());
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<ContactItem> generateList(){
        ArrayList<ContactItem> list = new ArrayList<>();
        Cursor cursor = null;
        MyContacts = new ContactDB(this, "contacts", null, 1);
        SQLiteDatabase db = MyContacts.getWritableDatabase();
        if(db!=null){
            String sqlSelect = "SELECT * FROM "+MyContacts.getTABLE_NAME();
            Log.d(TAG,sqlSelect);
            try {
                cursor = db.rawQuery(sqlSelect, null);
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    ContactItem item = new ContactItem(
                            cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_2()))),
                            cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_3()))),
                            Integer.parseInt(cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_4())))),
                            cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_5()))),
                            cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_6()))),
                            cursor.getString(cursor.getColumnIndex((MyContacts.getCOLUMN_7())))
                    );
                    list.add(item);
                    cursor.moveToNext();
                }
                cursor.close();
                db.close();
            }catch(Exception e){
                Log.d(TAG, e.toString());
            }
        }else{
            Log.d(TAG,"Unable to open DB");
        }
        return list;
    }


}
