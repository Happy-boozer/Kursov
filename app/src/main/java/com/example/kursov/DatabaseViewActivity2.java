package com.example.kursov;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseViewActivity2 extends AppCompatActivity {

    public void onNextActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private DatabaseHelper2 dbHelper;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sdatabase2);

        dbHelper = new DatabaseHelper2(this);
        tableLayout = findViewById(R.id.tableLayout);

        displayDataInTable();

        Button backbutton = findViewById(R.id.button2);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity();
            }
        });
    }

    private void displayDataInTable() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Проверяем существование таблицы
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='fils'", null);
        if (!cursor.moveToFirst()) {
            // Таблицы не существует, создаем ее
            dbHelper.onCreate(db);
        }
        cursor.close();

        // Очищаем таблицу (кроме заголовков)
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        //SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM fils", null);

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