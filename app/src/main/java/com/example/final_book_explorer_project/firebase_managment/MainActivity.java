package com.example.final_book_explorer_project.firebase_managment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity2;
import com.example.final_book_explorer_project.screen_handlers.MyDataBaseHelper;
import com.example.final_book_explorer_project.user_managment.TextToHash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private EditText editText, username_plain_text, password_plain_text;
    private ImageButton hide_text_button;
    private Button button_register, button_login;
    private TextView textView_main_page;
    private DatabaseReference myDatabase;
    private String USER_KEY = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_panel);
        init();






        button_register.setOnClickListener(v -> { // setOnClickListener - обработчик событий
            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {

                boolean existence = myDataBaseHelper.isUserInDB(username_plain_text.getText().toString().trim());
                if(existence){
                    textView_main_page.setText("Такой Аккаунт уже есть!");
                }
                else {
                    textView_main_page.setText("Вы успешно зарегистрировались!");
                    myDataBaseHelper.AddToDB(username_plain_text.getText().toString().trim(),
                            password_plain_text.getText().toString().trim());
                    StartNewActivity();
                }

            }

        });
        button_login.setOnClickListener(v -> {
            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {
                boolean existence = myDataBaseHelper.isUserInDB(username_plain_text.getText().toString().trim());
                if(existence){
                    textView_main_page.setText("Вы вошли в аккаунт");
                    StartNewActivity();
                }
                else {
                    textView_main_page.setText("Пользователь не найден");
                }
            }
        });

        hide_text_button.setOnClickListener(v -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        });


    }
    public void StartNewActivity(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void  onClick_Save(View view){
        String id = myDatabase.getKey();
        String username = username_plain_text.getText().toString();
        String password = TextToHash.textToHash(password_plain_text.getText().toString());
        UserPattern userPattern = new UserPattern(id, username, password);
        myDatabase.push().setValue(userPattern);

    }
    public void init(){
        textView_main_page = findViewById(R.id.textView_of_main_page);

        username_plain_text = findViewById(R.id.username_plain_text);
        username_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        password_plain_text = findViewById(R.id.password_plain_text);
        password_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        hide_text_button = findViewById(R.id.hide_text_button);

        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);

        myDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);

    }

}