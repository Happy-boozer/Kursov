package com.example.kursov;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private DatabaseHelper3 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        dbHelper = new DatabaseHelper3(this);

        Button saveButton = findViewById(R.id.button4);
        EditText nameText = findViewById(R.id.name);
        EditText salaryText = findViewById(R.id.oklad);

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