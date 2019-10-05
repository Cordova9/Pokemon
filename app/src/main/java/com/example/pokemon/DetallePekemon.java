package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePekemon extends AppCompatActivity {
    ImageView img;
    TextView nombre;
    LinearLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pekemon);
        img = findViewById(R.id.img_pokemon);
        nombre = findViewById(R.id.txt_pokemon);
        ln = findViewById(R.id.ln_pokemon);
        Intent i = getIntent();
        CArgarPokeDatos(i.getStringExtra("nombre_pokemon"));
  }
    void CArgarPokeDatos(String codpokemon){
        Retrofit retrofit = new Retrofit.Builder()//nos permite conectanos a internet
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfazPokemon ip = retrofit.create(InterfazPokemon.class);
        Call<Pokemon> servicio = ip.obtenerPokemon(codpokemon);
        servicio.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {//despues se enlaza con la parte grafica
                Pokemon p = response.body();
                nombre.setText(p.id +" _  "+ p.name);
                Glide.with(DetallePekemon.this)
                        .load(p.sprites.front_default).into(img);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}
