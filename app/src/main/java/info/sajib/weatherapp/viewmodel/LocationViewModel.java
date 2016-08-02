package info.sajib.weatherapp.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import info.sajib.weatherapp.databinding.ActivityMainBinding;
import info.sajib.weatherapp.view.WeatherShow;

/**
 * Created by sajib on 05-07-2016.
 */
public class LocationViewModel implements LocationListener {
    Context context;
    LocationManager locationManager;
    String provider;
    Double Longitude;
    Double Latitude;
    Location location;
    ActivityMainBinding mainBinding;
    boolean isNetworkAvailable;
    boolean isGpsAvailable;
    boolean gps_enabled;
    boolean network_enabled;
    public final ObservableField<Boolean> isSearched = new ObservableField<>(false);
    public final ObservableField<Boolean> isload=new ObservableField<>(false);
    String search;

    public LocationViewModel(Context context, ActivityMainBinding mainBinding) {
        this.context = context;
        this.mainBinding = mainBinding;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        getWeather();

    }

    private void getWeather() {

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (provider != null && !provider.equals("")) {

            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 0, 0, LocationViewModel.this);

            if (location != null) {
                onLocationChanged(location);
            } else {

                Snackbar.make(mainBinding.relativelayout, "Location not found", Snackbar.LENGTH_SHORT).show();
            }

        } else {
            Snackbar.make(mainBinding.relativelayout, "No Provider Found", Snackbar.LENGTH_SHORT).show();
        }

        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("GPS is not enabled.Do you want enable Gps?");
            dialog.setPositiveButton("Setting", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                }
            });
            dialog.show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void ShowMeWeather() {

        Intent intent = new Intent(context, WeatherShow.class);
        intent.putExtra("q", search);
        context.startActivity(intent);
        mainBinding.locationSearch.setText("");

    }

    public void GoToNextActivity() {
        getWeather();
        if (location != null) {
            Longitude = location.getLongitude();
            Latitude = location.getLatitude();
            Intent intent = new Intent(context, WeatherShow.class);
            intent.putExtra("Longitude", Longitude);
            intent.putExtra("Latitude", Latitude);

            context.startActivity(intent);
        }


    }

    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            search = s.toString();
            isSearched.set(s.length() > 0 ? true : false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}


