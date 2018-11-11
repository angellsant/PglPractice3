package com.example.luis.applicationthree.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;

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

        // Habilitamos Integridad Referencial
        public void onOpen(SQLiteDatabase db){
            super.onOpen(db);
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // Crear la tabla de la base de datos
            db.execSQL("CREATE TABLE " +
                       "(ID INTEGER PRIMARY KEY ON COMFLICT ROLLBACK AUTOINCREMENT, " +
                       Contract.Constructor.NAMECONSTRUCTOR + " TEXT, " +
                       Contract.Constructor.YEARBIRDTH + " TEXT, " +
                       Contract.Constructor.FOUNDER + " TEXT);"
            );

            initializeDataTable(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CONSTRUCTORS_TABLE_NAME);

            onCreate(db);
        }

        private void initializeDataTable(SQLiteDatabase db) {
            db.execSQL("INSERT INTO " + CONSTRUCTORS_TABLE_NAME + " ("+ Contract.Constructor._ID + "," + Contract.Constructor.NAMECONSTRUCTOR + "," + Contract.Constructor.YEARBIRDTH + ","+ Contract.Constructor.FOUNDER + ") " +
                       "VALUES (1,'TOYOTA','1933','Kiichiro Toyoda')");
            db.execSQL("INSERT INTO " + CONSTRUCTORS_TABLE_NAME + " ("+ Contract.Constructor._ID + "," + Contract.Constructor.NAMECONSTRUCTOR + "," + Contract.Constructor.YEARBIRDTH + ","+ Contract.Constructor.FOUNDER + ") " +
                    "VALUES (2,'HONDA','1948','Sheichiro Honda')");
            db.execSQL("INSERT INTO " + CONSTRUCTORS_TABLE_NAME + " ("+ Contract.Constructor._ID + "," + Contract.Constructor.NAMECONSTRUCTOR + "," + Contract.Constructor.YEARBIRDTH + ","+ Contract.Constructor.FOUNDER + ") " +
                    "VALUES (3,'FERRARI','1947','Enzo Ferrari')");
            db.execSQL("INSERT INTO " + CONSTRUCTORS_TABLE_NAME + " ("+ Contract.Constructor._ID + "," + Contract.Constructor.NAMECONSTRUCTOR + "," + Contract.Constructor.YEARBIRDTH + ","+ Contract.Constructor.FOUNDER + ") " +
                    "VALUES (4,'PORSCHE','1931','Ferdinand Porsche')");
            db.execSQL("INSERT INTO " + CONSTRUCTORS_TABLE_NAME + " ("+ Contract.Constructor._ID + "," + Contract.Constructor.NAMECONSTRUCTOR + "," + Contract.Constructor.YEARBIRDTH + ","+ Contract.Constructor.FOUNDER + ") " +
                    "VALUES (5,'SEAT','1950','Instituto Nacional de Industria España ')");
        }
    }

    public ContentProvider() {

    }

    public String getType (Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate(){
      dbHelper = new DatabaseHelper(getContext());
      return (dbHelper == null) ? false : true;
    }

    public void resetDatabase() {
        dbHelper.close();;
        dbHelper = new DatabaseHelper(getContext());
    }


    // Scrud de la Uri: insert, delete, update, query

    @Override
    public Uri insert (Uri uri, ContentValues values){

        sqlDB = dbHelper.getWritableDatabase();

        String table = "";
        switch (sUriMatcher.match(uri)){

            case CARCONSTRUCTOR_ALL_REGS:
                table = CONSTRUCTORS_TABLE_NAME;
                break;
        }

        long rowId = sqlDB.insert(table,"", values);

        if (rowId > 0) {
            Uri rowUri = ContentUris.appendId(uri.buildUpon(),rowId).build();
            getContext().getContentResolver().notifyChange(rowUri,null)
            return rowUri;
        }
        throw new SQLException("Fallo en la inserción de la fila " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        sqlDB = dbHelper.getWritableDatabase();

        String table = "";
        switch (sUriMatcher.match(uri)){
            case CARCONSTRUCTOR_ONE_REG:
                if (null == selection) selection = "";
                selection += Contract.Constructor._ID + " = " + uri.getLastPathSegment();
                table = CONSTRUCTORS_TABLE_NAME;
                break;

            case CARCONSTRUCTOR_ALL_REGS:
                table = CONSTRUCTORS_TABLE_NAME;
                break
        }

        int rows = sqlDB.delete(table,selection,selectionArgs);

        if (rows>0) {
            getContext().getContentResolver().notifyChange(uri,null);
            return rows;
        }
        throw new SQLException("Fallo al borrar la fila en " + uri);
    }

    @Override
    public Cursor query (Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){

    }

    @Override
    public int update (Uri uri, ContentValues values, String selection, String[] selectionArgs){

    }

}

































