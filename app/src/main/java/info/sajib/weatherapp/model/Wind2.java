package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sajib on 13-07-2016.
 */
public class Wind2 implements Parcelable{
    private Double speed;

    protected Wind2(Parcel in) {
    }

    public static final Creator<Wind2> CREATOR = new Creator<Wind2>() {
        @Override
        public Wind2 createFromParcel(Parcel in) {
            return new Wind2(in);
        }

        @Override
        public Wind2[] newArray(int size) {
            return new Wind2[size];
        }
    };

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    private Double deg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
