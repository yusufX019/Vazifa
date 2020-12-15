package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

//  # Инициализация Кнопки +
    FloatingActionButton addButton;

//  #Лист Вию
    ListView MainList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton=(FloatingActionButton) findViewById(R.id.addButton);
        MainList =(ListView)             findViewById(R.id.ListView);

    //  # Он клик
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // ------- Запуск АддТаск активити -------
                Intent intent = new Intent(MainActivity.this,AddTask_activity.class);
                startActivity(intent);
            }
        });

        VazifaDataBase dataBase = new VazifaDataBase(MainActivity.this);
        List<Task> everyTaskNames = dataBase.getEvery();

        ArrayAdapter TaskArrayAdapter=new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1,everyTaskNames);
        MainList.setAdapter(TaskArrayAdapter);

    }



}









