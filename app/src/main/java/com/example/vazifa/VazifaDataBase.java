package com.example.vazifa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.ListView;

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

//  Функция которая добовляет в базу данных новую запись
    public boolean AddTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",task.getName());
        values.put("description",task.getDescription());
        values.put("date",task.getDate());

        if(db.insert("Main",null,values)==-1)   return false;
        else                                                         return true;
    }

//  Функция которая выдающая все названия задач из базы данных
    public List<Task> getEvery(){
        List<Task> finalList = new ArrayList<>();
        String query = "Select * from Main";
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do{
                String name= cursor.getString(1);
                String desc= cursor.getString(2);
                String date= cursor.getString(3);

                finalList.add(new Task(name,desc,date));

            }while (cursor.moveToNext());
        }
        else{
            //No result
        }
        cursor.close();
        database.close();

        return finalList;

    }




}
