package com.example.xiao.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Xiao on 2015/11/19 0019.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * 建Province表
     */
    private static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";

    /**
     * 建City表
     */
    private static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";

    /**
     * 建Country表
     */
    private static final String CREATE_COUNTRY = "create table Country ("
            + "id integer primary key autoincrement, "
            + "country_id text, "
            + "country_code text, "
            + "city_id integer)";


    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
