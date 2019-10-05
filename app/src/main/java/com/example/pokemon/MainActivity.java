package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;
;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;


import com.example.pokemon.AdaptadorPokedex.AdaptadorPokedex;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycker_view;
    SearchView search;
    List<Pokemon> poke_lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        recycker_view = findViewById(R.id.recycker_view);
        search = findViewById(R.id.search);
        CargarPokedex();
        setTitle("Pokedexxxxx");

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {//buscar
                Log.e("SearchO",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {//editando el texto
                Log.e("SearchV",newText);
                List<Pokemon> poketemp = new ArrayList<>();
                for (Pokemon poke : poke_lista){
                    if(poke.name.contains(newText)){
                        poketemp.add(poke);
                    }
                }// creo una lista de pokemon y no uso la wue t¿esta hecha porque la anterior lista estan todos los pokemon ---no tiene ninguna referencia y elimina los anteriores
                AdaptadorPokedex adaptadorPokedex =
                        new AdaptadorPokedex(MainActivity.this,R.layout.item_pokedex,poketemp);
                recycker_view.setAdapter(adaptadorPokedex);
                return false;
            }
        });
    }

    void CargarPokedex() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfazPokemon ip = retrofit.create(InterfazPokemon.class);
        Call<Pokedex> servicio = ip.ObtenerPokedex(151);

        servicio.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {//cuando hay internet--vamos a iterar
                switch (response.code()) {
                    case 200:
                        Pokedex pokedex = response.body();//aqui itero--el cuerpo de la respuesta es el objeto que quiero utilizar
                        poke_lista = pokedex.results;
                        /*for (Pokemon p : pokedex.results) {
                            Log.e("Pokemon", p.name);
                        }*/
                        AdaptadorPokedex adaptadorPokedex =
                                new AdaptadorPokedex(MainActivity.this, R.layout.item_pokedex,pokedex.results);
                        recycker_view.setAdapter(adaptadorPokedex);
                        recycker_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        break;
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {//cuando no hay internet

            }
        });
    }
}

