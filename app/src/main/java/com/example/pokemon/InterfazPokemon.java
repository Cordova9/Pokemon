package com.example.pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfazPokemon {//hay vearios metodos
    @GET("pokemon/")//cuando haga la consulta los de abajo se guardan ariiba----ruta que tiene
    Call<Pokedex> ObtenerPokedex(@Query("limit")int cantidad);//parametro--quue tenga el nombre que pide a url

    //creamos un nuevo servicio

    @GET("pokemon/{codigo}/")
    Call<Pokemon> obtenerPokemon (@Path("codigo") int id);//id es lo mismo que el 1 para tener la informacion de ese pokemon

    @GET("pokemon/{codigo}/")
    Call<Pokemon> obtenerPokemon (@Path("codigo") String id);
}
