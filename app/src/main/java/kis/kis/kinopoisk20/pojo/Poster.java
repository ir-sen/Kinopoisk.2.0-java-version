package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

public class Poster {

    @SerializedName("url")
    String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "url='" + url + '\'' +
                '}';
    }
}
