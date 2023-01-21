package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("docs")
    List<Movie> moviesInf;

    public MovieResponse(List<Movie> moviesInf) {
        this.moviesInf = moviesInf;
    }

    public List<Movie> getMoviesInf() {
        return moviesInf;
    }
}
