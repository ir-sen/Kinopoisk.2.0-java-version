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
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.RetApi.ApiFactory;
import kis.kis.kinopoisk20.pojo.Movie;

public class MainViewModel extends AndroidViewModel {
    // here we save all get movies
    private final MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
    // loading value
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // rx java composable
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    private final String TAG = "MainViewModel";
    private int page = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }
    // For get list out
    public LiveData<List<Movie>> getListMovies() {
        return listMovies;
    }

    public void loadMovies() {
        Boolean loading = isLoading.getValue();
        if (loading != null && loading) {
            return;
        }
        Disposable disposable = ApiFactory.apiService.loadMovie(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // start load activity
                .doOnSubscribe(disposable1 -> isLoading.setValue(true))
                // end load activity
                .doAfterTerminate(() -> isLoading.setValue(false))
                .subscribe(movieResponse -> {
                    Log.d(TAG, movieResponse.toString());
                    List<Movie> loadMovies = listMovies.getValue();
                    // if first time init new list movies
                    if (loadMovies != null) {
                        loadMovies.addAll(movieResponse.getMoviesInf());
                        listMovies.setValue(loadMovies);
                    } else {
                        // else add old list
                        listMovies.setValue(movieResponse.getMoviesInf());
                    }
                    page++;

                }, throwable -> Log.d(TAG, throwable.toString())
                );

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
