package info.sajib.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sajib on 22-07-2016.
 */
public class Main  implements Parcelable{
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    protected Main(Parcel in) {
        temp = in.readDouble();
        pressure = in.readDouble();
        humidity = in.readDouble();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(pressure);
        dest.writeDouble(humidity);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
    }
}
