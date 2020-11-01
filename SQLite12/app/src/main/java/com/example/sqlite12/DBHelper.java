package com.example.sqlite12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_name = "employee.db";
    public static final String table_name = "employee_table";
    public static final String col_1="id";
    public static final String col_2="name";
    public static final String col_3="surname";
    public static final String col_4="section";
    public static final String col_5="sex";
    public static final String col_6="age";
    public static final String col_7="salary";

    public DBHelper(@Nullable Context context) {
        super(context, DB_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE employee_table (id INTEGER PRIMARY KEY AUTOINCREMENT," +
              "name TEXT," +
              "surname TEXT," +
              "section TEXT," +
              "sex TEXT," +
              "age INTEGER," +
              "salary INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("DROP TABLE IF EXISTS "+table_name);
      onCreate(db);

    }

    public boolean AddData(String name,String surname,String section,String sex,Integer age,Integer salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,section);
        contentValues.put(col_5,sex);
        contentValues.put(col_6,age);
        contentValues.put(col_7,salary);
        long result = db.insert(table_name,null,contentValues);
        if (result == 0){
            return false;
        }else {
            return true;
        }
    }

    public boolean UpdateData(String name,String surname,String section,String sex,Integer age,Integer salary,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,section);
        contentValues.put(col_5,sex);
        contentValues.put(col_6,age);
        contentValues.put(col_7,salary);
        long result = db.update(table_name,contentValues,"id = ?",new String[]{id});
        if (result == 0){
            return false;
        }else {
            return true;
        }

    }

    public Integer DeleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer qty = db.delete(table_name,"id = ?",new String[]{id});
        return qty;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resDat = db.rawQuery("SELECT * FROM "+table_name,null);
        return resDat;
    }

}
