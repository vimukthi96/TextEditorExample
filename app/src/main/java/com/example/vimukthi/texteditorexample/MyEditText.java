package com.example.vimukthi.texteditorexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    private Rect rect;
    private Paint paint;
    public MyEditText(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        paint.setTextSize(25);

        //paint.set();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        int baseline = getBaseline();
        for (int i = 0; i < getLineCount(); i++) {
            canvas.drawText(" " + (i+1)+" ", rect.left, baseline, paint);
            canvas.drawColor(0);
            baseline += getLineHeight();
        }
        super.onDraw(canvas);
    }

}
