package com.example.task71;

import android. content.ContentValues;
import android. content.Context;
import android. database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LostAndFound.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "items"
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Name = "name";
    private static final String COLUMN_Phone = "phone";
    private static final String COLUMN_Description = "description";
    private static final String COLUMN_Date = "date";
    private static final String COLUMN_Location = "location";

    public DatabaseHelper (@Nullable Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);

        public long insertItem(Item item){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_Name, item.getName);
            contentValues.put(COLUMN_Phone, item.getPhone());
            contentValues.put(COLUMN_Description, item.getDescription);
            contentValues.put(COLUMN_Date, item.getDate());
            contentValues.put(COLUMN_Location, item.getLocation());

            long newRowId = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            return newRowId;
        }
        public List<Item> getAllItems() {
            List<Item> items = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery (selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getcolumnName(COLUMN_ID);
                    int nameIndex = cursor.getColumnIndex(COLUMN_Name);
                    int phoneIndex = cursor.getColumnIndex(COLUMN_Phone);
                    int descriptionIndex = cursor.getColumnIndex (COLUMN_Description);
                    int dateIndex = cursor.getColumnIndex (COLUMN_Date);
                    int locationIndex = cursor. getColumnIndex (COLUMN_Location);

                    if (idIndex != -1 && nameIndex != -1 && phoneIndex != -1 && descriptionIndex != -1 && dateIndex != -1 && locationIndex != -1){
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        String phone = cursor.getString (phoneIndex);
                        String description = cursor. getString(descriptionIndex);
                        String date = cursor.getString(dateIndex);
                        String location = cursor.getString(locationIndex);

                        Item item = new Item(id, name, phone, description, date, location);
                        items.add(item);
                    while (cursor . moveToNext());
        }