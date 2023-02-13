package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("docs")
    private List<ReviewItem> reviewItemList;

    public ReviewResponse(List<ReviewItem> reviewItemList) {
        this.reviewItemList = reviewItemList;
    }

    public List<ReviewItem> getReviewItemList() {
        return reviewItemList;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewItemList=" + reviewItemList +
                '}';
    }
}
