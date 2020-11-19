package com.tekno.mybusinessmobile;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tekno.mybusinessmobile.db.MyBusinessRoomDatabase;
import com.tekno.mybusinessmobile.db.dao.ConexionDao;
import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

import java.util.List;

public class ConexionRepository {
    private ConexionDao conexionDao;
    private LiveData<List<ConexionEntity>> allConexiones;

    public ConexionRepository(Application application) {
        MyBusinessRoomDatabase db = MyBusinessRoomDatabase.getDatabase(application);
        conexionDao = db.conexionDao();
        allConexiones = conexionDao.getAllConexiones();
    }

    public LiveData<List<ConexionEntity>> getAllConexiones() {
        return allConexiones;
    }

    public void insert(ConexionEntity conexion) {
        new insertAsyncTask(conexionDao).execute(conexion);
    }

    private static class insertAsyncTask extends AsyncTask<ConexionEntity, Void, Void> {
        private ConexionDao conexionDaoAsyncTask;

        insertAsyncTask(ConexionDao conexionDao) {
            conexionDaoAsyncTask = conexionDao;
        }

        @Override
        protected Void doInBackground(ConexionEntity... conexionEntities) {
            conexionDaoAsyncTask.insert(conexionEntities[0]);
            return null;
        }
    }

}
