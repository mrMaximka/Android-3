package lesson5.rumpilstilstkin.ru.databaseex.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbTable {

    private final static String TABLE_NAME = "Main";
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_AVATAR = "avatar_url";
    private final static String COLUMN_LOGIN = "login";
    private final static String COLUMN_LOGIN_ID = "login_id";


    public static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_AVATAR + " TEXT,"
            + COLUMN_LOGIN + " TEXT," + COLUMN_LOGIN_ID + " TEXT);");
    }

    public static void onUpgrade(SQLiteDatabase db) {

    }

    public static void addElement(SQLiteDatabase db, String avatar_url, String login, String login_id){
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_AVATAR
            + ", " + COLUMN_LOGIN + ", " + COLUMN_LOGIN_ID + ") VALUES ( '"
            + avatar_url + "', '" + login + "', '" + login_id + "');");
    }

    public static int deleteAll (SQLiteDatabase db){
        int size = getAllElements(db).size();
        db.delete(TABLE_NAME, null, null);
        return size;
    }

    public static ArrayList<String> getAllElements(SQLiteDatabase database){  // Получить все города
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        return getResultFromCursor(cursor);
    }

    private static ArrayList<String> getResultFromCursor(Cursor cursor) {
        ArrayList<String> list = null;

        if (cursor != null && cursor.moveToFirst()){
            list = new ArrayList<>(cursor.getCount());

            int avatarIdx = cursor.getColumnIndex(COLUMN_AVATAR);
            int loginIdx = cursor.getColumnIndex(COLUMN_LOGIN);
            int loginIdIdx = cursor.getColumnIndex(COLUMN_LOGIN_ID);
            do {
                list.add(cursor.getString(avatarIdx) + ", " + cursor.getString(loginIdx) + ", " + cursor.getString(loginIdIdx));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        return list == null ? new ArrayList<String>(0) : list;
    }

}
