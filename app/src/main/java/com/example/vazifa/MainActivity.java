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


    FloatingActionButton addButton; //  # Объявление Кнопки "+"
    ListView MainList;              //  # Лист Вю
    VazifaDataBase dataBase;        //  # Объект Базы Данных
    ArrayAdapter TaskArrayAdapter;  //  #


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //  # Инициализация Новаой Базы Данных
        dataBase = new VazifaDataBase(MainActivity.this);

    //  # Инициализация Кнопки и Лист Вю
        addButton=(FloatingActionButton) findViewById(R.id.addButton);  //# -
        MainList =(ListView)             findViewById(R.id.ListView);   //# -

    //  # Обновляем Список Задач
        UpdateListView();




    //  # Он клик на Кнопку "Плюс"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // ------- Запуск АддТаск активити -------
                Intent intent = new Intent(MainActivity.this,AddTask_activity.class);
                startActivity(intent);
            }
        });





    }

//  # Эта функция Обноваляет Список на Главном Актвити MainActivity
    public void UpdateListView(){
        //  # АррейАдаптер для ЛистВю
        //    Пока что простой список simple_list item_1 , Есть другие виды списков в том числе с ЧекБоксом, Нужно ПроГуглить в инете и внедрить
        TaskArrayAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,dataBase.getAllNames());
        MainList.setAdapter(TaskArrayAdapter);
    }



}









