package com.tekno.mybusinessmobile.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conexiones_sql")
public class ConexionEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String empresa;
    public String servidor;
    public String dataBase;
    public String puerto;
    public String usuario;
    public String password;

    public ConexionEntity(String empresa, String servidor, String dataBase, String puerto, String usuario, String password) {
        this.empresa = empresa;
        this.servidor = servidor;
        this.dataBase = dataBase;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
