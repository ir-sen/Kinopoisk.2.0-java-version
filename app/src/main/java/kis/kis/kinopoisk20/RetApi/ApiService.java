package kis.kis.kinopoisk20.RetApi;

import io.reactivex.rxjava3.core.Single;
import kis.kis.kinopoisk20.pojo.MovieResponse;
import retrofit2.http.GET;


public interface ApiService {

    @GET("movie?token=7D2G2GJ-5PWMMVV-PXJ80HC-8DMAAG7&field=rating.kp&search=7-10&sortField=votes.kp$sortType=-1")
    Single<MovieResponse> loadMovie();


}
