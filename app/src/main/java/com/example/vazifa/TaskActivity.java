package com.example.vazifa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {

    private static final String SELECTED_TASK_ID="SelectedTaskID";
    TextView name;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        name=(TextView)findViewById(R.id.TaskName);
        desc=(TextView)findViewById(R.id.TaskDesc);

        int taskID = getIntent().getExtras().getInt(SELECTED_TASK_ID);

        VazifaDataBase dataBase = new VazifaDataBase(TaskActivity.this);

        name.setText( dataBase.getTask(taskID, VazifaDataBase.Type.UnCompleted).getName() );
        desc.setText( dataBase.getTask(taskID, VazifaDataBase.Type.UnCompleted).getDescription().toString() );




    }


}
