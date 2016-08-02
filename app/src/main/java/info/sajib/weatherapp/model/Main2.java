package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sajib on 13-07-2016.
 */
public class Main2 implements Parcelable{
    private Double temp;

    private Double temp_min;

    private Double temp_max;

    private Double pressure;

    private Double sea_level;

    private Double grnd_level;

    private Integer humidity;

    protected Main2(Parcel in) {
    }

    public static final Creator<Main2> CREATOR = new Creator<Main2>() {
        @Override
        public Main2 createFromParcel(Parcel in) {
            return new Main2(in);
        }

        @Override
        public Main2[] newArray(int size) {
            return new Main2[size];
        }
    };

    public Double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(Double temp_kf) {
        this.temp_kf = temp_kf;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(Double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getSea_level() {
        return sea_level;
    }

    public void setSea_level(Double sea_level) {
        this.sea_level = sea_level;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    private Double temp_kf;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
