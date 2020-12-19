package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;




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
        addButton  = (FloatingActionButton)findViewById(R.id.addButton);  //# -
        topList =(ListView)findViewById(R.id.TopListView);
        bottomList =(ListView)findViewById(R.id.BottomListView);


    //  # Инициализация Базы данных
        dataBase=new VazifaDataBase(MainActivity.this);

    //  Обновляем Список Невыполненных задач
        topList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,dataBase.getEvery(VazifaDataBase.Type.UnCompleted)));
        topList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

    //  Обновляем Список Выполненных задач
        bottomList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,android.R.layout.simple_list_item_1,dataBase.getEvery(VazifaDataBase.Type.Completed)));

    //
        topList.setO


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




}













