package com.example.kursov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kursov.DatabaseViewActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button mybutton = findViewById(R.id.button);

        addSampleData();

        mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, DatabaseViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addSampleData() {
        // Добавление одной записи
        dbHelper.addWorker(new Worker(1, "Иван Иванов", "Колорист", "80000", "5 лет"));
        //Toast.makeText(this, "Добавлена запись с ID: " + id, Toast.LENGTH_SHORT).show();

        // Добавление нескольких записей
            /*List<Worker> persons = new ArrayList<>();
            persons.add(new Worker("Петр Петров", 25, "petr@example.com"));
            persons.add(new Worker("Анна Сидорова", 28, "anna@example.com"));
            persons.add(new Worker("Сергей Сергеев", 35, "sergey@example.com"));

            dbHelper.insertMultipleData(persons);*/
        //Toast.makeText(this, "Добавлено " + persons.size() + " записей", Toast.LENGTH_SHORT).show();
    }
}