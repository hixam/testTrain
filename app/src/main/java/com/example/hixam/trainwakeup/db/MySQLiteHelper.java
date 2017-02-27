package com.example.hixam.trainwakeup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hixam on 26/02/17.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_POS = "positions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATION = "station";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LONG = "longt";


    private static final String DATABASE_NAME = "positions.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_POS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_STATION
            + " text not null," +  COLUMN_LAT
            + " text not null," + COLUMN_LONG
            + " text not null);"  ;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POS);
        onCreate(db);
    }

}