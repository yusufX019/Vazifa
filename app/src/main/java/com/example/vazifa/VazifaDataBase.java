package com.example.vazifa;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


class VazifaDataBase extends SQLiteOpenHelper{

    private static final String DB_name ="Main_DB"; //name of the database
    private static int DB_version          =1;         //version

    VazifaDataBase(Context context){
        super(context,DB_name,null,DB_version);
    };

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
