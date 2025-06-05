package com.example.kursov;

import static com.example.kursov.MainActivity.FLAG_EXTRA;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private DatabaseHelper3 dbHelper;

    public void onNextActivity(boolean flag){
        Intent intent = new Intent(this, DACT.class);
        intent.putExtra(FLAG_EXTRA, flag);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        boolean flag = getIntent().getBooleanExtra(FLAG_EXTRA, false);

        dbHelper = new DatabaseHelper3(this);

        Button saveButton = findViewById(R.id.button4);
        EditText nameText = findViewById(R.id.name);
        EditText salaryText = findViewById(R.id.oklad);
        ImageButton backbutton = findViewById(R.id.imageButton5);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextActivity(flag);
            }
        });

        saveButton.setOnClickListener(v -> {
            String name = nameText.getText().toString();
            String salary = salaryText.getText().toString();

            if (name.isEmpty() || salary.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            // Создаем объект Position (передаем salary как String)
            Position position = new Position(1, name, salary);

            // Добавляем в базу и проверяем результат
            boolean isInserted = dbHelper.addWorker(position);

            if (isInserted) {
                Toast.makeText(this, "Данные успешно добавлены", Toast.LENGTH_SHORT).show();
                Log.d("DB_INSERT", "Добавлена запись: " + name + ", " + salary);

                // Очищаем поля после успешного добавления
                //nameText.setText("");
                //salaryText.setText("");

                // Закрываем активити после добавления
                finish();
            } else {
                Toast.makeText(this, "Ошибка при добавлении данных", Toast.LENGTH_SHORT).show();
                Log.e("DB_INSERT", "Ошибка при добавлении: " + name + ", " + salary);
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}