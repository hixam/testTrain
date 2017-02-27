package com.example.hixam.trainwakeup.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.hixam.trainwakeup.clases.Station;
import com.example.hixam.trainwakeup.db.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hixam on 26/02/17.
 */
public class StationsDAO {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_STATION,MySQLiteHelper.COLUMN_LAT, MySQLiteHelper.COLUMN_LONG };

    public StationsDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Station createStation(Station station) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_STATION, station.getStation());
        values.put(MySQLiteHelper.COLUMN_LAT, station.getLat());
        values.put(MySQLiteHelper.COLUMN_LONG, station.getLongt());

        long insertId = database.insert(MySQLiteHelper.TABLE_POS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_POS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Station newStation = cursorToComment(cursor);
        cursor.close();
        return newStation;
    }

    public void deleteComment(Station station) {
        long id = station.getId();
        System.out.println("Station deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_POS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Station> getAllComments() {
        List<Station> stations = new ArrayList<Station>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_POS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Station station = cursorToComment(cursor);
            stations.add(station);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return stations;
    }

    private Station cursorToComment(Cursor cursor) {
        Station station = new Station();
        station.setId(cursor.getLong(0));
        station.setStation(cursor.getString(1));
        station.setLat(cursor.getString(2));
        station.setLongt(cursor.getString(3));
        return station;
    }
}
