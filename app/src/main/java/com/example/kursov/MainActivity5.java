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

public class MainActivity5 extends AppCompatActivity {

    private DatabaseHelper2 dbHelper2 = new DatabaseHelper2(this);

    public void onNextActivity(boolean flag){
        Intent intent = new Intent(this, DatabaseViewActivity2.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        Button savebutton = findViewById(R.id.button4);
        boolean flag = getIntent().getBooleanExtra(FLAG_EXTRA, false);

        EditText nametext = findViewById(R.id.number);
        EditText salarytext = findViewById(R.id.address);
        ImageButton backbutton = findViewById(R.id.imageButton6);

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


                dbHelper2.addWorker(new FIL(1, salary, name));

            }
        });
    }
}