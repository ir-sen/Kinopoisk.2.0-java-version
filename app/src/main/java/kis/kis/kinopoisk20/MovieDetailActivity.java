package kis.kis.kinopoisk20;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.RetApi.ApiFactory;
import kis.kis.kinopoisk20.databinding.ActivityMoviewDetailBinding;
import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.pojo.TrailersResponse;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMoviewDetailBinding binding;

    private final static String EXTRA_KEY = "movie";

    private final static String TAG = "MovieDetailActivity";

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

        ApiFactory.apiService.loadTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailersResponse>() {
                               @Override
                               public void accept(TrailersResponse trailersResponse) throws Throwable {
                                   Log.d(TAG, trailersResponse.toString());
                               }
                           }, new Consumer<Throwable>() {
                               @SuppressLint("CheckResult")
                               @Override
                               public void accept(Throwable throwable) throws Throwable {
                                   Log.d(TAG, throwable.toString());
                               }
                           }
                );

    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_KEY, movie);
        return intent;

    }
}

