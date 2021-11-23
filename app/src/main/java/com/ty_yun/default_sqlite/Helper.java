package com.ty_yun.default_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Helper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public static String dbName = "MyDB";
    public static int dbVersion = 1;
    private static Helper instance;

    public Helper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    public static Helper getInstance(Context context) {
        if (instance == null) {
            instance = new Helper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE TABLE_NAME " +
                "(ITEM_KEY INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", ITEM_1 VARCHAR(20)" +
                ", ITEM_2 INTEGER" +
                ", ITEM_3 VARCHAR(10)" +
                ", ITEM_4 VARCHAR(100));";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS TABLE_NAME;";
        db.execSQL(drop);
        onCreate(db);
    }

    public void insert(String item1, int item2, String item3, String item4) {
        db = getWritableDatabase();
        String insert = "INSERT INTO TABLE_NAME (ITEM_1, ITEM_2, ITEM_3, ITEM_4) VALUES ('"+item1+"', "+item2+", '"+item3+"', '"+item4+"');";
        db.execSQL(insert);
    }

    public void update(int itemKey, String item1) {
        db = getWritableDatabase();
        String update = "UPDATE TABLE_NAME SET ITEM_1 = '"+item1+"' WHERE ITEM_KEY = "+itemKey;
        db.execSQL(update);
    }

    public void delete(int itemKey) {
        db = getWritableDatabase();
        String delete = "DELETE FROM TABLE_NAME WHERE ITEM_KEY = "+itemKey+";";
        db.execSQL(delete);
    }

    public Item select(int itemKey) {
        db = getReadableDatabase();
        String select = "SELECT ITEM_KEY, ITEM_1, ITEM_2, ITEM_3, ITEM_4 FROM TABLE_NAME WHERE ITEM_KEY = "+itemKey;
        Cursor cursor = db.rawQuery(select, null);

        Item item = new Item();
        while (cursor.moveToNext()) {
            item.setItemKey(cursor.getInt(0));
            item.setItem1(cursor.getString(1));
            item.setItem2(cursor.getInt(2));
            item.setItem3(cursor.getString(3));
            item.setItem4(cursor.getString(4));
        }

        return item;
    }

    public ArrayList<Item> selectAll() {
        db = getReadableDatabase();
        String selectAll = "SELECT ITEM_KEY, ITEM_1, ITEM_2, ITEM_3, ITEM_4 FROM TABLE_NAME";
        Cursor cursor = db.rawQuery(selectAll, null);

        ArrayList<Item> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setItemKey(cursor.getInt(0));
            item.setItem1(cursor.getString(1));
            item.setItem2(cursor.getInt(2));
            item.setItem3(cursor.getString(3));
            item.setItem4(cursor.getString(4));
            dataList.add(item);
        }

        return dataList;
    }
}
