package com.example.vazifa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {


    TextView name;
    TextView desc;
    VazifaDataBase dataBase;
    int taskId;
    DataBaseType type;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        name=(TextView)findViewById(R.id.TaskName);
        desc=(TextView)findViewById(R.id.TaskDesc);

        taskId = getIntent().getExtras().getInt("SelectedTaskId");

        dataBase = new VazifaDataBase(TaskActivity.this);

        type = (getIntent().getExtras().getString("SelectedTaskType").equals("UnCompleted")) ? DataBaseType.UnCompleted
                                                                                                    : DataBaseType.Completed;

        name.setText( dataBase.getTask(taskId,type).getName() );
        desc.setText( dataBase.getTask(taskId,type).getDescription() );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addtask_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dataBase.deleteTask(new Task(taskId," "," "," "),type);
        startActivity(new Intent(this,MainActivity.class));

        return super.onOptionsItemSelected(item);
    }


}
