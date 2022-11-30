package com.sena.splashscreenapp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sena.splashscreenapp.DetalleActivity;
import com.sena.splashscreenapp.R;
import com.sena.splashscreenapp.modelos.Empleos;
import com.sena.splashscreenapp.modelos.Invertir;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleoAdapter extends RecyclerView.Adapter<EmpleoAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Empleos> empleos;
    private ArrayList<Empleos> empleosOriginal =new ArrayList<>();
    private EmpleoAdapter.RecyclerItemClick itemClick;


    public EmpleoAdapter(Context context, ArrayList<Empleos> empleos ){
        this.inflater = LayoutInflater.from(context);
        this.empleos = empleos;
        empleosOriginal.addAll(empleos);

    }


    @NonNull
    @Override
    public EmpleoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customempleo_view, parent, false);
        return new EmpleoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String evidencia = empleos.get(position).getEvidencia();
        String mensaje_trabajo = empleos.get(position).getMensaje_trabajo();
        String user_id = empleos.get(position).getUser_id();
        String emprendimiento_id = empleos.get(position).getEmprendimiento_id();

        holder.txtEvidencia.setText(evidencia);
        holder.txtMensaje_trabajo.setText(mensaje_trabajo);
        holder.txtUser_id.setText(user_id);
        holder.txtEmprendimiento_id.setText(emprendimiento_id);
    }

    @Override
    public int getItemCount() { return empleos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEvidencia, txtMensaje_trabajo, txtUser_id, txtEmprendimiento_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idempleos", empleos.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }

            });
            txtEvidencia = itemView.findViewById(R.id.txtEvidencia);
            txtMensaje_trabajo = itemView.findViewById(R.id.txtMensajeTrabajo);
            txtUser_id = itemView.findViewById(R.id.txtUser_id);
            txtEmprendimiento_id = itemView.findViewById(R.id.txtEmprendimiento_id);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            empleos.clear();
            empleos.addAll(empleos);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Empleos> collect = empleos.stream()
                        .filter(i -> i.getMensaje_trabajo()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                empleos.clear();

                empleos.addAll(collect);
            }
            else {
                empleos.clear();
                for (Empleos i : empleos) {
                    if (i.getMensaje_trabajo().toLowerCase().contains(strSearch)) {
                        empleos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {
        void itemClick(Empleos empleos);
    }
}

