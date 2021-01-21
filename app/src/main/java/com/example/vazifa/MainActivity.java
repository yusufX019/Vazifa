package com.example.vazifa;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    static RecyclerView recyclerView;
    static DataBase dataBase;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Инициализация элементов интерфейса
        addButton    = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.list);

        // Инициализация базы данных
        dataBase = new DataBase(this);

        //
        addButton.setOnClickListener(onButtonClick);

        setRecyclerView();
        setRecyclerViewDivider();
    }

    private void setRecyclerViewDivider() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),(new LinearLayoutManager(this).getOrientation()));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setRecyclerView() {
        TaskList taskList=new TaskList(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(taskList.getAsSorted()));
    }


    AdapterView.OnItemClickListener onListItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // типа анимация
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, findViewById(R.id.addButton), "shared_element_to_task");
            Intent intent = new Intent(MainActivity.this, TaskActivity.class);

            /** Потому что я пока не нашел способа как передать объект класса Task в другое активити через Интент
             *  будет передаватся поле "Id" класса Task */
            intent.putExtra("SelectedTaskId", ((Task) parent.getItemAtPosition(position)).getId());
            // Наченаем новое активити
            startActivity(intent, optionsCompat.toBundle());
        }
    };

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // ------- Запуск АддТаск активити -------
            startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
        }
    };


    public static Context getContext(){
        return getContext();
    }


}









