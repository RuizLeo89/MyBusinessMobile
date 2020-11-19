package com.tekno.mybusinessmobile.sqlserver;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerConnection {
    private String strServer;
    private String strDataBase;
    private String strPort;
    private String strUser;
    private String strPassword;

    private Connection connection;
    private String conectionError;

    public SQLServerConnection() {
        connection = null;
    }



    public SQLServerConnection(String strServer, String strPort, String strDataBase, String strUser, String strPassword) {
        this.strServer = strServer;
        this.strDataBase = strDataBase;
        this.strPort = strPort;
        this.strUser = strUser;
        this.strPassword = strPassword;
        connection = null;
    }

    /*public Connection getConnection() {
        if(connection == null) {
            connect();
        }
        return connection;
    }*/

    /*public void connect() {
        String url = "jdbc:jtds:sqlserver://" + strServer
                + ":" + strPort
                + ";databaseName=//" + strDataBase;
        conectionError = "";

        Connection conn = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(url, strUser,strPassword);
        } catch (ClassNotFoundException e) {
            conectionError = e.getMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            //conectionError = e.getMessage();
            conectionError = "SQLException";
            e.printStackTrace();
        } catch (Exception e) {
            //conectionError = e.getMessage();
            conectionError = "Exception";
            e.printStackTrace();
        }
        connection = conn;
    }*/

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getStrServer() {
        return strServer;
    }

    public void setStrServer(String strServer) {
        this.strServer = strServer;
    }

    public String getStrDataBase() {
        return strDataBase;
    }

    public void setStrDataBase(String strDataBase) {
        this.strDataBase = strDataBase;
    }

    public String getStrPort() {
        return strPort;
    }

    public void setStrPort(String strPort) {
        this.strPort = strPort;
    }

    public String getStrUser() {
        return strUser;
    }

    public void setStrUser(String strUser) {
        this.strUser = strUser;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getConectionError() {
        return conectionError;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setConectionError(String conectionError) {
        this.conectionError = conectionError;
    }

    @Override
    public String toString() {
        return "SQLServerConnection{" +
                "strServer='" + strServer + '\'' +
                ", strDataBase='" + strDataBase + '\'' +
                ", strPort='" + strPort + '\'' +
                ", strUser='" + strUser + '\'' +
                ", strPassword='" + strPassword + '\'' +
                '}';
    }

    public void connect (Activity activity) {
        new connectAsyncTask(this, activity).execute();
    }

    private static class connectAsyncTask extends AsyncTask<Void, Void, Void> {
        private SQLServerConnection sql;
        private Activity activity;

        public connectAsyncTask(SQLServerConnection sql, Activity activity) {
            this.sql = sql;
            this.activity = activity;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(sql.getConnection() != null && sql.getConectionError().length() == 0) {
                Toast.makeText(activity, "Conexión exitosa", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(activity, sql.getConectionError(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "jdbc:jtds:sqlserver://" + sql.getStrServer()
                    + ":" + sql.getStrPort()
                    + ";databaseName=//" + sql.getStrDataBase();
            sql.setConectionError("");

            sql.setConnection(null);
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                sql.setConnection( DriverManager.getConnection(url, sql.getStrUser(), sql.getStrPassword()) );
                //sql.setConnection( DriverManager.getConnection(url + ";user=" + sql.getStrUser() + ";password=" + sql.getStrPassword()));
                if(!ifcorrectDataBase(sql)) {
                    sql.setConectionError("No se econtró la Base de Datos especificada");
                }
            } catch (ClassNotFoundException e) {
                sql.setConectionError("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                sql.setConectionError("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                sql.setConectionError("Error: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        private boolean ifcorrectDataBase(SQLServerConnection sqlConn){
            String sqlQyery = "SELECT * FROM [" + sqlConn.getStrDataBase() + "].[dbo].[prods]";
            Statement st;
            ResultSet result = null;
            try {
                st = sqlConn.getConnection().createStatement();
                result = st.executeQuery(sqlQyery);
                st.close();
            } catch (SQLException e) {
                sql.setConectionError("Error: " + e.getMessage());
                e.printStackTrace();
            }
            return result != null;
        }
    }




}

