package com.example.tmdbapp;

public class MovieModelClass {

    String id;
    String name;
    String img;
    String original_title;
    String original_lang;
    String overview;


    public MovieModelClass(String id, String name, String img, String original_title, String original_lang, String overview) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.original_title = original_title;
        this.original_lang = original_lang;
        this.overview = overview;
    }

    public MovieModelClass() {
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_lang() {
        return original_lang;
    }

    public String getOverview() {
        return overview;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setOriginal_lang(String original_lang) {
        this.original_lang = original_lang;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}