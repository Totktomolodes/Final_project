package com.example.final_book_explorer_project.screen_handlers;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_book_explorer_project.R;


public class MainActivity extends AppCompatActivity {
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_panel);


        EditText username_plain_text = findViewById(R.id.username_plain_text);
        username_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        EditText password_plain_text = findViewById(R.id.password_plain_text);
        password_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        Button button_register = findViewById(R.id.button_register);

        button_register.setOnClickListener(v -> {
            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {
                myDataBaseHelper.AddToDB(username_plain_text.getText().toString().trim(),
                        password_plain_text.getText().toString().trim());
                TextView textView_main_page = findViewById(R.id.textView_of_main_page);
                textView_main_page.setText("Вы успешно зарегистрировались");
//Todo                ScreenHelper.replaceFragment(Myfragment.class, getSupportFragmentManager());




            }

        });
        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(v -> {
            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {
                boolean existence = myDataBaseHelper.isUserInDB(username_plain_text.getText().toString().trim());
                TextView textView_main_page = findViewById(R.id.textView_of_main_page);
                if(existence){
                    textView_main_page.setText("Вы вошли в аккаунт");

                }
                else {
                    textView_main_page.setText("Пользователь не найден");
                }
            }
        });

        ImageButton hide_text_button = findViewById(R.id.hide_text_button);
        hide_text_button.setOnClickListener(v -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        });


    }
}