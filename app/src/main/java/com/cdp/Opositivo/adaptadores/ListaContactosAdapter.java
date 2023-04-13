package com.cdp.Opositivo.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.Opositivo.R;
import com.cdp.Opositivo.VerActivity;
import com.cdp.Opositivo.entidades.Donante;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Donante> listaDonante;
    ArrayList<Donante> listaOriginal;

    public ListaContactosAdapter(ArrayList<Donante> listaDonante) {
        this.listaDonante = listaDonante;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaDonante);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaDonante.get(position).getNombre());
        holder.viewEmail.setText(listaDonante.get(position).getEmail());
        holder.viewTelefono.setText(listaDonante.get(position).getTelefono());
        holder.viewDoc_identidad.setText(listaDonante.get(position).getDoc_identidad());
        holder.viewTipo_sangre.setText(listaDonante.get(position).getTipo_sangre());
        holder.viewDireccion.setText(listaDonante.get(position).getDireccion());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaDonante.clear();
            listaDonante.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Donante> collecion = listaDonante.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaDonante.clear();
                listaDonante.addAll(collecion);
            } else {
                for (Donante c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaDonante.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaDonante.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewEmail, viewTelefono, viewDoc_identidad, viewTipo_sangre, viewDireccion;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewEmail = itemView.findViewById(R.id.viewEmail);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewDoc_identidad = itemView.findViewById(R.id.viewDoc_identidad);
            viewTipo_sangre = itemView.findViewById(R.id.viewTipo_sangre);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaDonante.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
