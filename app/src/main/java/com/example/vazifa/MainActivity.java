package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{
    private FloatingActionButton btnAddTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtons();
    }

    private void addListenerOnButtons() {
        btnAddTask = (FloatingActionButton) findViewById(R.id.AddButton);
        btnAddTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent AddTask = new Intent(MainActivity.this, AddTask_activity.class);
                        startActivity(AddTask);
                    }
                }
        );
    }
}
