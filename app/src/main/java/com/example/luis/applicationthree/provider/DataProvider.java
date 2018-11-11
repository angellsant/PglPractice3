package com.example.luis.applicationthree.provider;

import android.content.ContentProvider;
import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseArray;

public class DataProvider extends ContentProvider {


    private static final int CARCONSTRUCTOR_ONE_REG = 1;  // content://com.example.luis.applicationthree.provider.dataProvider/Constructor/#
    private static final int CARCONSTRUCTOR_ALL_REGS = 2; // content://com.example.luis.applicationthree.provider.dataProvider/Constructor

    private SQLiteDatabase sqlDB;
    private DatabaseHelper dbHelper;
    private static final String DATABASE_NAME = "FabricantesCoches.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CONSTRUCTORS_TABLE_NAME = "Constructor";

    private static final UriMatcher sUriMatcher;
    private static final SparseArray<String> sMimeTypes;

    static {

        sUriMatcher = new UriMatcher(0);

        sUriMatcher.addURI(Contract.AUTHORITY,CONSTRUCTORS_TABLE_NAME + "/#", CARCONSTRUCTOR_ONE_REG);
        sUriMatcher.addURI(Contract.AUTHORITY, CONSTRUCTORS_TABLE_NAME, CARCONSTRUCTOR_ALL_REGS);

        // Specifies custom MIME tipe for picture URL table text/html
        sMimeTypes.put(CARCONSTRUCTOR_ONE_REG, "vnd.android.cursor.item/vnd." + Contract.AUTHORITY + CONSTRUCTORS_TABLE_NAME);
        sMimeTypes.put(CARCONSTRUCTOR_ALL_REGS, "vnd.android.cursor.dir/vnd." + Contract.AUTHORITY + "." + CONSTRUCTORS_TABLE_NAME);

    }



    public static class  DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onOpen(SQLiteDatabase db){
            super.onOpen(db);
            // Habilitamos Integridad Referencial
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
