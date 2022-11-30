package com.sena.splashscreenapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.splashscreenapp.R;
import com.sena.splashscreenapp.modelos.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    private List<Post> items;
    private LayoutInflater mInflanter;
    private Context context;


    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_cards, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int i) {
            holder.imagen.setImageResource(items.get(i).getImagen());
            holder.descripcion.setText(items.get(i).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView descripcion;



        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen= itemView.findViewById(R.id.imagen);
            descripcion= itemView.findViewById(R.id.descipcion);
        }
    }
    public void setItems(List<Post> items){items = items;}

    public PostAdapter(List<Post> items) {
        this.items = items;
    }
}
