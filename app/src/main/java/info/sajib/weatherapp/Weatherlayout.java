package info.sajib.weatherapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sajib on 02-08-2016.
 */
public class Weatherlayout extends ViewGroup {
    public Weatherlayout(Context context) {
        super(context);
    }

    public Weatherlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Weatherlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Weatherlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.getDefaultSize(0, widthMeasureSpec);
        int height = View.getDefaultSize(0, heightMeasureSpec);
        for(int i=0;i<getChildCount();i++)
        {
            View child=getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            width=Math.max(width,child.getMeasuredWidth());
        }
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        View TodaysForecast_Title=getChildAt(0);
        int size=TodaysForecast_Title.getMeasuredWidth();
        int left=(getMeasuredWidth()/2)-(size/2);
        int top=0;
        int right=size+TodaysForecast_Title.getMeasuredWidth();
        int bottom=TodaysForecast_Title.getMeasuredHeight();
        TodaysForecast_Title.layout(left,top,left + TodaysForecast_Title.getMeasuredWidth(), top + TodaysForecast_Title.getMeasuredHeight());

        View Place_Name=getChildAt(1);
        size=Place_Name.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=bottom;
        right=left+Place_Name.getMeasuredWidth();
        bottom=top+Place_Name.getMeasuredHeight();
        Place_Name.layout(left,top,right,bottom);

        View FrameLayout=getChildAt(2);
        size=FrameLayout.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=bottom;
        right=left+FrameLayout.getMeasuredWidth();
        bottom=top+FrameLayout.getMeasuredHeight();
        FrameLayout.layout(left,top,right,bottom);

        View description=getChildAt(3);
        size=description.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=bottom;
        right=left+description.getMeasuredWidth();
        bottom=top+description.getMeasuredHeight();
        description.layout(left,top,right,bottom);

        View Max_Min_temp=getChildAt(4);
        size=Max_Min_temp.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=bottom;
        right=left+Max_Min_temp.getMeasuredWidth();
        bottom=top+Max_Min_temp.getMeasuredHeight();
        Max_Min_temp.layout(left,top,right,bottom);

        View Linechart=getChildAt(5);
        size=Linechart.getMeasuredWidth();
        left=right+8;
        right=left+Linechart.getMeasuredWidth();
        bottom=top+Linechart.getMeasuredHeight();
        Linechart.layout(left,top-10,right,bottom-10);

        View Wetherdetails=getChildAt(6);
        size=Wetherdetails.getMeasuredWidth();
        left=0;
        top=bottom;
        right=left+Wetherdetails.getMeasuredWidth();
        bottom=top+Wetherdetails.getMeasuredHeight();
        Wetherdetails.layout(left,top,right,bottom);

        View Forecast=getChildAt(9);
        size=Forecast.getMeasuredWidth();
        left=0;
        top=bottom;
        right=left+Forecast.getMeasuredWidth();
        bottom=top+Forecast.getMeasuredHeight();
        Forecast.layout(left,top,right,bottom);

        View ProgressBar=getChildAt(7);
        size=ProgressBar.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=(getMeasuredHeight()/2)-(ProgressBar.getMeasuredHeight()/2);
        right=left+ProgressBar.getMeasuredWidth();
        bottom=top+ProgressBar.getMeasuredHeight();
        ProgressBar.layout(left,top,right,bottom);

        View ErrorText=getChildAt(8);
        size=ErrorText.getMeasuredWidth();
        left=(getMeasuredWidth()/2)-(size/2);
        top=(getMeasuredHeight()/2)-(ErrorText.getMeasuredHeight()/2);
        right=left+ErrorText.getMeasuredWidth();
        bottom=top+ErrorText.getMeasuredHeight();
        ErrorText.layout(left,top,right,bottom);



    }
}
