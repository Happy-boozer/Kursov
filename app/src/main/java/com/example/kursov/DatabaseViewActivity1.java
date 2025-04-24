package com.example.kursov;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseViewActivity1 extends AppCompatActivity {

    private DatabaseHelper1 dbHelper;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sdatabase);

        dbHelper = new DatabaseHelper1(this);
        tableLayout = findViewById(R.id.tableLayout);

        displayDataInTable();
    }

    private void displayDataInTable() {
        // Очищаем таблицу (кроме заголовков)
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
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