package com.example.vazifa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class TaskActivity extends AppCompatActivity {

    String initialName;
    String initialDescription;

    EditText name;
    EditText desc;
    DataBase dataBase;
    int taskId;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        name      =  (EditText)findViewById(R.id.TaskName);
        desc      = (EditText)findViewById(R.id.TaskDesc);

        taskId = getIntent().getExtras().getInt("SelectedTaskId");

        dataBase = new DataBase(TaskActivity.this);



        initialName        = dataBase.get(taskId).getName();
        initialDescription = dataBase.get(taskId).getDescription();


        name.setText( initialName );
        desc.setText( initialDescription );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.trash:
                dataBase.delete(new Task(taskId," "," "," ",false));
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.checkmark1:
                if(!initialName.equals(name.getText().toString()) || !initialDescription.equals(desc.getText().toString()))
                    dataBase.edit(new Task(taskId,name.getText().toString(),desc.getText().toString(),"4",false));

                startActivity(new Intent(this,MainActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }





}
