package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajib on 10-07-2016.
 */
public class WeatherModel implements Parcelable {

    protected WeatherModel(Parcel in) {
        base = in.readString();
        name = in.readString();
    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    private List<Weather> weather = new ArrayList<Weather>();
    private String base;
    private Main main;
    private Wind wind;
    private Integer dt;
    private Integer id;
    private Sys sys;
    private String name;
    private Integer cod;

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(base);
        dest.writeString(name);
    }
}
