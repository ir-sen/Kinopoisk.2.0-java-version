package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    String kp;

    public String getRatingKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
