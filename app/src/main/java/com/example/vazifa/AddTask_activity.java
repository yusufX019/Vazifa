package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.textfield.TextInputLayout;




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
        name      = ((TextInputLayout)findViewById(R.id.Name_TextInput)).getEditText();
        desc      = ((TextInputLayout)findViewById(R.id.desc_TextInput)).getEditText();

//      # Он Клик
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //  ---Создание Объекта Базы данных
                VazifaDataBase db=new VazifaDataBase(AddTask_activity.this);

                // Если Поле для ввода не пустое
                if(name.getText().toString().trim().length()>0) {

                    if(db.addTask( new Task(0,name.getText().toString(), desc.getText().toString(), "17-12-2020"), DataBaseType.UnCompleted)){
                        Snackbar.make(v,R.string.Succes_Add,Snackbar.LENGTH_LONG).show();
                        startActivity( new Intent(AddTask_activity.this,MainActivity.class));
                    }
                    else Snackbar.make(v,R.string.Fail_Add,Snackbar.LENGTH_SHORT).show();

                }
                // Если Поле для ввода пустое
                else Toast.makeText(AddTask_activity.this,R.string.AskToEnter,Toast.LENGTH_SHORT).show();


            }
        });
    }


}