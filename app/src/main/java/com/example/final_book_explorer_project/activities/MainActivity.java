package com.example.final_book_explorer_project.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.handlers.firebase_managment.User;
import com.example.final_book_explorer_project.handlers.TextToHash;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {
    private EditText editText, username_plain_text, password_plain_text;
    private ImageButton hide_text_button;
    private Button button_register, button_login;
    private TextView textView_main_page;
    FirebaseAuth auth;
    FirebaseDatabase db;
    ConstraintLayout root;
    private DatabaseReference users;
    private boolean isReadPermissionGranted = false;

//    ActvityResultLauncher<String[]> mPermissionResultLauncher;


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
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignIn_window();

            }

        });
//        mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
//                new ActivityResultCallback<Map<String, Boolean>>() {
//                    @Override
//                    public void onActivityResult(Map<String, Boolean> result) {
//                        if(result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != null){
//                            isReadPermissionGranted = result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                        }
//
//                    }
//                }
//                {
//        })


    }


    private void showSignIn_window() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите все данные для входа");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_window);

        EditText password = sign_window.findViewById(R.id.password_plaintext);
        EditText email = sign_window.findViewById(R.id.email_palintext);


        dialog.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }

        });
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Ошибка пароля", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Ошибка почты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString(), TextToHash.textToHash(password.getText().toString()))
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(findViewById(android.R.id.content), "Ошибка авторизации" + e.getMessage(), Snackbar.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите все данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        EditText username = register_window.findViewById(R.id.username_plaintext);
        EditText password = register_window.findViewById(R.id.password_plaintext);
        EditText email = register_window.findViewById(R.id.email_palintext);


        dialog.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }

        });
        dialog.setPositiveButton("Зарегистрироваться", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Ошибка логина или пароля или почты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Ошибка логина или пароля или почты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(findViewById(android.R.id.content), "Ошибка логина или пароля или почты", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                User user = new User(email.getText().toString(),
                                        username.getText().toString(), TextToHash.textToHash(password.getText().toString()));
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Snackbar.make(findViewById(android.R.id.content), "Пользователь добавлен!", Snackbar.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(MainActivity.this, MainActivity2.class));

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(findViewById(android.R.id.content), "Ошибка регистрации" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                            }
                                        });
                            }
                        });
            }
        });

        dialog.show();
    }
    //TODO поменять в бд email и имя пользователя

    private void init() {
        textView_main_page = findViewById(R.id.textView_of_main_page);
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");
        root = findViewById(R.id.root_element);


    }


}