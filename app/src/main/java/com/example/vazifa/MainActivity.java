package com.example.vazifa;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

//  # Инициализация Кнопки +
    FloatingActionButton AddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddButton=findViewById(R.id.AddButton);

    //  # Он клик
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // ------- Запуск АддТаск активити -------
                Intent intent = new Intent(MainActivity.this,AddTask_activity.class);
                startActivity(intent);
            }
        });



    }



}









