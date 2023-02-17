package kis.kis.kinopoisk20.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    public
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
