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
        this.db = this.getWritableDatabase();

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
        String password1  = TextToHash.textToHash(password);
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password1);
        db.insert("users", null, values);
    }

    public boolean isUserInDB(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s == '%s';", TABLE_NAME, COLUMN_USERNAME, username), new  String[]{});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}