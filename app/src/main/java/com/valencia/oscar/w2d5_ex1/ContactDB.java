package com.valencia.oscar.w2d5_ex1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDB extends SQLiteOpenHelper {

    private final String TABLE_NAME = "contacts";
    private final String COLUMN_1 = "id";
    private final String COLUMN_2 = "name";
    private final String COLUMN_3 = "lastname";
    private final String COLUMN_4 = "email";
    private final String COLUMN_5 = "age";
    private final String COLUMN_6 = "imagepath";
    private String sqlCreate =
            "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_1+
                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_2+
                    " VARCHAR NOT NULL, "+COLUMN_3+
                    " VARCHAR NOT NULL, "+COLUMN_4+
                    " VARCHAR NOT NULL, "+COLUMN_5+
                    " INTEGER NOT NULL, "+COLUMN_6+
                    " VARCHAR)";
    private String sqlDrop = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public ContactDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getCOLUMN_1() {
        return COLUMN_1;
    }

    public String getCOLUMN_2() {
        return COLUMN_2;
    }

    public String getCOLUMN_3() {
        return COLUMN_3;
    }

    public String getCOLUMN_4() {
        return COLUMN_4;
    }

    public String getCOLUMN_5() {
        return COLUMN_5;
    }

    public String getCOLUMN_6() {
        return COLUMN_6;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate);
    }
}
