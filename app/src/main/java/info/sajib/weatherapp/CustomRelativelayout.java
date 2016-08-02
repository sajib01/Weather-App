package info.sajib.weatherapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by sajib on 26-07-2016.
 */
public class CustomRelativelayout extends ViewGroup {
    public CustomRelativelayout(Context context) {
        super(context);
    }

    public CustomRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width= View.getDefaultSize(0,widthMeasureSpec);
        int height=View.getDefaultSize(0,heightMeasureSpec);
        int widthConstraints=getPaddingLeft()+getPaddingRight();
        int heightConstarints=getPaddingTop()+getPaddingBottom();

        View Button=getChildAt(0);
        measureChildWithMargins(Button,widthMeasureSpec,widthConstraints,heightMeasureSpec,heightConstarints);





    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }



}
