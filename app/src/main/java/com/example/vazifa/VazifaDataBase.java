package com.example.vazifa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


class VazifaDataBase extends SQLiteOpenHelper{

    private static final String DB_name ="Main_DB"; //name of the database
    private static int DB_version          =1;      //version

    //Конструктор Класса
    VazifaDataBase(@Nullable Context context){
        super(context,DB_name,null,DB_version);
    };

    //Главная таблица
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Main("             //название таблицы Main
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text,"
                + "date date );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
