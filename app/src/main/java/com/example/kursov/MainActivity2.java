package com.example.kursov;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private DatabaseHelper1 dbHelper1 = new DatabaseHelper1(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Button savebutton = findViewById(R.id.save);

        EditText nametext = findViewById(R.id.name);
        EditText salarytext = findViewById(R.id.salary);
        EditText positiontext = findViewById(R.id.position);
        EditText expr = findViewById(R.id.exp);

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
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }
}