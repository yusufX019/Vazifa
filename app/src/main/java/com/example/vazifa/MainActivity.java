package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class MainActivity extends AppCompatActivity {

    public static final String SELECTED_TASK_ID="SelectedTaskID";
    FloatingActionButton addButton;
    ListView topList;
    ListView bottomList;
    VazifaDataBase dataBase;


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

        updateBottomList();
        updateTopList();


    //  Если нажать на задачу
        topList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataBase.addTask( (Task)parent.getItemAtPosition(position), VazifaDataBase.Type.Completed );      // Добовляем выполненную задачу в в другую таблицу базы данных
                dataBase.deleteTask( (Task)parent.getItemAtPosition(position), VazifaDataBase.Type.UnCompleted ); // Удаляем из первой базы данных

                updateTopList();
                updateBottomList();

            }
        });

    //
        topList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,findViewById(R.id.addButton),"shared_element_to_task");
                Intent intent = new Intent(MainActivity.this,TaskActivity.class);
                intent.putExtra(SELECTED_TASK_ID, ( (Task)parent.getItemAtPosition(position)).getId() );
                startActivity(intent,optionsCompat.toBundle());
                return true;


            }
        });


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

    public void updateTopList(){
        //  Обновляем Список Невыполненных задач
        topList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,dataBase.getEvery(VazifaDataBase.Type.UnCompleted)));
        topList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
    }

    public void updateBottomList(){
        //  Обновляем Список Выполненных задач
        bottomList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,R.layout.custom_listview,R.id.CustomTextView,dataBase.getEvery(VazifaDataBase.Type.Completed)));
    }


}













