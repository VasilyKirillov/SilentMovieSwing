package model;

public class Genre {
    private int id;
    private String genre;
    
    public Genre() {}

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
