package kis.kis.kinopoisk20.data_base;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kis.kis.kinopoisk20.pojo.Movie;

@Database(entities = {Movie.class}, version = 3, exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {

    private static final String DB_NAME = "movie.db";
    private static MovieDataBase instance = null;
    // create singlton
    public static MovieDataBase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    MovieDataBase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    public abstract MovieDao movieDao();

}
