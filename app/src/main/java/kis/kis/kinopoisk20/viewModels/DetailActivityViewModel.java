package kis.kis.kinopoisk20.viewModels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.RetApi.ApiFactory;
import kis.kis.kinopoisk20.data_base.MovieDao;
import kis.kis.kinopoisk20.data_base.MovieDataBase;
import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.pojo.ReviewItem;
import kis.kis.kinopoisk20.pojo.ReviewResponse;
import kis.kis.kinopoisk20.pojo.Trailer;
import kis.kis.kinopoisk20.pojo.TrailersResponse;

public class DetailActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();


    private MutableLiveData<List<ReviewItem>> reviews = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final static String TAG = "DetailActivityViewModel";


    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }
    public LiveData<List<ReviewItem>> getReviews() {
        return reviews;
    }

    // add data base
    private final MovieDao movieDao;

    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
        this.movieDao = MovieDataBase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavoriteMovie(int movieId) {
        return movieDao.getFavoriteMovie(movieId);
    }

    public void isertMovie(Movie movie) {
        Disposable disposable = movieDao.inserMoview(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId) {
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }


    @SuppressLint("CheckResult")
    public void loadTrailers(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // map ???????? ?????? ?????????? ?????? ???? ???????????????? ????????????????????????
                // ?????? ???????????? ?? ???????? ???????????? list<trailers>
                // ?????????????????? ?????????? ???????????? ???? ?????? ???? ?????????? ??????????????????????????
                .map(new Function<TrailersResponse, List<Trailer>>() {

                    @Override
                    public List<Trailer> apply(TrailersResponse trailersResponse) throws Throwable {
                        return trailersResponse.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                               @Override
                               public void accept(List<Trailer> listTrailers) throws Throwable {
                                   trailers.setValue(listTrailers);
                               }
                           }, new Consumer<Throwable>() {
                               @SuppressLint("CheckResult")
                               @Override
                               public void accept(Throwable throwable) throws Throwable {
                                   Log.d(TAG, throwable.toString());
                               }
                           }
                );
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int id) {
        Disposable disposable = ApiFactory.apiService.getAllReview(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewResponse, List<ReviewItem>>() {

                    @Override
                    public List<ReviewItem> apply(ReviewResponse reviewResponse) throws Throwable {
                        return reviewResponse.getReviewItemList();
                    }
                })
                .subscribe(new Consumer<List<ReviewItem>>() {
                    @Override
                    public void accept(List<ReviewItem> reviewItemList) throws Throwable {
                        reviews.setValue(reviewItemList );
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
