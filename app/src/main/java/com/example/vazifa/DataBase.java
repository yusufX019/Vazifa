package com.example.vazifa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;


import androidx.annotation.Nullable;

import java.util.ArrayList;

import java.util.List;


class DataBase extends SQLiteOpenHelper{

    private static final String DB_name ="MainDataBase"; //Название Базы Данных
    private static int DB_version          =1;           //Версия базы Данных





    //Конструктор Класса
    DataBase(@Nullable Context context){
        super(context,DB_name,null,DB_version);
    };





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Tasks("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text,"
                + "date date,"
                + "completed integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean getBool(int i){ return i==1;}
    private int getInt (boolean b){return b?1:0;}

//  Функция которая добовляет в базу данных новую запись
    public boolean add(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",task.getName());
        values.put("description",task.getDescription());
        values.put("date",task.getDate());
        values.put("completed",0);


        return db.insert("Tasks", null, values) != -1;

    }

//  Функция которая выдающая всю информацию о задачах задач из базы данных
    public List<Task> getAll(){
        List<Task> finalList = new ArrayList<>();
        String query = "Select * from Tasks";
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do{
                int    id  = cursor.getInt   (0);
                String name= cursor.getString(1);
                String desc= cursor.getString(2);
                String date= cursor.getString(3);
                int completed=cursor.getInt  (4);

                finalList.add(new Task(id,name,desc,date,getBool(completed)));

            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return finalList;

    }


    public Task get(int taskId){
        Task resultedTask;
        String query = "Select * from Tasks where id =" +taskId;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor=database.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String date = cursor.getString(3);
                int completed=cursor.getInt(4);

                resultedTask = new Task(id, name, desc, date,getBool(completed));

            } while (cursor.moveToNext());
        }
        else resultedTask=null;



        cursor.close();
        database.close();

        return  resultedTask;

    }

    //
    public boolean delete(Task task){
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "Delete from Tasks where id="+task.getId() ;

        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst())    return true;
        else                        return false;

    }


    //TODO Слишком сложный код нужно изменить
    public void edit(Task task){
        SQLiteDatabase database =this.getWritableDatabase();
        String query1 = "Update Tasks set " +
                "name='" +task.getName() +
                "',description='" +task.getDescription()+
                "',completed="+getInt(task.isCompleted())+
                " where id ="+task.getId();


        database.execSQL(query1);

    }

}
