package com.arnoldsson.anton.gmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Anton on 2016-10-27.
 */

public class MapImageView extends ImageView {

    public MapImageView(Context context) {
        super(context);
    }

    public MapImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        Point p3 = Adjust(13.0, 55.612, 13.0, 55.612, 12);
//        Point p2 = new Point(0, 0);
//        if(canvas.getHeight() != 0) {
//            double newptY = (double)p3.y * ((double)canvas.getWidth() / (double)canvas.getHeight());
//                    p2 = new Point(p3.x, (int)newptY);
//        }
        int cw = canvas.getHeight();
        int ch = canvas.getWidth();

        Log.println(Log.DEBUG, "cw", Integer.toString(cw));
        Log.println(Log.DEBUG, "ch", Integer.toString(ch));

        double cwRatio = (double)cw / 640D;
        double chRatio = (double)ch / 640D;

        Point p2 = new Point((int)(p3.x*cwRatio), (int)(p3.y*chRatio));

        Log.println(Log.DEBUG, "width", Integer.toString(canvas.getWidth()));
        Log.println(Log.DEBUG, "height", Integer.toString(canvas.getHeight()));
        Log.println(Log.DEBUG, "date p2", p2.toString());
        canvas.drawRect(p2.x + (cw /2), p2.y + (ch /2), p2.x+10 + (cw /2), p2.y+10 + (ch /2), p);

    }


    //(half of the earth circumference's in pixels at zoom level 21)
    static double offset = 268435456;
    static double radius = offset / Math.PI;
    // X,Y ... location in degrees
// xcenter,ycenter ... center of the map in degrees (same value as in
// the google static maps URL)
// zoomlevel (same value as in the google static maps URL)
// xr, yr and the returned Point ... position of X,Y in pixels relativ
// to the center of the bitmap
    static Point Adjust(double X, double Y, double xcenter, double ycenter,
                        int zoomlevel)
    {
        int xr = (LToX(X) - LToX(xcenter)) >> (21 - zoomlevel);
        int yr = (LToY(Y) - LToY(ycenter)) >> (21 - zoomlevel);
        Point p = new Point(xr, yr);
        return p;
    }

    static int LToX(double x)
    {
        return (int)(Math.round(offset + radius * x * Math.PI / 180));
    }

    static int LToY(double y)
    {
        return (int)(Math.round(offset - radius * Math.log((1 +
                Math.sin(y * Math.PI / 180)) / (1 - Math.sin(y *
                Math.PI / 180))) / 2));
    }

}
