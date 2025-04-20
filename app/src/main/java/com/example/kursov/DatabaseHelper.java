package com.example.kursov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EXPIRIENS = "experience";
    private static final String COLUMN_POSITION = "position";
    private static final String COLUMN_SALARY = "salary";

    public DatabaseHelper(Context context) {
        super(context, "Workers.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_POSITION + " TEXT, " +
                COLUMN_SALARY + " TEXT, " +
                COLUMN_EXPIRIENS + " TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(db);
    }
    // Добавление нового worker
    public boolean addWorker(Worker worker) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, worker.getName());
        cv.put(COLUMN_POSITION, worker.getPosition());
        cv.put(COLUMN_SALARY, worker.getSalary());
        cv.put(COLUMN_EXPIRIENS, worker.getPosition());
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }
    // Удаление workera по position
    public boolean deleteContact(String postion) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_POSITION + " = ?",
                new String[]{postion});
        db.close();
        return result > 0;
    }
    // Поиск контакта по номеру телефона
    public Worker findContact(String position) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new
                        String[]{COLUMN_ID, COLUMN_NAME, COLUMN_POSITION},
                COLUMN_POSITION + " = ?", new String[]{position}, null,
                null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Worker worker = new Worker(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            cursor.close();
            db.close();
            return worker;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }
    // Получение всех контактов
    public List<Worker> getAllContacts() {
        List<Worker> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Worker contact = new Worker(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    public boolean updateContact(String oldPhone, String newName,
                                 String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, newName);
        cv.put(COLUMN_POSITION, newPhone);
        // Обновляем запись, где номер телефона равен oldPhone
        int result = db.update(TABLE_NAME, cv, COLUMN_POSITION + " = ? ", new String[]{oldPhone});
                db.close();
        return result > 0;
    }

    public void clearWorkersTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }


}