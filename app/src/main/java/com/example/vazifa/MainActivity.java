package com.example.vazifa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;




public class MainActivity extends AppCompatActivity {


    FloatingActionButton addButton; //  # Объявление Кнопки "+"
    ListView topList;
    ListView bottomList;
    VazifaDataBase dataBase;        //




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //  # Инициализация Кнопки и Лист Вю
        addButton  =(FloatingActionButton) findViewById(R.id.addButton);  //# -

        topList=(ListView)findViewById(R.id.topListView);
        bottomList=(ListView)findViewById(R.id.bottomListView);

    //  # Инициализация Базы данных
        dataBase=new VazifaDataBase(this);

    //  Обновляем Список Невыполненных задач
        topList.setAdapter(new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_multiple_choice,dataBase.getEvery(VazifaDataBase.Type.UnCompleted)));


    //  Обновляем Список Выполненных задач
        bottomList.setAdapter(new ArrayAdapter<Task>(this,android.R.layout.simple_list_item_1,dataBase.getEvery(VazifaDataBase.Type.Completed)));

    //
        //dataBase.deleteTask((Task)TopList.getItemAtPosition(TopList.getCheckedItemPosition()), VazifaDataBase.Type.UnCompleted);


    //  # Он клик на Кнопку "Плюс"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // ------- Запуск АддТаск активити -------
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,findViewById(R.id.addButton),"shared_element_to_add_task");
                startActivity(new Intent(MainActivity.this,AddTask_activity.class),optionsCompat.toBundle());
            }
        });
    }

    //  Класс Кастомного Адаптера для ЛистВю
    class MyCustomAdapter extends ArrayAdapter<String>{
        Context context;
        List<String> names;

       MyCustomAdapter(Context context, List<String> names){
            super(context,R.layout.mycustom_listview,R.id.TextView,names);
            this.context=context;
            this.names=names;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View custom_listview=layoutInflater.inflate(R.layout.mycustom_listview,parent,false);

            TextView text     =custom_listview.findViewById(R.id.TextView);
            CheckBox checkBox =custom_listview.findViewById(R.id.CheckBox);

            text.setText(names.get(position));

           return custom_listview;
        }



    }



}









