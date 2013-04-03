package idcc.ntu.idcc_pa2;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "data";
    
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT NOT NULL, " +
                CONTENT + " TEXT NOT NULL);";
        
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
    public void saveData(SQLiteDatabase db, String title, String data) {
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(CONTENT, data);
        db.insert(TABLE_NAME, null, values);
    }
    
    public String[] getData(SQLiteDatabase db) {
        ArrayList<String> arr = new ArrayList<String>();
        Cursor c = db.query(TABLE_NAME, new String[]{TITLE, CONTENT}, 
                null, null, null, null, null);
        int num = c.getCount();
        for (int i = 0 ; i < num ; ++i) {
            if (c.moveToFirst()) {
                String t = c.getString(0);
                arr.add(t);
            }
        }
        
        String[] str = new String[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++) {
            str[i] = arr.get(i);
        }
        return str;
    }
    
    public String getTitle(int position) {
        Cursor c = getReadableDatabase().query(TABLE_NAME, 
                    new String[]{TITLE}, ID + "=" + position, null, null, null, null);
        if (c.moveToFirst()) {
            return c.getString(0);
        }
        return null;
    }
    
    public String getContent(int position) {
        Cursor c = getReadableDatabase().query(TABLE_NAME, 
                new String[]{CONTENT}, ID + "=" + position, null, null, null, null);
    if (c.moveToFirst()) {
        return c.getString(0);
    }
    return null;        
    }
}
