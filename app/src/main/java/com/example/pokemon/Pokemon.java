package com.example.pokemon;

public class Pokemon {
    int id;
    String name;//los datos que quiero de la app
    String url;
    SpritePokemon sprites;

    public Pokemon(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public SpritePokemon getSprites() {
        return sprites;
    }

    public void setSprites(SpritePokemon sprites) {
        this.sprites = sprites;
    }
}
