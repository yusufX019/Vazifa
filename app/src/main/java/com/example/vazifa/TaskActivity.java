package com.example.vazifa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TaskActivity extends AppCompatActivity {

    String initialName;
    String initialDescription;

    EditText name;
    EditText desc;
    VazifaDataBase dataBase;
    int taskId;
    DataBaseType type;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        name      =  ((TextInputLayout)findViewById(R.id.TaskName)).getEditText();
        desc      = ((TextInputLayout)findViewById(R.id.TaskDesc)).getEditText();

        taskId = getIntent().getExtras().getInt("SelectedTaskId");

        dataBase = new VazifaDataBase(TaskActivity.this);

        type = (getIntent().getExtras().getString("SelectedTaskType").equals("UnCompleted")) ? DataBaseType.UnCompleted
                                                                                                  : DataBaseType.Completed;

        initialName        = dataBase.getTask(taskId,type).getName();
        initialDescription = dataBase.getTask(taskId,type).getDescription();


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
                dataBase.deleteTask(new Task(taskId," "," "," "),type);
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.checkmark1:
                if(!initialName.equals(name.getText().toString()) || !initialDescription.equals(desc.getText().toString()))
                    dataBase.editTask(new Task(taskId,name.getText().toString(),desc.getText().toString(),"0"),type);

                startActivity(new Intent(this,MainActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }





}
