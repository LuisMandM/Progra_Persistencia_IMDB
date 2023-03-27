package com.ikasgela;

public class Pelicula {
    private String rank;
    private String title;
    private String genre;
    private String director;
    private int year;
    private String rating;

    private String runtime;


    public Pelicula(String rank, String title, String genre, String director, int year,
                    String rating, String runtime) {
        this.rank = rank;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
