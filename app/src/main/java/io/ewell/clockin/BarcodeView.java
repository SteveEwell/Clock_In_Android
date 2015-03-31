package io.ewell.clockin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class BarcodeView extends View {

    private Paint mLinePaint = new Paint();
    private int mCanvasWidth;
    private int mCanvasHeight;



    public BarcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.init();
    }

    private void init() {

        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(1f);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvasWidth = canvas.getWidth();
        this.mCanvasHeight = canvas.getHeight();

        canvas.drawColor(Color.WHITE);
        canvas.drawLine(10,10,10,(mCanvasHeight - 10),mLinePaint);

    }


}
