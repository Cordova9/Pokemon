package com.example.pokemon.AdaptadorPokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.Pokemon;

import java.util.List;

public class AdaptadorPokedex extends RecyclerView.Adapter<PokedexHolder> {//la clase
    Context context;//referencia del contexto
    int layout;
    List<Pokemon> datos;
    LayoutInflater layoutInflater;

    public AdaptadorPokedex(Context context, int layout, List<Pokemon> datos) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PokedexHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(layout, parent, false);//vista para cada item
        return new PokedexHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexHolder holder, int position) {//le decimos al holder le pasamos el dato que deberia de tener
        holder.nombrePokemon.setText(datos.get(position).getName());
        //holder.id = datos.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }//para el tamaño
}
