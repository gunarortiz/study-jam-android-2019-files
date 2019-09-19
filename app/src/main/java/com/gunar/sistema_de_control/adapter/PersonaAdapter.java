package com.gunar.sistema_de_control.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.gunar.sistema_de_control.R;
import com.gunar.sistema_de_control.model.Persona;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.MyViewHolder> {

    private List<Persona> comenList;
    private static ClickListener clickListener;
    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView nombre, carnet, telefono;

        public MyViewHolder(View view) {
            super(view);

            nombre = (TextView) view.findViewById(R.id.nombre);
            carnet = (TextView) view.findViewById(R.id.carnet);
            telefono = (TextView) view.findViewById(R.id.telefono);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public PersonaAdapter(List<Persona> myDataset) {
        comenList = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comen_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Persona comen = comenList.get(position);

        holder.nombre.setText(comen.getNombre());
        holder.carnet.setText(comen.getCarnet());
        holder.telefono.setText(comen.getTelefono());
    }

    @Override
    public int getItemCount() {
        return comenList.size();
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        PersonaAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }


}


