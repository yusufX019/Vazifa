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

    private static final String DB_name ="Main_DB"; //Название Базы Данных
    private static int DB_version          =1;      //Версия базы Данных




    //Конструктор Класса
    VazifaDataBase(@Nullable Context context){
        super(context,DB_name,null,DB_version);
    };

    /** В одной Базе данных будет создано две таблицы **/

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем Две Таблицы, одна будет хранить данные о невыполненных задачах а вторая о уже выполненных
        db.execSQL("Create Table UnCompleted("             //название таблицы UnCompleted
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text,"
                + "date date );");

        db.execSQL("Create Table Completed("             //название таблицы Completed
                + "id integer primary key,"
                + "name text,"
                + "description text,"
                + "date date );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//  Функция которая добовляет в базу данных новую запись
    public boolean addTask(Task task, DataBaseType type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",task.getName());
        values.put("description",task.getDescription());
        values.put("date",task.getDate());

        if(type==DataBaseType.UnCompleted)
            return db.insert("UnCompleted", null, values) != -1;
        else
            return db.insert("Completed", null, values) != -1;

    }

//  Функция которая выдающая всю информацию о задачах задач из базы данных
    public List<Task> getEvery(DataBaseType type){
        List<Task> finalList = new ArrayList<>();
        String query = (type==DataBaseType.UnCompleted) ? "Select * from UnCompleted" : "Select * from Completed";

        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()) {
            do{
                int    id  = cursor.getInt   (0);
                String name= cursor.getString(1);
                String desc= cursor.getString(2);
                String date= cursor.getString(3);

                finalList.add(new Task(id,name,desc,date));

            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return finalList;

    }


    public Task getTask(int taskId,DataBaseType type){
        Task resultedTask;
        String query = (type==DataBaseType.Completed) ? "Select * from   Completed where id =" +taskId
                                              : "Select * from UnCompleted where id =" +taskId;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor=database.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String date = cursor.getString(3);

                resultedTask = new Task(id, name, desc, date);

            } while (cursor.moveToNext());
        }
        else resultedTask = new Task(-2,"Error","DataBaseError","#45%%");



        cursor.close();
        database.close();

        return  resultedTask;

    }

    // # Нужно фиксить
    public boolean deleteTask(Task task,DataBaseType type){

        SQLiteDatabase database = this.getWritableDatabase();
        String query = (type==DataBaseType.UnCompleted)? "Delete from UnCompleted where id="+task.getId()    // if
                                                         :"Delete from   Completed where id="+task.getId();   // else

        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst())    return true;
        else                        return false;

    }
}
