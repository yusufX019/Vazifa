package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
                Task newTask=new Task(name.getText().toString(),desc.getText().toString(),"15-12-2020");

            //  ---Обработка
                if(db.AddTask(newTask)) // в случае успеха вывести ТОАСТ
                    Toast.makeText(AddTask_activity.this,R.string.Succes_Add,Toast.LENGTH_SHORT).show();
                else                    // в случае неудачи вывести ТОАСТ с соответсвующим сообщением
                    Toast.makeText(AddTask_activity.this,R.string.Fail_Add,Toast.LENGTH_SHORT).show();
            }
        });
    }




}