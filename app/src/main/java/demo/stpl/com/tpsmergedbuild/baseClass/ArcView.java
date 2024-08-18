package demo.stpl.com.tpsmergedbuild.baseClass;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stpl on 9/13/2016.
 */
public class ArcView extends View {

    Paint paint;
    Path path;

    public ArcView(Context context) {
        super(context);
        init();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ArcView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w=canvas.getWidth();
        int h=canvas.getHeight();
        int w_2= (w / 2);
        int h_2= (h / 2);
        PointF mPoint1 = new PointF(0, 0); //starts at canvas left top
        PointF mPoint2 = new PointF(w_2, h_2);//mid of the canvas
        Path drawPath1 =drawCurve(mPoint1, mPoint2);
        canvas.drawPath(drawPath1, paint);
    }

    private Path drawCurve(PointF mPointa, PointF mPointb) {
        Path myPath = new Path();
        myPath.moveTo(mPointa.x, mPointa.y);
        final float x2 = (mPointb.x + mPointa.x) / 3;
        final float y2 = (mPointb.y + mPointa.y) / 3;
        myPath.quadTo(x2, y2, mPointb.x, mPointb.y);
        return myPath;
    }
}
