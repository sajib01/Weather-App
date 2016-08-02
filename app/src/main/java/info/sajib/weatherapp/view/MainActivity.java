package info.sajib.weatherapp.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import info.sajib.weatherapp.R;
import info.sajib.weatherapp.databinding.ActivityMainBinding;
import info.sajib.weatherapp.viewmodel.LocationViewModel;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setLvm(new LocationViewModel(this,mainBinding));
        mainBinding.locationSearch.setText("");
    }
}
