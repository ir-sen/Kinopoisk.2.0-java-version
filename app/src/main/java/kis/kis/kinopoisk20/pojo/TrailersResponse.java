package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

public class TrailersResponse {

    @SerializedName("videos")
    private TrailersList trailersList;

    public TrailersList getTrailersList() {
        return trailersList;
    }

    public TrailersResponse(TrailersList trailersList) {
        this.trailersList = trailersList;
    }

    @Override
    public String toString() {
        return "TrailersResponse{" +
                "trailersList=" + trailersList +
                '}';
    }
}
