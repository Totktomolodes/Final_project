package com.example.final_book_explorer_project.activities;

import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.page_count;
import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.fileContent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.final_book_explorer_project.R;

public class MainActivity3 extends AppCompatActivity {
    TextView reading_textview;
    ImageButton go_back_btn;
    Button back_btn, next_btn;
    int start_page = 1;
    int page_size = 5000;
    int last_page = page_count;
    public String file_content = fileContent;
    private String page;
    int counter = 1;
    int last_symb, start_symb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_screen);
        init();

        go_back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                back_to_second_activity();
            }

        });
        next_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                counter = counter + 1;
                start_symb = page_size * counter;
                last_symb = last_symb + page_size;
                page = file_content.substring(0, page_size);
                reading_textview.setText(page);

            }

        });
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (counter != 1) {


                    counter = counter - 1;
                    start_symb = page_size * counter;
                    last_symb = last_symb + page_size;
                    page = file_content.substring(start_symb, last_symb);
                    reading_textview.setText(page);
                }
            }

        });



    }

    private void init(){
        reading_textview = findViewById(R.id.reading_textview);
        go_back_btn = findViewById(R.id.go_back_btn);
        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);
    }
    private void back_to_second_activity(){
        finish();
        startActivity(new Intent(MainActivity3.this, MainActivity2.class));
    }

}