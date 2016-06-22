package com.example.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Song on 2016/6/21.
 */
public class MyView extends View{

    private Bitmap mWith;
    private Bitmap mBlack;

    private Paint mPaint;

    private int mPanelWidth;
    private float mLineHeight;
    private int MAXLINE = 10;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }

        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAXLINE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = mPanelWidth;
        float lineHeight = mLineHeight;

        int startX = (int) (0.5 * lineHeight);
        int endX = (int) (width - 0.5 * lineHeight);


        for (int i = 0; i < MAXLINE; i++) {
            canvas.drawLine(startX, (float) ((0.5 + i) * lineHeight),
                    endX, (float) ((0.5 + i) * lineHeight), mPaint);

            canvas.drawLine((float) ((0.5 + i) * lineHeight), startX,
                    (float) ((0.5 + i) * lineHeight), endX, mPaint);
        }

    }
}
