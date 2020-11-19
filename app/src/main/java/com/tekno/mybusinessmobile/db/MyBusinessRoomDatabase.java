package com.tekno.mybusinessmobile.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tekno.mybusinessmobile.db.dao.ConexionDao;
import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

@Database(entities = {ConexionEntity.class}, version = 1)
public abstract class MyBusinessRoomDatabase extends RoomDatabase {
    public abstract ConexionDao conexionDao();
    private static volatile MyBusinessRoomDatabase INSTANCE;

    public static MyBusinessRoomDatabase getDatabase( final Context context) {
        if(INSTANCE == null) {
            synchronized (MyBusinessRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyBusinessRoomDatabase.class, "MyBusinessMobile").build();
                }
            }
        }

        return INSTANCE;
    }
}
