package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {


    FloatingActionButton addButton;
    ListView topList;
    ListView bottomList;
    VazifaDataBase dataBase;
    Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    //  # Инициализация Кнопки и Лист Вю
        addButton  = (FloatingActionButton)findViewById(R.id.addButton);  //# -
        topList =(ListView)findViewById(R.id.TopListView);
        bottomList =(ListView)findViewById(R.id.BottomListView);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

    //  # Инициализация Базы данных
        dataBase=new VazifaDataBase(MainActivity.this);

        updateBottomList();
        updateTopList();


    //  Если нажать на задачу
        topList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataBase.addTask( (Task)parent.getItemAtPosition(position), DataBaseType.Completed );      // Добовляем выполненную задачу в в другую таблицу базы данных
                dataBase.deleteTask( (Task)parent.getItemAtPosition(position), DataBaseType.UnCompleted ); // Удаляем из первой базы данных

                updateTopList();
                updateBottomList();
            }
        });


        setOnItemLongClickListener(bottomList, DataBaseType.Completed);
        setOnItemLongClickListener(topList,DataBaseType.UnCompleted);


    //  # Он клик на Кнопку "Плюс"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // ------- Запуск АддТаск активити -------
                startActivity(new Intent(MainActivity.this,AddTask_activity.class));
            }
        });
    }

    public void updateTopList(){
        //  Обновляем Список Невыполненных задач
        topList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,android.R.layout.simple_list_item_multiple_choice,dataBase.getEvery(DataBaseType.UnCompleted)));
        topList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
    }

    public void updateBottomList(){
        //  Обновляем Список Выполненных задач
        bottomList.setAdapter(new ArrayAdapter<Task>(MainActivity.this,R.layout.custom_listview,R.id.CustomTextView,dataBase.getEvery(DataBaseType.Completed)));
    }

    public void setOnItemLongClickListener(final ListView list, final DataBaseType type){
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String dbType = (type==DataBaseType.Completed) ? "Completed"
                                                               : "UnCompleted";

                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,findViewById(R.id.addButton),"shared_element_to_task");
                Intent intent = new Intent(MainActivity.this,TaskActivity.class);
                intent.putExtra("SelectedTaskId" , ( (Task)parent.getItemAtPosition(position)).getId() );
                intent.putExtra("SelectedTaskType",dbType);

                startActivity(intent,optionsCompat.toBundle());

                return true;

            }
        });

    }


}













