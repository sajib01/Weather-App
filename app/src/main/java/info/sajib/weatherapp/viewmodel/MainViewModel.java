package info.sajib.weatherapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import info.sajib.weatherapp.view.Graphview;
import info.sajib.weatherapp.MyApplication;
import info.sajib.weatherapp.RecyclerviewAdapter;
import info.sajib.weatherapp.model.List;
import info.sajib.weatherapp.model.WeatherModel;
import info.sajib.weatherapp.model.WeatherService;
import info.sajib.weatherapp.model.weatherForecastModel;
import retrofit2.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sajib on 11-07-2016.
 */
public class MainViewModel implements RecyclerviewAdapter.OnItemClickListner {
    Context context;
    Subscription subscription;
    Subscription sub;
    Double longitude;
    Double latitude;
    String AppID;
    Weatherdata weatherdata;
    weatherForecastModel weatherforecastmodel;
    RecyclerviewAdapter recyclerviewAdapter;
    float weatherdataa[];
    float precipitaionArray[];
    String dt[];
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> pressure = new ObservableField<>();
    public final ObservableField<String> humidity = new ObservableField<>();
    public final ObservableField<String> windspeed = new ObservableField<>();
    public final ObservableField<String> winddegree = new ObservableField<>();
    public final ObservableField<String> sunrise = new ObservableField<>();
    public final ObservableField<String> sunset = new ObservableField<>();
    public final ObservableField<String> temp = new ObservableField<>();
    public final ObservableField<String> mintemp = new ObservableField<>();
    public final ObservableField<String> maxtemp = new ObservableField<>();
    public final ObservableField<Boolean> isloaded = new ObservableField<>(false);
    public final ObservableField<Boolean> isload = new ObservableField<>(false);
    public final ObservableField<Drawable> imageicon = new ObservableField<>();
    public final ObservableField<String> infomessage = new ObservableField<>();
    public final ObservableInt infomessagevisibility = new ObservableInt();

    double centi;
    double faren;
    double centimax;
    double farenmax;
    double common;
    double commonmax;
    double commonmin;
    double centimin;
    double farenmin;

    Map<String, String> data = new HashMap<>();
    String search;

    public MainViewModel(Context context, String finalAddress, Double longitude, Double latitude, Weatherdata weatherdata, String search, RecyclerviewAdapter recyclerviewAdapter) {
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;
        this.search = search;
        address.set(finalAddress);
        infomessagevisibility.set(View.INVISIBLE);
        AppID = MyApplication.APP_ID;
        this.weatherdata = weatherdata;
        final Handler handler = new Handler();
        this.recyclerviewAdapter = recyclerviewAdapter;

        if (longitude != null && latitude != null) {
            data.put("lat", String.valueOf(latitude));
            data.put("lon", String.valueOf(longitude));
            data.put("appid", AppID);
            data.put("units", "metric");
        }
        if (search != null) {
            data.put("appid", AppID);
            data.put("units", "metric");
            data.put("q", search);
        }
        LoadData(data);
        LoadForeCastData(data);

    }

    private void LoadForeCastData(Map<String, String> data) {
        WeatherService forecast = WeatherService.Factory.create();
        sub = forecast.getForecast(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<weatherForecastModel>() {
                    @Override
                    public void onCompleted() {
                        weatherdata.forecastdata(weatherforecastmodel);
                        isloaded.set(true);
                        isload.set(true);
                        MakeChartData(weatherforecastmodel);

                    }

                    @Override
                    public void onError(Throwable e) {


                        if (isHttp404(e)) {
                            infomessage.set("Oops Something went Wrong");
                        } else {
                            infomessage.set("Oops Something Went Wrong");
                        }
                        infomessagevisibility.set(View.VISIBLE);
                        isloaded.set(true);
                    }

                    @Override
                    public void onNext(weatherForecastModel weatherForecastModel) {
                        MainViewModel.this.weatherforecastmodel = weatherForecastModel;
                        address.set(weatherForecastModel.getCity().getName());


                    }
                });
    }


    private void LoadData(Map<String, String> data) {
        WeatherService weatherservice = WeatherService.Factory.create();
        subscription = weatherservice.getWeather(this.data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isHttp404(e)) {
                            infomessage.set("Please Enter Valid City Name");
                        } else {
                            infomessage.set("Oops Something Went Wrong");
                        }
                        infomessagevisibility.set(View.VISIBLE);
                        isloaded.set(true);
                    }

