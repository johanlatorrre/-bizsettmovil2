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
import com.sena.splashscreenapp.modelos.Invertir;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvertirAdapter extends RecyclerView.Adapter<InvertirAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Invertir> invertirs;
    private ArrayList<Invertir> invertirsOriginal =new ArrayList<>();
    private RecyclerItemClick itemClick;


    public InvertirAdapter(Context context, ArrayList<Invertir> invertirs ){
        this.inflater = LayoutInflater.from(context);
        this.invertirs = invertirs;
        invertirsOriginal.addAll(invertirs);

    }


    @NonNull
    @Override
    public InvertirAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custominvertir_view, parent, false);
        return new InvertirAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String propuesta = invertirs.get(position).getPropuesta();
        String user_id = invertirs.get(position).getUser_id();
        String emprendimiento_id = invertirs.get(position).getEmprendimiento_id();

        holder.txtPropuesta.setText(propuesta);
        holder.txtUser_id.setText(user_id);
        holder.txtEmprendimiento_id.setText(emprendimiento_id);
    }

    @Override
    public int getItemCount() { return invertirs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPropuesta, txtUser_id, txtEmprendimiento_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idinvertir", invertirs.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }

            });
            txtPropuesta = itemView.findViewById(R.id.txtPropuesta);
            txtUser_id = itemView.findViewById(R.id.txtUser_id);
            txtEmprendimiento_id = itemView.findViewById(R.id.txtEmprendimiento_id);
//        imagen = itemView.findViewById(R.id.imageNt);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            invertirs.clear();
            invertirs.addAll(invertirsOriginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Invertir> collect = invertirs.stream()
                        .filter(i -> i.getPropuesta()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                invertirs.clear();

                invertirs.addAll(collect);
            }
            else {
                invertirs.clear();
                for (Invertir i : invertirs) {
                    if (i.getPropuesta().toLowerCase().contains(strSearch)) {
                        invertirs.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public interface RecyclerItemClick {
        void itemClick(Invertir invertir);
    }
}

