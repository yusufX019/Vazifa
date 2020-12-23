package com.example.vazifa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {


    TextView name;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        name=(TextView)findViewById(R.id.TaskName);
        desc=(TextView)findViewById(R.id.TaskDesc);

        int taskId = getIntent().getExtras().getInt("SelectedTaskId");

        VazifaDataBase dataBase = new VazifaDataBase(TaskActivity.this);

        DataBaseType type = (getIntent().getExtras().getString("SelectedTaskType").equals("UnCompleted")) ? DataBaseType.UnCompleted
                                                                                                               : DataBaseType.Completed;

        name.setText( dataBase.getTask(taskId,type).getName() );
        desc.setText( dataBase.getTask(taskId,type).getDescription() );


    }


}
