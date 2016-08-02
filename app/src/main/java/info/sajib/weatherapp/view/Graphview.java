package info.sajib.weatherapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import info.sajib.weatherapp.LineChart;
import info.sajib.weatherapp.R;
import info.sajib.weatherapp.databinding.GraphviewBinding;
import info.sajib.weatherapp.model.weatherForecastModel;

/**
 * Created by sajib on 23-07-2016.
 */
public class Graphview extends AppCompatActivity {
    weatherForecastModel model;
    List<info.sajib.weatherapp.model.List> data = Collections.emptyList();

    float graphdata[];
    float precipitaion[];
    String dt[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GraphviewBinding binding= DataBindingUtil.setContentView(this,R.layout.graphview);
        graphdata=getIntent().getFloatArrayExtra("weatherdata");
        precipitaion=getIntent().getFloatArrayExtra("weatherdata2");
        dt=getIntent().getStringArrayExtra("DateData");

        LineChart chart= binding.linechart;

        chart.setChartData(graphdata);
        chart.setPrecipitationdata(precipitaion);
        chart.setDateData(dt);

    }

}
