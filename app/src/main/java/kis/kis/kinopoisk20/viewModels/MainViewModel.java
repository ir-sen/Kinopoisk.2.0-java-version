package kis.kis.kinopoisk20.viewModels;

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
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.RetApi.ApiFactory;
import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.pojo.MovieResponse;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final String TAG = "MainViewModel";
    private int page = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Movie>> getListMovies() {
        return listMovies;
    }

    public void loadMovies() {
        Disposable disposable = ApiFactory.apiService.loadMovie(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                               @Override
                               public void accept(MovieResponse movieResponse) throws Throwable {
                                   Log.d(TAG, movieResponse.toString());
                                   page++;
                                   listMovies.setValue(movieResponse.getMoviesInf());

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Throwable {
                                    Log.d(TAG, throwable.toString());
                               }
                           }
                );

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
