package kis.kis.kinopoisk20;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import kis.kis.kinopoisk20.data_base.MovieDao;
import kis.kis.kinopoisk20.data_base.MovieDataBase;
import kis.kis.kinopoisk20.pojo.Movie;

public class FavouritesViewModel extends AndroidViewModel {

    private final MovieDao movieDao;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDataBase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieDao.getAllFavoriteMovies();

    }
}
