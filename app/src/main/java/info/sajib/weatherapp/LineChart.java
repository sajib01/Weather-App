package info.sajib.weatherapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LineChart extends View {

    private static final int MIN_LINES = 3;
    private static final int MAX_LINES = 8;
    private static final int[] DISTANCES = {1, 2, 5};
    private static final float GRAPH_SMOOTHNES = 0.15f;

    private float[] datapoints;
    private Paint paint = new Paint();

    private float Xaxis[];
    private float Yaxis[];

    int floatarraysize = 0;
    Context context;

    Canvas canvas;

    float temp;

    float precipitationData[];

    String Dt[];

    Map<Float, Float> data = new HashMap<>();

    Map<Float, Float> preciData = new HashMap<>();

    float temp2;

    Path textpath = new Path();

    boolean canDraw = false;

    float RectXaxis;
    float RectYaxis;


    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setPrecipitationdata(float[] Datapoint) {
        precipitationData = Datapoint;
    }

    public void setChartData(float[] newDatapoints) {
        datapoints = newDatapoints;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Paint Dpaint = new Paint();
        Dpaint.setStrokeWidth(1);
        Dpaint.setColor(0xFFFFFF00);
        Dpaint.setTextAlign(Align.LEFT);
        Dpaint.setStyle(Style.FILL);
        Dpaint.setTextSize(20);
        Dpaint.setAntiAlias(true);

        Paint Mpaint = new Paint();
        Mpaint.setStrokeWidth(3);
        Mpaint.setColor(Color.RED);
        Mpaint.setStyle(Style.STROKE);
        Mpaint.setAntiAlias(true);

        Paint RPaint = new Paint();
        RPaint.setStrokeWidth(4);
        RPaint.setColor(Color.RED);
        RPaint.setStyle(Style.FILL);
        RPaint.setAntiAlias(true);

        Path Npath = new Path();


        float maxValue = GetMax(datapoints);
        this.canvas = canvas;

        drawBackground(canvas, maxValue);
        drawLineChart(canvas, maxValue);
        Npath.moveTo(RectXaxis + 4, RectYaxis + 4);

        if (canDraw) {
            Npath.lineTo(RectXaxis + 20, RectYaxis + 20);
            canvas.drawCircle(RectXaxis, RectYaxis, 8, Mpaint);
            canvas.drawPath(Npath, Mpaint);

            canvas.drawRoundRect(RectXaxis + 17, RectYaxis + 17, RectXaxis + 103, RectYaxis + 83, 8, 8, RPaint);

            canvas.drawText(String.valueOf(data.get(temp) + "°C"), RectXaxis + 25, RectYaxis + 50, Dpaint);
            Dpaint.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(preciData.get(temp2)), RectXaxis + 25, RectYaxis + 70, Dpaint);

            canDraw = false;
        }


        Dpaint.setColor(Color.YELLOW);
        Dpaint.setStyle(Style.FILL);
        Dpaint.setAntiAlias(true);
        canvas.drawRect(getWidth() / 2, getHeight() / 2, (20 + getWidth() / 2), (20 + getHeight() / 2), Dpaint);
        Dpaint.setColor(Color.BLACK);
        Dpaint.setStyle(Style.FILL);
        Dpaint.setStrokeWidth(1);
        Dpaint.setAntiAlias(true);
        Dpaint.setTextSize(20);
        canvas.drawText("Temperature", (30 + getWidth() / 2), 20 + getHeight() / 2, Dpaint);

        Dpaint.setColor(Color.BLUE);
        Dpaint.setStyle(Style.FILL);
        Dpaint.setAntiAlias(true);
        canvas.drawRect(getWidth() / 2, 30 + getHeight() / 2, (20 + getWidth() / 2), (50 + getHeight() / 2), Dpaint);
        Dpaint.setColor(Color.BLACK);
        Dpaint.setStyle(Style.FILL);
        Dpaint.setStrokeWidth(1);
        Dpaint.setAntiAlias(true);
        Dpaint.setTextSize(20);
        canvas.drawText("Precipitation", (30 + getWidth() / 2), 50 + getHeight() / 2, Dpaint);

        Log.d("bbh", String.valueOf(canvas));
    }

    private void drawBackground(Canvas canvas, float maxValue) {
        int range = getLineDistance(maxValue);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Align.LEFT);
        paint.setTextSize(16);
        paint.setStrokeWidth(1);
        for (int y = 0; y < maxValue; y += range) {
            final int yPos = (int) getYPos(y, maxValue);

            // turn off anti alias for lines, they get crisper then
            paint.setAntiAlias(false);
            canvas.drawLine(0, yPos, getWidth(), yPos, paint);


            // turn on anti alias again for the text
            paint.setAntiAlias(true);
            canvas.drawText(y+ "°C", 0+4, yPos - 2, paint);
        }

        for (int i = 0; i < 11; i++) {
            int XPos = (int) getXPos(i);
            // canvas.drawLine(XPos,0,XPos,getHeight(),paint);
            if(Dt.length!=0) {
                canvas.drawText(String.valueOf(Dt[i]), XPos-10, getHeight() - (getPaddingBottom()-20), paint);
            }

        }


    }

    private int getLineDistance(float maxValue) {
        long distance;
        int distanceIndex = 0;
        long distanceMultiplier = 1;
        int numberOfLines = MIN_LINES;

        do {
            distance = DISTANCES[distanceIndex] * distanceMultiplier;

            numberOfLines = (int) Math.ceil(maxValue / distance);

            distanceIndex++;
            if (distanceIndex == DISTANCES.length) {
                distanceIndex = 0;
                distanceMultiplier *= 10;
            }

        } while (numberOfLines < MIN_LINES || numberOfLines > MAX_LINES);

        return (int) distance;
    }

    private void drawLineChart(Canvas canvas, float maxValue) {
        Path path = createSmoothPath(maxValue);

        Xaxis = new float[datapoints.length];
        Yaxis = new float[datapoints.length];

        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(0xFF33B5E5);
        paint.setAntiAlias(true);
        paint.setShadowLayer(4, 2, 2, 0x81000000);
        canvas.drawPath(path, paint);

        Paint nPaint = new Paint();
        nPaint.setStyle(Style.FILL);
        nPaint.setColor(Color.GRAY);
        nPaint.setTextAlign(Align.LEFT);
        nPaint.setTextSize(17);
        nPaint.setStrokeWidth(4);

        Paint Circle = new Paint();
        Circle.setStyle(Style.FILL);
        Circle.setColor(Color.YELLOW);
        for (int i = 0; i < datapoints.length - 1; i++) {

            float thisPointX = getXPos(i);
            float thisPointY = getYPos(datapoints[i], maxValue);

            float preciY = getYPos(precipitationData[i], GetMax(precipitationData));

            Xaxis[i] = getXPos(i);
            Yaxis[i] = getYPos(datapoints[i], maxValue);

            data.put(getYPos(datapoints[i], maxValue), datapoints[i]);

            preciData.put(getYPos(datapoints[i], maxValue), precipitationData[i]);

            //canvas.drawText(String.valueOf(datapoints[i].getPosition() + "°C"), thisPointX + 20, thisPointY + 20, nPaint);

            nPaint.setColor(Color.BLUE);
            nPaint.setAlpha(70);
            canvas.drawRect(thisPointX - 10, preciY, thisPointX + 10, getHeight() - getPaddingBottom(), nPaint);

            canvas.drawCircle(thisPointX, thisPointY, 6, Circle);

        }
        paint.setShadowLayer(0, 0, 0, 0);
    }

    private Path createSmoothPath(float maxValue) {

        Path path = new Path();
        path.moveTo(getXPos(0), getYPos(datapoints[0], maxValue));
        for (int i = 0; i < datapoints.length - 1; i++) {

            float thisPointX = getXPos(i);
            float thisPointY = getYPos(datapoints[i], maxValue);
            float nextPointX = getXPos(i + 1);
            float nextPointY = getYPos(datapoints[si(i + 1)], maxValue);

            float startdiffX = (nextPointX - getXPos(si(i - 1)));
            float startdiffY = (nextPointY - getYPos(datapoints[si(i - 1)], maxValue));
            float endDiffX = (getXPos(si(i + 2)) - thisPointX);
            float endDiffY = (getYPos(datapoints[si(i + 2)], maxValue) - thisPointY);

            float firstControlX = thisPointX + (GRAPH_SMOOTHNES * startdiffX);
            float firstControlY = thisPointY + (GRAPH_SMOOTHNES * startdiffY);
            float secondControlX = nextPointX - (GRAPH_SMOOTHNES * endDiffX);
            float secondControlY = nextPointY - (GRAPH_SMOOTHNES * endDiffY);

            path.cubicTo(firstControlX, firstControlY, secondControlX, secondControlY, nextPointX,
                    nextPointY);

        }
        return path;
    }

    /**
     * Given an index in datapoints, it will make sure the the returned index is
     * within the array
     *
     * @param i
     * @return
     */
    private int si(int i) {
        if (i > datapoints.length - 1) {
            return datapoints.length - 1;
        } else if (i < 0) {
            return 0;
        }
        return i;
    }


    private float GetMax(float[] array) {
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private float getYPos(float value, float maxValue) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();

        // scale it to the view size
        value = (value / maxValue) * height;

        // invert it so that higher values have lower y
        value = height - value;

        // offset it to adjust for padding
        value += getPaddingTop();

        return value;
    }

    private float getXPos(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float maxValue = datapoints.length - 1;

        // scale it to the view size
        value = (value / maxValue) * width;

        // offset it to adjust for padding
        value += getPaddingLeft();
        return value;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            textpath.moveTo(event.getX(), event.getY());
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isAreaInXaxis(event.getX(), event.getY())) {

                canDraw = true;
            }


        }
        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }

    public boolean isAreaInXaxis(float x, float y) {
        for (int i = 0; i < 11; i++) {
            float X = Xaxis[i];
            float Y = Yaxis[i];
            if (X >= (x - 20) && X <= (x + 20) && Y >= (y - 10) && Y <= (y + 10)) {
                temp = Y;
                temp2 = Y;
                RectXaxis = X;
                RectYaxis = Y;
                return true;
            }
        }
        return false;
    }


    public void setDateData(String[] dt) {
        Dt=dt;
    }
}