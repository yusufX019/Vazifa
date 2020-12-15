package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class AddTask_activity extends AppCompatActivity {

//  Компоненты
    Button saveButton;
    EditText name;
    EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
//      # Инициализация компонентов
        saveButton= findViewById(R.id.saveButton);
        name=findViewById(R.id.Name_TextInput);
        desc=findViewById(R.id.desc_TextInput);

//      # Он Клик
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //  ---Создание Объекта Базы данных
                VazifaDataBase db=new VazifaDataBase(AddTask_activity.this);

            //  ---Создание Обьекта новой задачи
                if(name.getText().toString().trim().length()>0) {                    //   -------                Если Поле для ввода не пустое
                //  # Добавление в Класс нового объекта
                    Task newTask = new Task(name.getText().toString(), desc.getText().toString(), "15-12-2020");

                //         ---------- Обработка Результатов Добавления в базу Данных ----------
                    if(db.addTask(newTask)){ // в случае успеха вывести СнекБар
                        Snackbar.make(v,R.string.Succes_Add,Snackbar.LENGTH_LONG).show();

                    //  ---После того как Задача было добавлено Снова вызываем MainActivity
                        Intent intent = new Intent(AddTask_activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else                     // в случае неудачи вывести СнекБар с соответсвующим сообщением
                        Snackbar.make(v,R.string.Fail_Add,Snackbar.LENGTH_SHORT).show();

                }
                else                                                               //   -------                Если Поле для ввода пустое
                    Toast.makeText(AddTask_activity.this,R.string.AskToEnter,Toast.LENGTH_SHORT).show();


            }
        });
    }




}