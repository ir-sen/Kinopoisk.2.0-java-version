package kis.kis.kinopoisk20;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.data_base.MovieDao;
import kis.kis.kinopoisk20.data_base.MovieDataBase;
import kis.kis.kinopoisk20.databinding.ActivityMoviewDetailBinding;
import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.pojo.ReviewItem;
import kis.kis.kinopoisk20.pojo.Trailer;
import kis.kis.kinopoisk20.viewModels.DetailActivityViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMoviewDetailBinding binding;

    private final static String EXTRA_KEY = "movie";

    private final static String TAG = "MovieDetailActivity";

    private DetailActivityViewModel viewModel;

    private TrailersAdapter trailersAdapter;



    private ReviewAdapter reviewAdapter;
    private RecyclerView reviewRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoviewDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        viewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);
        initRecycleViewTrailers();
        GetMovie();
        initRecycle();


        // test DAta base
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_KEY);
        MovieDao movieDao = MovieDataBase.getInstance(getApplication()).movieDao();

        Drawable star_on = ContextCompat
                .getDrawable(MovieDetailActivity.this, android.R.drawable.star_on);
        Drawable star_off = ContextCompat
                .getDrawable(MovieDetailActivity.this, android.R.drawable.star_off);
        viewModel.getFavoriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieDb) {
                if (movieDb == null) {
                    binding.starIv.setImageDrawable(star_off);
                    binding.starIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.isertMovie(movie);
                        }
                    });
                } else {
                    binding.starIv.setImageDrawable(star_on);
                    binding.starIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.removeMovie(movie.getId());
                        }
                    });
                }
            }
        });

    }


    private void initRecycle() {
        reviewRecycle = binding.reviewRv;
        reviewAdapter = new ReviewAdapter();
        reviewRecycle.setAdapter(reviewAdapter);
        viewModel.getReviews().observe(this, new Observer<List<ReviewItem>>() {
            @Override
            public void onChanged(List<ReviewItem> reviewItemList) {
                reviewAdapter.setListReview(reviewItemList);
            }
        });


    }

    private void initRecycleViewTrailers() {
        trailersAdapter = new TrailersAdapter();
        binding.trailerRv.setAdapter(trailersAdapter);

        trailersAdapter.setOnTrailerItemClickListener(new TrailersAdapter.OnTrailerItemClickListener() {
            @Override
            public void onItemClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });


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
            }
        });


        viewModel.loadReviews(movie.getId());


    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_KEY, movie);
        return intent;

    }
}

