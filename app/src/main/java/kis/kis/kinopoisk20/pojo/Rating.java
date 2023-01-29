package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    double kp;

    public double getRatingKp() {
        return kp;
    }

    public void setKp(double kp) {
        this.kp = kp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
