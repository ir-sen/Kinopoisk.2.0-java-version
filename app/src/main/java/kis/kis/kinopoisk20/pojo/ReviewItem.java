package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

public class ReviewItem {

    @SerializedName("author")
    private String author;

    @SerializedName("review")
    private String review;

    @SerializedName("type")
    private String type;

    public ReviewItem(String author, String review, String type) {
        this.author = author;
        this.review = review;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ReviewItem{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
