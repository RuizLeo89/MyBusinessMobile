package com.tekno.mybusinessmobile.ui;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tekno.mybusinessmobile.NuevaConexionDialogViewModel;
import com.tekno.mybusinessmobile.R;
import com.tekno.mybusinessmobile.db.entity.ConexionEntity;
import com.tekno.mybusinessmobile.sqlserver.SQLServerConnection;

public class NuevaConexionDialogFragment extends DialogFragment implements View.OnClickListener {

    private View view;
    private EditText editTextEmpresa;
    private EditText editTextServidor;
    private EditText editTextBD;
    private EditText editTextPuerto;
    private EditText editTextUsuario;
    private EditText editTextPassword;
    private Button buttonProbar;

    public static NuevaConexionDialogFragment newInstance() {
        return new NuevaConexionDialogFragment();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_conexion_dialog_fragment, null);

        editTextEmpresa = view.findViewById(R.id.editTextEmpresa);
        editTextServidor = view.findViewById(R.id.editTextServidor);
        editTextPuerto = view.findViewById(R.id.editTextPuerto);
        editTextBD = view.findViewById(R.id.editTextBD);
        editTextUsuario = view.findViewById(R.id.editTextUsuario);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonProbar = view.findViewById(R.id.buttonProbar);
        builder.setView(view);
        buttonProbar.setOnClickListener(this);

        builder.setTitle("Nueva Conexión")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String empresa = editTextEmpresa.getText().toString().trim();
                        String servidor = editTextServidor.getText().toString().trim();
                        String puerto = editTextPuerto.getText().toString().trim();
                        String bd = editTextBD.getText().toString().trim();
                        String usuario = editTextUsuario.getText().toString().trim();
                        String password = editTextPassword.getText().toString().trim();
                        dialog.dismiss();

                        NuevaConexionDialogViewModel mViewModel = ViewModelProviders.of(getActivity())
                                .get(NuevaConexionDialogViewModel.class);
                        mViewModel.insertarConexion(new ConexionEntity(empresa, servidor, puerto, bd, usuario, password));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });



        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        String empresa = editTextEmpresa.getText().toString().trim();
        String servidor = editTextServidor.getText().toString().trim();
        String bd = editTextBD.getText().toString().trim();
        String puerto = editTextPuerto.getText().toString().trim();
        String usuario = editTextUsuario.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        SQLServerConnection sqlConecction = new SQLServerConnection(servidor, puerto, bd, usuario, password);
        sqlConecction.connect(getActivity());

        /*if(connection != null) {
            Toast.makeText(getActivity(), "Conexión exitosa", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getActivity(), sqlConecction.getConectionError(), Toast.LENGTH_LONG).show();
        }*/
    }
}