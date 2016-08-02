package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

/**
 * Created by sajib on 13-07-2016.
 */
public class weatherForecastModel implements Parcelable{
    private City city;
    private java.util.List<List> list = new ArrayList<List>();

    protected weatherForecastModel(Parcel in) {
    }

    public static final Creator<weatherForecastModel> CREATOR = new Creator<weatherForecastModel>() {
        @Override
        public weatherForecastModel createFromParcel(Parcel in) {
            return new weatherForecastModel(in);
        }

        @Override
        public weatherForecastModel[] newArray(int size) {
            return new weatherForecastModel[size];
        }
    };

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
