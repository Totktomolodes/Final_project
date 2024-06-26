package com.example.final_book_explorer_project.activities;

import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.fileContent;
import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.fileName;
import static com.example.final_book_explorer_project.fragments.installer.InstallerFragment.maker_book_in_catalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_book_explorer_project.R;
import com.example.final_book_explorer_project.fragments.installer.InstallerFragment;
import com.example.final_book_explorer_project.fragments.mycatalog.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    TextView reading_textview, count_textview;
    ImageButton go_back_btn;
    Button back_btn, next_btn;
    int page_size = 1700;
    public String file_content;
    private String page;
    int counter = 0;
    String text = "%d из %d";
    String text2;
    List<String> pages_list;
    int counter_maximum;
    public Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_screen);
        Intent intent = getIntent();
        int bookIndex = intent.getIntExtra("bookIndex", 0);
        this.book = InstallerFragment.bookList.get(bookIndex);
        file_content = this.book.getText();
        counter_maximum = (file_content.length() / page_size);


        init();
        make_counter();
        divider_text_len();






        go_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_to_second_activity();
            }

        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checking_max()) {
                    return;
                }
                counter = counter + 1;
                make_counter();
                divider_text_len();


            }

        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checking_min()) {
                    return;
                }
                counter = counter - 1;
                make_counter();
                divider_text_len();

            }

        });


    }

    private void init() {
        reading_textview = findViewById(R.id.reading_textview);
        go_back_btn = findViewById(R.id.go_back_btn);
        back_btn = findViewById(R.id.back_btn);
        next_btn = findViewById(R.id.next_btn);
        count_textview = findViewById(R.id.counter_pages_text_view);
    }

    private void back_to_second_activity() {
        finish();
        startActivity(new Intent(MainActivity3.this, MainActivity2.class));
    }

    public void divider_text_len() {
//        if (file_content.length() < page_size) {
//            reading_textview.setText(file_content);
//        } else {
//            page = file_content.substring(counter * page_size, (counter + 1) * page_size);
//            reading_textview.setText(page);
//
//
//        }
        pages_list = splitText(file_content, page_size);
        reading_textview.setText(pages_list.get(counter));

    }

    public boolean checking_max() {

        if (counter_maximum == counter) {
            return false;
        }

        return true;
    }

    public boolean checking_min() {

        if (counter == 0) {
            return false;
        }

        return true;
    }

    public void make_counter() {
        text2 = String.format(text, counter + 1, counter_maximum + 1);
        count_textview.setText(text2);


    }


    public static List<String> splitText(String text, int pageSize) {
        List<String> pages = new ArrayList<>();
        int length = text.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + pageSize, length);

            if (end < length && text.charAt(end) != ' ') {
                int lastSpace = text.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }


            pages.add(text.substring(start, end).trim());

            start = end;

            while (start < length && text.charAt(start) == ' ') {
                start++;
            }
        }

        return pages;
    }
}

