package com.example.kursov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    private static final String CORRECT_WORD = "LOG"; // Заданное слово для проверки
    public static final String FLAG_EXTRA = "flag_extra"; // Ключ для передачи флага

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);

        Button enterButton = findViewById(R.id.button);
        EditText nameText = findViewById(R.id.textView);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем текст из поля ввода при нажатии
                String inputText = nameText.getText().toString().trim();
                // Проверяем совпадение с заданным словом
                boolean flag = inputText.equalsIgnoreCase(CORRECT_WORD);

                // Создаем Intent для перехода
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                // Передаем флаг в следующую активность
                intent.putExtra(FLAG_EXTRA, flag);
                startActivity(intent);
            }
        });
    }
}