package kis.kis.kinopoisk20.viewModels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kis.kis.kinopoisk20.RetApi.ApiFactory;
import kis.kis.kinopoisk20.pojo.Trailer;
import kis.kis.kinopoisk20.pojo.TrailersResponse;

public class DetailActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final static String TAG = "DetailActivityViewModel";


    public MutableLiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public DetailActivityViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    public void loadTrailers(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // map если нам нужно что бы прилетал определенный
                // тип данных в этом случии list<trailers>
                // указываем какой объект во что мы хотим приобразовать
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

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
