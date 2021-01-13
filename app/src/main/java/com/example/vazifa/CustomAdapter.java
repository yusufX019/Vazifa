package com.example.vazifa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;

import java.util.List;

/** This class is custom array adapter that extends ArrayAdapter with type of 'Task' */

public class CustomAdapter extends ArrayAdapter<Task> {
    private List<Task> tasks;   //List of tasks
    private Activity context;

    // Constructor
    public CustomAdapter(Activity context, List<Task> tasks){
        super(context,R.layout.list_with_checkbox,tasks);

        this.context=context;
        this.tasks=tasks;

    }

    // in these method i will realise all the manipulations with listView
    //--
    @Override
    public View getView(final int postion, View view, ViewGroup parent){


        if(view==null) {
            // getting view via layoutInflater
            view = context.getLayoutInflater().inflate(R.layout.list_with_checkbox, null, true);

            //Initializing textview and checkbox
            TextView textview=(TextView)view.findViewById(R.id.text_inList);
            CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkbox_inList);

            //setting the text of TextView as the name of the task
            textview.setText(tasks.get(postion).getName());

           final DataBase dataBase = new DataBase(context);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataBase.addTask( tasks.get(postion), DataBaseType.Completed );      // Добовляем выполненную задачу в в другую таблицу базы данных
                    dataBase.deleteTask( tasks.get(postion), DataBaseType.UnCompleted ); // Удаляем из первой базы данных

                    MainActivity.updateBottomList(context);
                    MainActivity.updateTopList(context);

                }
            });

        }





        return view;

    }


}
