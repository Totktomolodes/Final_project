package com.example.final_book_explorer_project.screen_handlers;
//class userManager
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_book_explorer_project.user_managment.TextToHash;


public class MyDataBaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;
    private  SQLiteDatabase db;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("CREATE TABLE IF NOT EXISTS users (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "username TEXT,\n" +
                "password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void AddToDB(String username, String  password){
        ContentValues values = new ContentValues();
        values.put("username", TextToHash.textToHash(username));
        values.put("password", TextToHash.textToHash(password));
        db.insert("users", null, values);
    }

    public boolean isUserInDB(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_USERNAME , new String[]{username});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
