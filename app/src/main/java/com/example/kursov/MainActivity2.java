package com.example.kursov;

import static com.example.kursov.MainActivity.FLAG_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private DatabaseHelper1 dbHelper1 = new DatabaseHelper1(this);

    public void onNextActivity(boolean flag){
        Intent intent = new Intent(this, DatabaseViewActivity1.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        boolean flag = getIntent().getBooleanExtra(FLAG_EXTRA, false);
        setContentView(R.layout.activity_main2);

        Button savebutton = findViewById(R.id.save);

        EditText nametext = findViewById(R.id.name);
        EditText salarytext = findViewById(R.id.salary);
        EditText positiontext = findViewById(R.id.position);
        EditText expr = findViewById(R.id.exp);
        ImageButton backbutton = findViewById(R.id.imageButton4);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity(flag);
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametext.getText().toString();
                String salary = salarytext.getText().toString();
                String position = positiontext.getText().toString();
                String exp = expr.getText().toString();

                dbHelper1.addWorker(new Worker(1, name, position, salary, exp));

            }
        });
    }
}