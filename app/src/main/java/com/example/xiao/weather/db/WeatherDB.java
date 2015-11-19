package com.example.xiao.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xiao.weather.model.City;
import com.example.xiao.weather.model.Country;
import com.example.xiao.weather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiao on 2015/11/19 0019.
 */
public class WeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    /**
     * 私有构造方法 ，构成单例
     * @param context
     */
    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取WeatherDB的实例
     */
    public synchronized static WeatherDB getInstance(Context context){
        if(weatherDB == null){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * 将Province实例存储到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库中读取全国所有的省份信息
     */
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        while(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City实例保存到数据库中
     * @param city
     */
    public void saveCity(City city){
        ContentValues values = new ContentValues();
        values.put("city_name", city.getCityName());
        values.put("city_code", city.getCityCode());
        values.put("province_id", city.getProviceId());
        db.insert("City", null, values);
    }

    /**
     *从数据库中读取某省份下的City
     * @return
     */
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[]{String.valueOf(provinceId)}, null, null, null);
        while(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProviceId(provinceId);
                list.add(city);
            }while(cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将Country实例保存到数据库中
     * @param country
     */
    public void saveCountry(Country country){
        ContentValues values = new ContentValues();
        values.put("country_name", country.getCountryName());
        values.put("country_code", country.getCountryCode());
        values.put("city_id", country.getCityId());
        db.insert("Country", null, values);
    }

    /**
     * 从数据库中导出某城市下的Country
     * @param cityId
     * @return
     */
    public List<Country> loadCountries(int cityId){
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null, "city_id = ?",
                new String[]{String.valueOf(cityId)}, null, null, null);
        while(cursor.moveToFirst()){
            do{
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCityId(cityId);
                list.add(country);
            }while(cursor.moveToNext());
        }
        return list;
    }
}


















































