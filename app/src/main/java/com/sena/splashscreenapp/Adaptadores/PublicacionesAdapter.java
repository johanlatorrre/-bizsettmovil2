package com.sena.splashscreenapp.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.splashscreenapp.PublicacionesEdit;
import com.sena.splashscreenapp.R;
import com.sena.splashscreenapp.modelos.Publicaciones;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Publicaciones> publicaciones ;
    private ArrayList<Publicaciones> publicacionesOrginal =new ArrayList<>();
    private String imagen = "http://10.0.2.2:800/publicaciones";

    public PublicacionesAdapter(Context context, ArrayList<Publicaciones> publicaciones){
        this.inflater = LayoutInflater.from(context);
        this.publicaciones = publicaciones;
        publicacionesOrginal.addAll(publicaciones);

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_view, parent, false);

        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String Descripcion = publicaciones.get(position).getDescripcion();
        String imagen = publicaciones.get(position).getImagen();
        String Emprendimiento_id = publicaciones.get(position).getEmprendimiento_id();

        holder.txtDescripcion.setText(Descripcion);
        holder.txtEmprendimiento_id.setText(Emprendimiento_id);


//
//        Picasso.get().load(imagen)
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.imagen);
//        Log.d("imagen", imagen);



        Picasso.get().load(imagen)
                .error(R.drawable.imagenpublicaciones)
                .into(holder.imagen);



    }

    @Override
    public int getItemCount() { return publicaciones.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmprendimiento_id, txtDescripcion;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), PublicacionesEdit.class);
                    intent.putExtra("id", String.valueOf(publicaciones.get(getAdapterPosition()).getId()));
                    intent.putExtra("descripcion", String.valueOf(publicaciones.get(getAdapterPosition()).getDescripcion()));
                    intent.putExtra("emprendimiento_id", String.valueOf(publicaciones.get(getAdapterPosition()).getEmprendimiento_id()));
                    intent.putExtra("idpublicaciones", publicaciones.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);




//                Intent intent = new Intent(v.getContext(), DetalleActivity.class);
//                intent.putExtra("idpublicaciones", publicaciones.
//                        get(getAdapterPosition()).getId());
//                v.getContext().startActivity(intent);

//                Intent intent= new Intent(itemView, PublicacionesCrud.class);


                }

            });
            txtEmprendimiento_id = itemView.findViewById(R.id.txtEmprendimiento_id);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            imagen = itemView.findViewById(R.id.imageNt);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            publicaciones.clear();
            publicaciones.addAll(publicacionesOrginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Publicaciones> collect = publicaciones.stream()
                        .filter(i -> i.getDescripcion()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                publicaciones.clear();

                publicaciones.addAll(collect);
            }
            else {
                publicaciones.clear();
                for (Publicaciones i : publicaciones) {
                    if (i.getDescripcion().toLowerCase().contains(strSearch)) {
                        publicaciones.add(i);
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
