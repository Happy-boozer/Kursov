package com.example.kursov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper3 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Workers.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "positions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_OKLAD = "oklad";

    public DatabaseHelper3(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_OKLAD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Добавление новой позиции
    public boolean addWorker(Position worker) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


            cv.put(COLUMN_NAME, worker.getName());
            cv.put(COLUMN_OKLAD, worker.getOklad());

            long result = db.insert(TABLE_NAME, null, cv);
            return result != -1;

            //Log.e("DB_ERROR", "Ошибка при добавлении: " + e.getMessage());
            //return false;

    }

    // Поиск позиции по названию
    public Position findContact(String positionName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_OKLAD},
                COLUMN_NAME + " = ?",
                new String[]{positionName},
                null, null, null);

        Position worker = null;
        if (cursor != null && cursor.moveToFirst()) {
            worker = new Position(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.close();
        }
        db.close();
        return worker;
    }

    // Получение всех позиций
    public List<Position> getAllPositions() {
        List<Position> positionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Position position = new Position(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                positionList.add(position);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return positionList;
    }

    public void clearWorkersTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}