package com.example.kursov;

import static com.example.kursov.MainActivity.FLAG_EXTRA;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DACT extends AppCompatActivity {

    private DatabaseHelper3 dbHelper;
    private TableLayout tableLayout;
    //static final boolean flagm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //insertTestData();
        //dbHelper.clearWorkersTable();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sdatabase3);


        dbHelper = new DatabaseHelper3(this);
        tableLayout = findViewById(R.id.tableLayout);
        boolean flag = getIntent().getBooleanExtra(FLAG_EXTRA, false);
        final boolean flagm = flag;
        insertTestData();

        // Инициализация UI
        initUI(flagm);

        // Загрузка данных при создании активити
        loadData();
    }

    private void initUI(boolean flag) {
        ImageButton backbutton = findViewById(R.id.imageButton3);
        Button addbutton = findViewById(R.id.button3);

        if (flag) {
            addbutton.setVisibility(View.GONE);
        } else {
            addbutton.setVisibility(View.VISIBLE);
        }

        backbutton.setOnClickListener(v -> onNextActivity(flag));
        addbutton.setOnClickListener(v -> {
            onNextActivity2(flag);
            // После возврата из MainActivity3 обновляем данные
            //loadData();
        });
    }

    public void onNextActivity(boolean flag) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    public void onNextActivity2(boolean flag) {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем данные при возвращении в активити
        //loadData();
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='positions'", null
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    private void loadData() {
        tableLayout.removeAllViews();

        SQLiteDatabase db = null;
        Cursor cursor = null;


            db = dbHelper.getReadableDatabase();

            // Проверяем и создаем таблицу если нужно
            if (!isTableExists(db, "positions")) {
                dbHelper.onCreate(db);
                //insertTestData(); // Добавляем тестовые данные после создания таблицы
            }

            cursor = db.rawQuery("SELECT * FROM positions", null);

            if (cursor.getCount() == 0) {
                showEmptyTableMessage();
                return;
            }

            createTableHeaders();

            while (cursor.moveToNext()) {
                addTableRow(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
            }

    }

    private void createTableHeaders() {
        TableRow headerRow = new TableRow(this);
        String[] headers = {"ID", "Должность", "Оклад"};

        for (String header : headers) {
            TextView textView = new TextView(this);
            textView.setText(header);
            textView.setPadding(16, 16, 16, 16);
            textView.setBackgroundResource(R.drawable.cell_border);
            textView.setTypeface(null, Typeface.BOLD);
            headerRow.addView(textView);
        }
        tableLayout.addView(headerRow);
    }

    private void addTableRow(int id, String position, String salary) {
        TableRow row = new TableRow(this);

        TextView idView = createTableCell(String.valueOf(id));
        TextView posView = createTableCell(position);
        TextView salView = createTableCell(salary);

        row.addView(idView);
        row.addView(posView);
        row.addView(salView);

        tableLayout.addView(row);
    }

    private TextView createTableCell(String text) {
        TextView textView = new TextView(this);
        textView.setText(text != null ? text : "");  // Гарантируем не-null текст
        textView.setPadding(16, 16, 16, 16);
        textView.setBackgroundResource(R.drawable.cell_border);

        // Применяем стиль ТОЛЬКО если текст не пустой
//        if (!text.isEmpty()) {
//            textView.setTypeface(null, Typeface.BOLD);
//        }

        return textView;
    }

    private void showEmptyTableMessage() {
        TextView message = new TextView(this);
        message.setText("Нет данных для отображения");
        message.setTextSize(18);
        message.setPadding(16, 50, 16, 16);
        message.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tableLayout.addView(message);
    }

    private void insertTestData() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // Очищаем таблицу перед добавлением тестовых данных
            db.delete("positions", null, null);

            // Массив тестовых данных
            String[][] testData = {
                    {"1", "Manager", "50000"},
                    {"2", "Developer", "100000"},
                    {"3", "Master", "40000"},
                    {"4", "GENdir", "1000000"}
            };

            for (String[] data : testData) {
                ContentValues values = new ContentValues();
                values.put("id", data[0]);
                values.put("name", data[1]);
                values.put("oklad", data[2]);

                long result = db.insert("positions", null, values);
                if (result == -1) {
                    Log.e("DB_INSERT", "Ошибка вставки: " + data[1]);
                }
            }

            db.setTransactionSuccessful();
            Log.d("DB_INSERT", "Тестовые данные успешно добавлены");
        } catch (Exception e) {
            Log.e("DB_INSERT", "Ошибка вставки тестовых данных", e);
        } finally {
            if (db != null) {
                try {
                    db.endTransaction();
                    db.close();
                } catch (Exception e) {
                    Log.e("DB", "Ошибка закрытия БД", e);
                }
            }}}


    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}