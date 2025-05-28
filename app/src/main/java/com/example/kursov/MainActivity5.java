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

public class MainActivity5 extends AppCompatActivity {

    private DatabaseHelper2 dbHelper2 = new DatabaseHelper2(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
               Button savebutton = findViewById(R.id.button4);

                EditText nametext = findViewById(R.id.number);
                EditText salarytext = findViewById(R.id.address);


                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nametext.getText().toString();
                        String salary = salarytext.getText().toString();


                        dbHelper2.addWorker(new FIL(1, salary,name));

                    }
                });}}
