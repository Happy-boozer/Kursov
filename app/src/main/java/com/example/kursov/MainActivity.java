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

    public static final String FLAG_EXTRA = "flag_extra";


    public void onNextActivity(boolean flag) {
        Intent intent = new Intent(this, DatabaseViewActivity1.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    public void onNextActivity2(boolean flag) {
        Intent intent = new Intent(this, DatabaseViewActivity2.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    public void onNextActivity3(boolean flag) {
        Intent intent = new Intent(this, DACT.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }


    private DatabaseHelper1 dbHelper1 = new DatabaseHelper1(this);
    //private DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(this);
    String[] tabels = {"Сотрудники", "Филлиалы", "Должности"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        boolean flag = getIntent().getBooleanExtra(MainActivity4.FLAG_EXTRA, false);

        ListView workers = (ListView) findViewById(R.id.list_item);

        ArrayAdapter<String> workersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tabels);
        workers.setAdapter(workersAdapter);

        workers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = tabels[position];
                switch (selectedItem) {
                    case "Сотрудники":
                        //addSampleData();
                        onNextActivity(flag);
                        break;
                    case "Филлиалы":
                        onNextActivity2(flag);
                        break;
                    case "Должности":
                        onNextActivity3(flag);
                        break;

                }


            }
        });

    }
}