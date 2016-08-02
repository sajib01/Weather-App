package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sajib on 13-07-2016.
 */
public class List implements Parcelable{
    protected List(Parcel in) {
        wind = in.readParcelable(Wind2.class.getClassLoader());
        dt_txt = in.readString();
    }

    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel in) {
            return new List(in);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    private Integer dt;
    private Main2 main;
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    private Rain rain;

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    private Wind2 wind;
    private Sys sys;
    private String dt_txt;

    public Main2 getMain() {
        return main;
    }

    public void setMain(Main2 main) {
        this.main = main;
    }

    public Wind2 getWind() {
        return wind;
    }

    public void setWind(Wind2 wind) {
        this.wind = wind;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(wind, flags);
        dest.writeString(dt_txt);
    }
}
