package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Messages.db";
    private static final int VERSION_NUM = 3;
    private static final String TAG = "ChatDatabaseHelper";


    public static final String TABLE_MESSAGES = "messages";


    public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE = "message";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Calling onCreate");

        String CREATE_TABLE_MESSAGES = "CREATE TABLE " + TABLE_MESSAGES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MESSAGE + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);

        onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
