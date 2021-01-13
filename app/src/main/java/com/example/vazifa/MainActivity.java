package com.example.vazifa;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class MainActivity extends AppCompatActivity {


    FloatingActionButton addButton;
    static ListView topList;
    static ListView bottomList;
    static DataBase dataBase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //  # Инициализация Кнопки и Лист Вю
        addButton  = (FloatingActionButton)findViewById(R.id.addButton);  //# -
        topList    = findViewById(R.id.TopListView);
        bottomList = findViewById(R.id.BottomListView);




    //  # Инициализация Базы данных
        dataBase=new DataBase(MainActivity.this);

        updateBottomList(MainActivity.this);
        updateTopList(MainActivity.this);










        setOnItemLongClickListener(bottomList, DataBaseType.Completed);
        setOnItemLongClickListener(topList,DataBaseType.UnCompleted);


    //  # Он клик на Кнопку "Плюс"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // ------- Запуск АддТаск активити -------
                startActivity(new Intent(MainActivity.this,AddTaskActivity.class));
            }
        });
    }


    // Эта функция обновляет верхний список
    public static void updateTopList(Activity context){
        topList.setAdapter(new CustomAdapter(context,dataBase.getAllTasks(DataBaseType.UnCompleted)));
    }

    // Эта функция обновляет нижний список
    public static void updateBottomList(Activity context){
        //  Обновляем Список Выполненных задач
        bottomList.setAdapter(new ArrayAdapter<Task>(context,R.layout.custom_listview,R.id.CustomTextView,dataBase.getAllTasks(DataBaseType.Completed)));
    }

    // Долгое нажатие
    public void setOnItemLongClickListener(final ListView list, final DataBaseType type){
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                String dbType = (type==DataBaseType.Completed) ? "Completed"
                                                               : "UnCompleted";

                // типа анимация
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,findViewById(R.id.addButton),"shared_element_to_task");

                // Создаем интент и начинаем новое активити
                Intent intent = new Intent(MainActivity.this,TaskActivity.class);
                intent.putExtra("SelectedTaskId" , ( (Task)parent.getItemAtPosition(position)).getId() ); // передаем Ид
                intent.putExtra("SelectedTaskType",dbType);                                                // и тип базы данных
                startActivity(intent,optionsCompat.toBundle());

                return true;

            }
        });

    }





}













