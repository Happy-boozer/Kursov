package com.example.kursov;

import static com.example.kursov.MainActivity.FLAG_EXTRA;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseViewActivity1 extends AppCompatActivity {

    private DatabaseHelper1 dbHelper;
    private TableLayout tableLayout;


    public void onNextActivity(boolean flag){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    public void onNextActivity2(boolean flag){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sdatabase1);
        boolean flag = getIntent().getBooleanExtra(FLAG_EXTRA, false);
        final boolean flagm = flag;

        dbHelper = new DatabaseHelper1(this);
        tableLayout = findViewById(R.id.tableLayout);

        displayDataInTable();

        ImageButton mybutton = findViewById(R.id.imageButton);
        Button mybutton1 = findViewById(R.id.button3);
        if (flagm) {
            mybutton1.setVisibility(View.GONE);
        } else {
            mybutton1.setVisibility(View.VISIBLE);
        }
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity(flagm);
            }
        });

        mybutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onNextActivity2(flagm);}
        });


    }

    private void displayDataInTable() {




        // Очищаем таблицу (кроме заголовков)
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='contacts'", null);
        //dbHelper.onUpgrade(db, 1, 2);
        if (!cursor1.moveToFirst()) {
            // Таблицы не существует, создаем ее
            dbHelper.onCreate(db);
        }
        cursor1.close();
        Cursor cursor = db.rawQuery("SELECT * FROM contacts", null);

        TableRow headerRow = new TableRow(this);
        String[] columnNames = cursor.getColumnNames();
        for (String columnName : columnNames) {
            TextView textView = new TextView(this);
            textView.setText(columnName);
            textView.setPadding(5, 5, 5, 5);
            textView.setBackgroundResource(R.drawable.cell_border);
            textView.setTypeface(null, Typeface.BOLD);
            headerRow.addView(textView);
        }

        tableLayout.addView(headerRow);

        // Добавляем данные
        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                TextView textView = new TextView(this);
                textView.setText(cursor.getString(i));
                textView.setPadding(5, 5, 5, 5);
                textView.setBackgroundResource(R.drawable.cell_border);
                row.addView(textView);
            }
            tableLayout.addView(row);
        }

        cursor.close();
        db.close();
    }


}