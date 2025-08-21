package com.example.aplikcjacrud.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.aplikcjacrud.R;
import com.example.aplikcjacrud.model.Account;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, "quiz_db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "create table users(id integer primary key autoincrement, name text, bestScore integer);";
        String query2 = "create table questions(id integer primary key autoincrement, correctAnswer text, answer1 text, answer2 text, image text)";
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query1 = "drop table if exists notes;";
        String query2 = "drop table if exists questions;";
        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        this.onCreate(sqLiteDatabase);
    }

    public void addUser(Account account) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", account.getName());
        contentValues.put("bestScore", account.getBestScore());
        sqLiteDatabase.insert("users", null, contentValues);
    }

    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from users order by bestScore ASC";
        Cursor cursor = null;
        if (sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getAllUsersSorted() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from users order by bestScore desc;";
        Cursor cursor = null;
        if (sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteUser(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete("users", "id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            Toast.makeText(context, R.string.nonDeletedUser, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.deletedUser, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isUserExists(String name) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM users WHERE name = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public void updateBestScoreIfHigher(String name, int newScore) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select bestScore from users where name = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            int currentScore = cursor.getInt(0);
            cursor.close();

            if (newScore > currentScore) {
                SQLiteDatabase writableDb = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("bestScore", newScore);
                writableDb.update("users", values, "name = ?", new String[]{name});
            }
        }
    }
}
