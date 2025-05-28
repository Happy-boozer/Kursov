package com.example.kursov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void onNextActivity(){
        Intent intent = new Intent(this, DatabaseViewActivity1.class);
        startActivity(intent);
    }

    public void onNextActivity2(){
        Intent intent = new Intent(this, DatabaseViewActivity2.class);
        startActivity(intent);
    }

    public void onNextActivity3(){
        Intent intent = new Intent(this, DACT.class);
        startActivity(intent);
    }


    private DatabaseHelper1 dbHelper1 = new DatabaseHelper1(this);
    //private DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);
    String [] tabels = {"Сотрудники", "Филлиалы", "Должности"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView workers = (ListView)findViewById(R.id.list_item);

        ArrayAdapter<String> workersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tabels);
        workers.setAdapter(workersAdapter);

        workers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = tabels[position];
                switch (selectedItem) {
                    case "Сотрудники":
                        //addSampleData();
                        onNextActivity();
                        break;
                    case "Филлиалы":
                        onNextActivity2();
                        break;
                    case "Должности":
                        onNextActivity3();
                        break;

                }


            }
        });

        //Button mybutton = findViewById(R.id.button);



        //dbHelper1.clearWorkersTable();
        //addSampleData();

        /*mybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, DatabaseViewActivity1.class);
                startActivity(intent);
            }
        });*/

    }

//    private void addSampleData() {
//        // Добавление одной записи
//        dbHelper1.addWorker(new Worker(1, "Иван Иванов", "Колорист", "80000", "5 лет"));
//    }
}