                    @Override
                    public void onNext(WeatherModel weatherModel) {
                        setdata(weatherModel);

                    }
                });

    }

    public void changeUnit() {
        if (temp.get().equals(centi + "°C")) {

            faren = (int) (centi * (1.8) + 32);
            temp.set(faren + "°F");
            common = (centi * (1.8) + 32);
            farenmin = (int) (centimin * (1.8) + 32);
            mintemp.set(farenmin + "°F");
            commonmin = (centimin * (1.8) + 32);
            farenmax = (int) (centimax * (1.8) + 32);
            maxtemp.set(farenmax + "°F");
            commonmax = (centimax * (1.8) + 32);

        } else {
            centi = (int) ((common - 32) / 1.8);
            temp.set(centi + "°C");

            centimin = (int) ((commonmin - 32) / 1.8);
            mintemp.set(centimin + "°C");

            centimax = (int) ((commonmax - 32) / 1.8);
            maxtemp.set(centi + "°C");
        }
    }

    public void GoToGraphView() {
        Intent intent = new Intent(context, Graphview.class);
        intent.putExtra("weatherdata", weatherdataa);
        intent.putExtra("weatherdata2", precipitaionArray);
        intent.putExtra("DateData", dt);
        context.startActivity(intent);
    }

    private void setdata(WeatherModel weatherModel) {
        String icon = weatherModel.getWeather().get(0).getIcon();
        centi = weatherModel.getMain().getTemp();
        centimax = weatherModel.getMain().getTemp_max();
        centimin = weatherModel.getMain().getTemp_min();
        int resources = context.getResources().getIdentifier("drawable/icon_" + icon, null, context.getPackageName());
        imageicon.set(context.getResources().getDrawable(resources));
        temp.set(weatherModel.getMain().getTemp() + "°C");
        mintemp.set(weatherModel.getMain().getTemp_min() + "°C");
        maxtemp.set(weatherModel.getMain().getTemp_max() + "°C");
        description.set(weatherModel.getWeather().get(0).getDescription());
        pressure.set(weatherModel.getMain().getPressure() + " hpa");
        humidity.set(weatherModel.getMain().getHumidity() + " %");
        windspeed.set(weatherModel.getWind().getSpeed() + " m/s");
        winddegree.set(weatherModel.getWind().getDeg() + "°");
        Date date = new Date(weatherModel.getSys().getSunrise() * 1000L);
        String sunrisetime = String.format("%tr", date);
        Date date2 = new Date(weatherModel.getSys().getSunset() * 1000L);
        String sunsettime = String.format("%tr", date2);
        sunrise.set(sunrisetime);
        sunset.set(sunsettime);
    }

    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }

    @Override
    public void OnItemClick(List data) {
        String icon = data.getWeather().get(0).getIcon();
        centi = data.getMain().getTemp();
        centimax = data.getMain().getTemp_max();
        centimin = data.getMain().getTemp_min();
        int resources = context.getResources().getIdentifier("drawable/icon_" + icon, null, context.getPackageName());
        imageicon.set(context.getResources().getDrawable(resources));
        temp.set(data.getMain().getTemp() + "°C");
        mintemp.set(data.getMain().getTemp_min() + "°C");
        maxtemp.set(data.getMain().getTemp_max() + "°C");
        description.set(data.getWeather().get(0).getDescription());
        pressure.set(data.getMain().getPressure() + " hpa");
        humidity.set(data.getMain().getHumidity() + " %");
        windspeed.set(data.getWind().getSpeed() + " m/s");
        winddegree.set(data.getWind().getDeg() + "°");
    }


    public interface Weatherdata {
        void forecastdata(weatherForecastModel weatherforecastmodel);
    }


    private void MakeChartData(weatherForecastModel weatherforecastmodel) {
        if (weatherforecastmodel != null) {
            weatherdataa = new float[11];
            precipitaionArray = new float[11];
            Calendar cal = Calendar.getInstance();
            Date Systemtime = cal.getTime();
            String fv = String.format("%tr", Systemtime);

            dt = new String[11];
            for (int i = 0; i < 11; i++) {
                String s = String.valueOf(weatherforecastmodel.getList().get(i).getMain().getTemp());
                float f = Float.parseFloat(s);
                weatherdataa[i] = f;
                if (weatherforecastmodel.getList().get(i).getRain().getProbability() != null) {
                    precipitaionArray[i] = weatherforecastmodel.getList().get(i).getRain().getProbability();
                }
                if (weatherforecastmodel.getList().get(i).getRain().getProbability() == null) {
                    precipitaionArray[i] = 0.0f;
                }
                long date = weatherforecastmodel.getList().get(i).getDt();
                Date datee = new Date(date * 1000L);
                String Day = String.format("%tR", datee);
                Log.d("gbh", Day);
                dt[i] = Day;

            }
        }
    }

}
