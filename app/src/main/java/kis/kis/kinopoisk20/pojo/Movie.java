package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// here we create pojo classes we been use in project
public class Movie implements Serializable {

    @SerializedName("poster")
    Poster poster;

    @SerializedName("rating")
    Rating rating;

    @SerializedName("id")
    int id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("year")
    int year;

    public Movie(Poster poster, Rating rating, int id, String name, String description, int year) {
        this.poster = poster;
        this.rating = rating;
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "poster=" + poster +
                ", rating=" + rating +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
