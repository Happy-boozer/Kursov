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

        dbHelper.clearWorkersTable();
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
    }
}
