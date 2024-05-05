package com.example.final_book_explorer_project.firebase_managment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.activities.MainActivity2;
import com.example.final_book_explorer_project.screen_handlers.MyDataBaseHelper;
import com.example.final_book_explorer_project.user_managment.TextToHash;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;


public class MainActivity extends AppCompatActivity {
    private EditText editText, username_plain_text, password_plain_text;
    private ImageButton hide_text_button;
    private Button button_register, button_login;
    private TextView textView_main_page;
    FirebaseAuth auth;
    FirebaseDatabase db;
    ConstraintLayout root;
    private DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_panel);
        init();


//        button_register.setOnClickListener(v -> {
//            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {
//
//                boolean existence = myDataBaseHelper.isUserInDB(username_plain_text.getText().toString().trim());
//                if (existence) {
//                    textView_main_page.setText("Такой Аккаунт уже есть!");
//                } else {
//                    textView_main_page.setText("Вы успешно зарегистрировались!");
//                    myDataBaseHelper.AddToDB(username_plain_text.getText().toString().trim(),                     // добавлять в глобальную базу firebase
//                            password_plain_text.getText().toString().trim());                    // добавлять в глобальную базу firebase
//                    StartNewActivity();
//                }
//
//            }
//
//        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();

            }

        });
        button_login.setOnClickListener(v -> {
            try (MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this)) {
                boolean existence = myDataBaseHelper.isUserInDB(username_plain_text.getText().toString().trim());                // добавлять в глобальную базу firebase
                if (existence) {
                    textView_main_page.setText("Вы вошли в аккаунт");
                    StartNewActivity();
                } else {
                    textView_main_page.setText("Пользователь не найден");
                }
            }
        });

        hide_text_button.setOnClickListener(v -> {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//не работает
        });


    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        MaterialEditText username = register_window.findViewById(R.id.username_plaintext);
        MaterialEditText password = register_window.findViewById(R.id.password_plaintext);
        MaterialEditText email = register_window.findViewById(R.id.email_palintext);


        dialog.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
            dialogInterface.dismiss();
            }

        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    Snackbar.make(root, "Ошибка имени", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Snackbar.make(root, "Ошибка пароля", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Ошибка почты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
//TODO досмотреть видос
                            }
                        });
            }
        });
    }

    public void StartNewActivity() {
        Intent intent = new Intent(this, MainActivity2.class);
        finish();
        startActivity(intent);
    }

    public void onClick_Save(View view) {
        String id = users.getKey();
        String username = username_plain_text.getText().toString();
        String password = TextToHash.textToHash(password_plain_text.getText().toString());
        UserPattern userPattern = new UserPattern(id, username, password);
        users.push().setValue(userPattern);

    }

    public void init() {
        textView_main_page = findViewById(R.id.textView_of_main_page);

        username_plain_text = findViewById(R.id.username_plain_text);
        username_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        password_plain_text = findViewById(R.id.password_plain_text);
        password_plain_text.setInputType(InputType.TYPE_CLASS_TEXT);

        hide_text_button = findViewById(R.id.hide_text_button);

        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        root = findViewById(R.id.root_element);


    }

}