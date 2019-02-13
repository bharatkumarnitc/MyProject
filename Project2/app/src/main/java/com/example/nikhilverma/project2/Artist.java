package com.example.nikhilverma.project2;

public class Artist {
    String name;
    String generes;
    String id;

    public Artist(String name, String generes, String id) {
        this.name = name;
        this.generes = generes;
        this.id = id;
    }

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public String getGeneres() {
        return generes;
    }

    public String getId() {
        return id;
    }
}
