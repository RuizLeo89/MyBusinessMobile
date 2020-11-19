package com.tekno.mybusinessmobile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

import java.util.List;

public class NuevaConexionDialogViewModel extends AndroidViewModel {
    private LiveData<List<ConexionEntity>> allConexiones;
    private ConexionRepository conexionRepository;

    public NuevaConexionDialogViewModel (Application application) {
        super(application);

        conexionRepository = new ConexionRepository(application);
        allConexiones = conexionRepository.getAllConexiones();
    }

    // El fragmento que necesita recibir la nueva lista de datos
    public LiveData<List<ConexionEntity>> getAllConexiones() {
        return allConexiones;
    }

    //El fragmento que inserte una nueva nota, deberá comunicarlo a este Viewmodel
    public void insertarConexion(ConexionEntity nuevaConexionEntity) {
        conexionRepository.insert(nuevaConexionEntity);
    }
}