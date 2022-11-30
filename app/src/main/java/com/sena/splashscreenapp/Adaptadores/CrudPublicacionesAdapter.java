package com.sena.splashscreenapp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.splashscreenapp.DetalleActivity;
import com.sena.splashscreenapp.PublicacionesCrud;
import com.sena.splashscreenapp.R;
import com.sena.splashscreenapp.modelos.Publicaciones;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrudPublicacionesAdapter extends RecyclerView.Adapter<CrudPublicacionesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Publicaciones> publicacionesCruds;
    private ArrayList<Publicaciones> publicacionesCrudsOrginal =new ArrayList<>();
    private String imagen = "http://10.0.2.2:800/publicaciones";

    public CrudPublicacionesAdapter(Context context, ArrayList<Publicaciones> publicacionesCruds){
        this.inflater = LayoutInflater.from(context);
        this.publicacionesCruds = publicacionesCrudsOrginal;

    }


    @NonNull
    @Override
    public CrudPublicacionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_publicaciones_dash, parent, false);
        return new CrudPublicacionesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrudPublicacionesAdapter.ViewHolder holder, int position) {
        String Descripcion = publicacionesCruds.get(position).getDescripcion();
        String imagen = publicacionesCruds.get(position).getImagen();
        String Emprendimiento_id = publicacionesCruds.get(position).getEmprendimiento_id();

        holder.txtDescripcion.setText(Descripcion);
        holder.txtEmprendimiento_id.setText(Emprendimiento_id);



        Picasso.get().load(imagen)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imagen);
        Log.d("imagen", imagen);



//        Picasso.get().load(imagen)
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.imagen);


//


    }

    @Override
    public int getItemCount() { return publicacionesCruds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmprendimiento_id, txtDescripcion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idpublicaciones", publicacionesCruds.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }

            });
            txtEmprendimiento_id = itemView.findViewById(R.id.txtEmprendimiento_id);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            imagen = itemView.findViewById(R.id.imageNt);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            publicacionesCruds.clear();
            publicacionesCruds.addAll(publicacionesCrudsOrginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Publicaciones> collect = publicacionesCruds.stream()
                        .filter(i -> i.getDescripcion()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                publicacionesCruds.clear();

                publicacionesCruds.addAll(collect);
            }
            else {
                publicacionesCruds.clear();
                for (Publicaciones i : publicacionesCruds) {
                    if (i.getDescripcion().toLowerCase().contains(strSearch)) {
                        publicacionesCruds.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {
        void itemClick(Publicaciones publicaciones);
    }
}

