package com.uangku.ega.uangku.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by egateza on 1/16/2018.
 */

public class dataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "uang.db";
    public static final int DATABASE_VERSION = 1;
    public dataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TABLE = "CREATE TABLE "+
                dataEntry.entryAdapter.TABLE_NAME + " ("+
                dataEntry.entryAdapter._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                dataEntry.entryAdapter.NOMINAL+ " DOUBLE NOT NULL, "+
                dataEntry.entryAdapter.KETERANGAN+ " TEXT NOT NULL, "+
                dataEntry.entryAdapter.STATUS+ " TEXT NOT NULL, "+
//                dataEntry.entryAdapter.SALDO+ " DOUBLE NOT NULL, "+
                dataEntry.entryAdapter.DATE+" TEXT NOT NULL"+
                ");";

//        String sql = "create table keuangan(no integer primary key, nama text null, tgl text null, jk text null, alamat text null);";
        Log.d("Log SQL", SQL_CREATE_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ dataEntry.entryAdapter.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
