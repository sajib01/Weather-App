package info.sajib.weatherapp.binding;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.sajib.weatherapp.MyApplication;

/**
 * Created by sajib on 05-07-2016.
 */
public class BindingAdapters {

    @BindingAdapter("app:Click")
    public static void onClick(View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
            }
        });
    }
    @BindingAdapter("app:src")
    public static void getPic(ImageView imageView, Drawable drawable)
    {
        imageView.setImageDrawable(drawable);
    }
    @BindingAdapter("app:text")
    public static void getText(TextView textView,String text)
    {
        Typeface typeface= Typeface.createFromAsset(textView.getContext().getAssets(),"fonts/weathericons-regular-webfont.ttf");
        textView.setTypeface(typeface);
        textView.setText(text);

    }

}

