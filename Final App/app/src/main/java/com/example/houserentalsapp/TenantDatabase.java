package com.example.houserentalsapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TenantEntity.class}, version = 1)
public abstract class TenantDatabase extends RoomDatabase {

    private static  final String dbNAme = "user";
    private static  TenantDatabase tenantDatabase;

    public static synchronized TenantDatabase getTenantDatabase(Context context){

        if (tenantDatabase == null){
            tenantDatabase = Room.databaseBuilder(context,TenantDatabase.class, dbNAme)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return tenantDatabase;
    }
    public abstract TenantDao tenantDao();
}
