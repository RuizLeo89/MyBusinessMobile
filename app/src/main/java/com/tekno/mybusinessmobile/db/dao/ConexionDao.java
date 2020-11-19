package com.tekno.mybusinessmobile.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

import java.util.List;

@Dao
public interface ConexionDao {

    @Insert
    void insert(ConexionEntity conexion);

    @Update
    void update (ConexionEntity conexion);

    @Query("DELETE FROM conexiones_sql WHERE id = :id ")
    void deleteConexion(int id);

    @Query("SELECT * FROM conexiones_sql")
    LiveData<List<ConexionEntity>> getAllConexiones();
}
