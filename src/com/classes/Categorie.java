package com.classes;



public class Categorie {

    private long id_categorie;
    private String title;

    public Categorie() { }
    public Categorie(String title) {
        this.title = title;
    }

    public Categorie(long id, String title) {
        this.id_categorie = id;
        this.title = title;
    }
    public String toString(){
        return title;
    }

    public long getId() {
        return id_categorie;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id_categorie = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
