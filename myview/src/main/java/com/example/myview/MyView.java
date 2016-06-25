package com.example.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Song on 2016/6/21.
 */
public class MyView extends View {

    private Bitmap mWhite;
    private Bitmap mBlack;

    private Paint mPaint;

    private int mPanelWidth;
    private float mLineHeight;
    private int MAXLINE = 10;

    private Point mPoint;
    private List<Point> mWhiteArray = new ArrayList<>();
    private List<Point> mBlackArray = new ArrayList<>();
    //默认白棋先
    private boolean mIsWhite = true;
    //棋子比例
    private float mScale = 3 * 1.0f / 4;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mWhite = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlack = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
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

        int width = (int) (mLineHeight * mScale);
        mWhite = Bitmap.createScaledBitmap(mWhite, width, width, false);
        mBlack = Bitmap.createScaledBitmap(mBlack, width, width, false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = getPoint(x, y);
            if (mWhiteArray.contains(p) || mBlackArray.contains(p))
                return false;
            if (mIsWhite) {
                mWhiteArray.add(p);
            } else {
                mBlackArray.add(p);
            }
            invalidate();
            mIsWhite = !mIsWhite;
        }
        return true;
    }

    private Point getPoint(int x, int y) {
        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制棋盘
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

        //绘制棋子
        for (int i = 0, n = mWhiteArray.size(); i < n; i++) {
            Point p = mWhiteArray.get(i);
            canvas.drawBitmap(mWhite, p.x * mLineHeight,
                    p.y * mLineHeight, null);
        }

        for (int i = 0, n = mBlackArray.size(); i < n; i++) {
            Point p = mBlackArray.get(i);
            canvas.drawBitmap(mBlack, p.x * mLineHeight,
                    p.y * mLineHeight, null);
        }

    }
}
