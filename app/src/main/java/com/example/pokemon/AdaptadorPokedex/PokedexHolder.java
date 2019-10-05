package com.example.pokemon.AdaptadorPokedex;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.DetallePekemon;
import com.example.pokemon.MainActivity;
import com.example.pokemon.R;

public class PokedexHolder extends RecyclerView.ViewHolder {

TextView nombrePokemon;
CardView cv;
Context context;
int id;

    public PokedexHolder(@NonNull View itemView, final Context c) {
        super(itemView);
        this.context = c;
        nombrePokemon = itemView.findViewById(R.id.nombre_pokemon);
        cv = itemView.findViewById(R.id.card_view);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mandar a una nueva actividad
                Intent intent = new Intent(context, DetallePekemon.class);
                intent.putExtra("nombre_pokemon",nombrePokemon.getText().toString());
                context.startActivity(intent);
            }
        });
    }
}
