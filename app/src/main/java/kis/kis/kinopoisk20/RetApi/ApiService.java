package kis.kis.kinopoisk20.RetApi;

import io.reactivex.rxjava3.core.Single;
import kis.kis.kinopoisk20.pojo.MovieResponse;
import kis.kis.kinopoisk20.pojo.ReviewResponse;
import kis.kis.kinopoisk20.pojo.TrailersResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
        // api request service
    // just test token : ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06
//    @GET("movie?token=7D2G2GJ-5PWMMVV-PXJ80HC-8DMAAG7&field=rating.kp&search=1-10&sortField=votes.kp$sortType=-5&limit=10")
    @GET("movie?field=rating.kp&search=7-10&field=year&search=2017-2020&field=typeNumber&search=2&sortField=year&sortType=1&sortField=votes.imdb&sortType=-1&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06")
    Single<MovieResponse> loadMovie(@Query("page") int page);

    @GET("movie?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=id")
    Single<TrailersResponse> loadTrailers(@Query("search") int id);

    @GET("review?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=movieId")
    Single<ReviewResponse> getAllReview(@Query("search") int id);

}
