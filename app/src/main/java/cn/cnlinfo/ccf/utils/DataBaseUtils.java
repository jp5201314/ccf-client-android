package cn.cnlinfo.ccf.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.cnlinfo.ccf.CCFApplication;
import cn.cnlinfo.ccf.entity.City;
import cn.cnlinfo.ccf.entity.Province;
import cn.cnlinfo.ccf.entity.Zone;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class DataBaseUtils {
    private String DB_NAME = "province_city_zone.db";
    private Context mContext;
    private static DataBaseUtils dataBaseUtils = new DataBaseUtils(CCFApplication.getContext());
    private final SQLiteDatabase sqLiteDatabase  = getSqliteDatabase();
    private DataBaseUtils(Context mContext) {
        this.mContext = mContext;
    }

    public static DataBaseUtils getInstance(){
        return dataBaseUtils;
    }
    //把assets目录下的db文件复制到dbpath下
    private  SQLiteDatabase getSqliteDatabase() {
        String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                boolean flag = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/").mkdirs();
                if (flag){
                    boolean newFile = new File(dbPath).createNewFile();
                    if (newFile){
                        FileOutputStream out = null;
                        InputStream in = null;
                        try {
                            out = new FileOutputStream(dbPath);
                            in = mContext.getAssets().open("province_city_zone.db");
                            byte[] buffer = new byte[1024];
                            int readBytes = 0;
                            while ((readBytes = in.read(buffer)) != -1)
                            out.write(buffer, 0, readBytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            in.close();
                            out.close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }
    public  List<Province> queryProvince(){
        List<Province> provinceList = new LinkedList<>();
        Province province  = null;
        String sqlProvince = "select * from T_Province";
        if (sqLiteDatabase.isOpen()){
            Cursor cursor = sqLiteDatabase.rawQuery(sqlProvince,null);
            while (cursor.moveToNext()){
                String proName = cursor.getString(0);
                String proSort = cursor.getString(1);
                String proRemark = cursor.getString(2);
                province = new Province(proName,proSort,proRemark);
                provinceList.add(province);
            }
        }
        return provinceList;
    }

    public  List<City> queryCity(){
        List<City> cityList = new LinkedList<>();
        City city = null;
        String sqlCity = "select * from T_City";
        if (sqLiteDatabase.isOpen()){
            Cursor cursor = sqLiteDatabase.rawQuery(sqlCity,null);
            while (cursor.moveToNext()){
                String cityName = cursor.getString(0);
                String proId = cursor.getString(1);
                String citySort = cursor.getString(2);
                city = new City(cityName,proId,citySort);
                cityList.add(city);
            }
        }
        return cityList;
    }


    public  List<Zone> queryZone(){
        List<Zone> zoneList = new LinkedList<>();
        Zone zone = null;
        String sqlZone = "select * from T_Zone";
        if (sqLiteDatabase.isOpen()){
            Cursor cursor = sqLiteDatabase.rawQuery(sqlZone,null);
            while (cursor.moveToNext()){
                int zoneId = cursor.getInt(0);
                String zoneName = cursor.getString(1);
                String cityId = cursor.getString(2);
                zone = new Zone(zoneId,zoneName,cityId);
                zoneList.add(zone);
            }
        }
        return zoneList;
    }


    @NonNull
    public List<String> getProvinceNameList() {
        List<Province> provinceList = dataBaseUtils.queryProvince();
        final List<String> proNameList = new ArrayList<>();
        for (int i = 0; i < provinceList.size(); i++) {
            Province province = provinceList.get(i);
            proNameList.add(province.getProName());
        }
        return proNameList;
    }


    @NonNull
    public List<String> getCityNameList() {
        List<City> cityList = dataBaseUtils.queryCity();
        final List<String> cityNameList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            City city = cityList.get(i);
            cityNameList.add(city.getCityName());
        }
        return cityNameList;
    }

    @NonNull
    public List<String> getZoneNameList() {
        List<Zone> zoneList = dataBaseUtils.queryZone();
        final List<String> zoneNameList = new ArrayList<>();
        for (int i = 0; i < zoneList.size(); i++) {
            Zone zone = zoneList.get(i);
            zoneNameList.add(zone.getZoneName());
        }
        return zoneNameList;
    }
}
