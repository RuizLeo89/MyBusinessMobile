package com.tekno.mybusinessmobile.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekno.mybusinessmobile.R;
import com.tekno.mybusinessmobile.db.entity.ConexionEntity;

import java.util.List;

public class MyConexionEntityRecyclerViewAdapter extends RecyclerView.Adapter<MyConexionEntityRecyclerViewAdapter.ViewHolder> {

    private final Context ctx;
    private List<ConexionEntity> mValues;

    public MyConexionEntityRecyclerViewAdapter(Context context, List<ConexionEntity> items) {
        ctx = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conexion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewEmpresa.setText(holder.mItem.getEmpresa());
        holder.imageViewEditar.setImageResource(R.drawable.ic_baseline_edit_24);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasConexiones(List<ConexionEntity> nuevasConexiones) {
        this.mValues = nuevasConexiones;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewEmpresa;
        public final ImageView imageViewEditar;
        public ConexionEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewEmpresa = view.findViewById(R.id.textViewEmpresa);
            imageViewEditar = view.findViewById(R.id.imageViewEditar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewEmpresa.getText() + "'";
        }
    }
}