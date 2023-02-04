package kis.kis.kinopoisk20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import kis.kis.kinopoisk20.databinding.ActivityMoviewDetailBinding;
import kis.kis.kinopoisk20.pojo.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMoviewDetailBinding binding;

    private final static String EXTRA_KEY = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviewDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        GetMovie();

    }
    //  get and set movie to activity
    private void GetMovie() {
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_KEY);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(binding.mainPosterIv);
        binding.titleTv.setText(movie.getName());
        binding.yearTv.setText(String.valueOf(movie.getYear()));
        binding.descriptionTv.setText(movie.getDescription());

    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_KEY, movie);
        return intent;

    }
}

