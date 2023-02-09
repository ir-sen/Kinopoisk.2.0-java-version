package kis.kis.kinopoisk20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kis.kis.kinopoisk20.databinding.ActivityMoviewDetailBinding;
import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.pojo.Trailer;
import kis.kis.kinopoisk20.viewModels.DetailActivityViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMoviewDetailBinding binding;

    private final static String EXTRA_KEY = "movie";

    private final static String TAG = "MovieDetailActivity";

    private DetailActivityViewModel viewModel;

    private TrailersAdapter trailersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviewDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);
        initRecycleViewTrailers();
        GetMovie();


    }

    private void initRecycleViewTrailers() {
        trailersAdapter = new TrailersAdapter();
        binding.trailerRv.setAdapter(trailersAdapter);

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

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setListTrailers(trailers);
                Log.d(TAG, trailers.toString());
            }
        });

    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_KEY, movie);
        return intent;

    }
}

