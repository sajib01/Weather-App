package info.sajib.weatherapp.view;

import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import info.sajib.weatherapp.R;
import info.sajib.weatherapp.RecyclerviewAdapter;
import info.sajib.weatherapp.databinding.ActivityWeatherShowBinding;
import info.sajib.weatherapp.model.weatherForecastModel;
import info.sajib.weatherapp.viewmodel.MainViewModel;

public class WeatherShow extends AppCompatActivity implements MainViewModel.Weatherdata {
    Double Longitude;
    Double Latitude;
    String finalAddress;
    RecyclerviewAdapter recyclerviewAdapter;
    RecyclerView recyclerView;
    ActivityWeatherShowBinding activityWeatherShowBinding;
    weatherForecastModel model;
    String search;
    float weatherdata[];
    float precipitaionArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWeatherShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather_show);
        Longitude = getIntent().getDoubleExtra("Longitude", 0);
        Latitude = getIntent().getDoubleExtra("Latitude", 0);
        search = getIntent().getStringExtra("q");

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        if (Longitude != 0 && Latitude != 0) {
            StringBuilder builder = new StringBuilder();
            try {
                List<Address> address = geocoder.getFromLocation(Latitude, Longitude, 1);
                int maxLines = address.get(0).getMaxAddressLineIndex();
                for (int i = 0; i < maxLines; i++) {
                    String addressStr = address.get(0).getAddressLine(i);
                    builder.append(addressStr);
                    builder.append(" ");
                }

                finalAddress = builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MainViewModel mainViewModel = new MainViewModel(this, finalAddress, Longitude, Latitude, this, search, recyclerviewAdapter);
        recyclerviewAdapter = new RecyclerviewAdapter(WeatherShow.this, mainViewModel);
        activityWeatherShowBinding.setViewmodel(mainViewModel);
        recyclerView = activityWeatherShowBinding.recycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(recyclerviewAdapter);
    }

    @Override
    public void forecastdata(weatherForecastModel weatherforecastmodel) {
        model = weatherforecastmodel;
        recyclerviewAdapter.setData(weatherforecastmodel);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerviewAdapter.notifyDataSetChanged();

    }

}
