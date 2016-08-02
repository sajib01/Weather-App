package info.sajib.weatherapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import info.sajib.weatherapp.model.List;
import info.sajib.weatherapp.model.weatherForecastModel;
/**
 * Created by sajib on 13-07-2016.
 */
public class Recyclerviewmodel extends BaseObservable {
    Context context;
    List list;
    int position;
    public Recyclerviewmodel(Context context,List list)
    {
        this.list=list;
        this.context=context;
    }

    public Drawable getWeatherpic()
    {
        String icon=list.getWeather().get(0).getIcon();
        int resources = context.getResources().getIdentifier("drawable/icon_" + icon, null, context.getPackageName());
        return context.getResources().getDrawable(resources);
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public String getDate()
    {
        long date=list.getDt();
        Date datee = new Date(date * 1000L);
        String Day = String.format("%tA", datee);
        String Dayinnumber=String.format("%tm", datee);
        String dat=String.format("%tm", datee);
        String year=String.format("%tY", datee);
        Calendar calendar=Calendar.getInstance();
        Date date2=calendar.getTime();
        String Currentday = String.format("%tA", date2);
        String Currentdate=String.format("%tm", date2);
        String Currentyear=String.format("%tY", date2);

        Calendar calendar1=Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_YEAR,+1);
        Date date1=calendar1.getTime();
        String Tomorrow=String.format("%tA", date1);
        if(Day.equals(Currentday)&&dat.equals(Currentdate)&&year.equals(Currentyear))
        {
            return "Today";
        }
        if(Day.equals(Tomorrow))
        {
            return "Tommorrow";
        }
        return Day;
    }
    public String getWeathercondition()
    {
        return list.getWeather().get(position).getDescription();
    }

    public String getTemperature()
    {
        return String.valueOf(list.getMain().getTemp()+"°C");
    }
    public void setData(List list) {
        this.list = list;
        notifyChange();
    }

    public String getTime()
    {
        long date=list.getDt();
        Date datee = new Date(date * 1000L);
        String Day = String.format("%tr", datee);
        Log.d("ffd", String.valueOf(datee));
        return Day;
    }
}
