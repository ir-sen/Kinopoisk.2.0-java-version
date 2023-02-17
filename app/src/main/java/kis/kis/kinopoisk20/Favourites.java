package kis.kis.kinopoisk20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import kis.kis.kinopoisk20.pojo.Movie;

public class Favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        RecyclerView recycleView = findViewById(R.id.favouritesRv);
        MainRecycleViewAdapter movieAdapter = new MainRecycleViewAdapter();
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        recycleView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(
                        Favourites.this,
                        movie
                );
                startActivity(intent);
            }
        });

        FavouritesViewModel viewModel = new ViewModelProvider(this).get(
                FavouritesViewModel.class
        );
        viewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, Favourites.class);
    }


}