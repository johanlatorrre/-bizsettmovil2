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
import com.sena.splashscreenapp.modelos.Emprendimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Emprendimiento> emprendimientos = new ArrayList<>();
    private ArrayList<Emprendimiento> emprendimientosOrginal =new ArrayList<>();
    private RecyclerItemClick itemClick;
    private String domain_image = "http://10.0.2.2:800/storage/multimedia_folder/";


    public Adapter(Context context, ArrayList<Emprendimiento> emprendimientos) {
        this.inflater = LayoutInflater.from(context);
        this.emprendimientos = emprendimientos;
        emprendimientosOrginal.addAll(emprendimientos);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_emprendimientos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Nombre = emprendimientos.get(position).getNombre();
        String Categoria = emprendimientos.get(position).getCategoria();
        String Descripcion = emprendimientos.get(position).getDescripcion();
        String Telefono = emprendimientos.get(position).getTelefono();
        String Direccion = emprendimientos.get(position).getDireccion();


        holder.txtNombre.setText(Nombre);
        holder.txtCategoria.setText(Categoria);
        holder.txtDescripcion.setText(Descripcion);
        holder.txtTelefono.setText(Telefono);
        holder.txtDireccion.setText(Direccion);

    }

    @Override
    public int getItemCount() { return emprendimientos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtCategoria, txtDescripcion, txtTelefono, txtDireccion;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idemprendimientos", emprendimientos.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }

            });
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            emprendimientos.clear();
            emprendimientos.addAll(emprendimientosOrginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Emprendimiento> collect = emprendimientos.stream()
                        .filter(i -> i.getNombre()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                emprendimientos.clear();

                emprendimientos.addAll(collect);
            }
            else {
                emprendimientos.clear();
                for (Emprendimiento i : emprendimientosOrginal) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        emprendimientos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {
        void itemClick(Emprendimiento emprendimiento);
    }

}


