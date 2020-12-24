package com.example.vazifa;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import com.google.android.material.textfield.TextInputLayout;




public class AddTask_activity extends AppCompatActivity {

//  Компоненты

    EditText name;
    EditText desc;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);



//      # Инициализация компонентов
        name      = ((TextInputLayout)findViewById(R.id.Name_TextInput)).getEditText();
        desc      = ((TextInputLayout)findViewById(R.id.desc_TextInput)).getEditText();



        name.requestFocus();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addtask_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //  ---Создание Объекта Базы данных
        VazifaDataBase db=new VazifaDataBase(AddTask_activity.this);

        // Если Поле для ввода не пустое
        if(name.getText().toString().trim().length()>0) {

            if(db.addTask( new Task(0,name.getText().toString(), desc.getText().toString(), "17-12-2020"), DataBaseType.UnCompleted)){
                Snackbar.make(item.getActionView(),R.string.Succes_Add,Snackbar.LENGTH_LONG).show();
                startActivity( new Intent(AddTask_activity.this,MainActivity.class));
            }
            else Snackbar.make(item.getActionView(),R.string.Fail_Add,Snackbar.LENGTH_SHORT).show();

        }
        // Если Поле для ввода пустое
        else Toast.makeText(AddTask_activity.this,R.string.AskToEnter,Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}