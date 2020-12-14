package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTask_activity extends AppCompatActivity implements OnClickListener {
    private FloatingActionButton sendTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        sendTask = (FloatingActionButton) findViewById(R.id.AddedTask);
        sendTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddedTask:
                // Task should be added to data base
                break;
            default:
                break;
        }
    }
}