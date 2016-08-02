package info.sajib.weatherapp.model;

import java.util.Map;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by sajib on 10-07-2016.
 */
public interface WeatherService {
    @GET("weather")
    Observable<WeatherModel> getWeather(@QueryMap Map<String,String> query);
    @GET("forecast")
    Observable<weatherForecastModel> getForecast(@QueryMap Map<String,String> query);

    class Factory {
        public static WeatherService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(WeatherService.class);
        }
    }
}
