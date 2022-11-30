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

import com.google.android.material.navigation.NavigationView;
import com.sena.splashscreenapp.DetalleActivity;
import com.sena.splashscreenapp.R;
import com.sena.splashscreenapp.modelos.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Users> users;
    private ArrayList<Users> usersOriginal =new ArrayList<>();
    private UsersAdapter.RecyclerItemClick itemClick;


    public UsersAdapter(Context context, ArrayList<Users> users ){
        this.inflater = LayoutInflater.from(context);
        this.users = users;
        usersOriginal.addAll(users);

    }


    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customusers, parent, false);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        String email = users.get(position).getEmail();
        String password = users.get(position).getPassword();
        String nombre = users.get(position).getNombre();
        String apellidos = users.get(position).getApellidos();
        String numero_documento = users.get(position).getNumero_documento();

        holder.txtEmail.setText(email);
        holder.txtPassword.setText(password);
        holder.txtNombre.setText(nombre);
        holder.txtApellidos.setText(apellidos);
        holder.txtNumero_documento.setText(numero_documento);
    }

    @Override
    public int getItemCount() { return users.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmail, txtPassword, txtNombre, txtApellidos, txtNumero_documento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("id", users.
                            get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }

            });
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPassword = itemView.findViewById(R.id.txtPassword);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtApellidos = itemView.findViewById(R.id.txtApellidos);
            txtNumero_documento =itemView.findViewById(R.id.txtNumero_documento);
        }
    }


    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            users.clear();
            users.addAll(usersOriginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Users> collect = users.stream()
                        .filter(i -> i.getNombre()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                users.clear();

                users.addAll(collect);
            }
            else {
                users.clear();
                for (Users i : users) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        users.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }




    public interface RecyclerItemClick {
        void itemClick(Users users);
    }
}

