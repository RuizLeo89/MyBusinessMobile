package com.tekno.mybusinessmobile.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tekno.mybusinessmobile.NuevaConexionDialogViewModel;
import com.tekno.mybusinessmobile.R;
import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

import java.util.ArrayList;
import java.util.List;


public class ConexionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private List<ConexionEntity> lstConexiones;
    private MyConexionEntityRecyclerViewAdapter adapterConexiones;
    private NuevaConexionDialogViewModel conexionViewModel;
    private FragmentManager fragmentManager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ConexionFragment() {
    }



    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ConexionFragment newInstance(int columnCount) {
        ConexionFragment fragment = new ConexionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        getActivity(). setTitle("Empresas");

        //Indicamos que el Fragmento tiene un menu de opciones propio
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conexion_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            lstConexiones = new ArrayList();
            //lstConexiones.add(new ConexionEntity("Tekno Servicios", "192.168.1.83", "dataBase", "1433", "mbpmobile", "12345"));
            //lstConexiones.add(new ConexionEntity("OnLine", "192.168.1.83", "dataBase", "1433", "mbpmobile", "12345"));
            //lstConexiones.add(new ConexionEntity("SOLTEC Solutions", "192.168.1.83", "dataBase", "1433", "mbpmobile", "12345"));
            adapterConexiones = new MyConexionEntityRecyclerViewAdapter(getContext(), lstConexiones);
            recyclerView.setAdapter(adapterConexiones);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        conexionViewModel = ViewModelProviders.of(getActivity()).get(NuevaConexionDialogViewModel.class);
        conexionViewModel.getAllConexiones().observe(getActivity(), new Observer<List<ConexionEntity>>() {
            @Override
            public void onChanged(List<ConexionEntity> conexionEntities) {
                adapterConexiones.setNuevasConexiones(conexionEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_conexion_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_conexion:
                mostratDialogNuevaConexion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostratDialogNuevaConexion() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        NuevaConexionDialogFragment dialogNuevaConexion = new NuevaConexionDialogFragment();
        dialogNuevaConexion.show(fm, "NuevaConexionDialogFrament");
    }




}