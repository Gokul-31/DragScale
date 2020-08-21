package com.example.dragandscale.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.dragandscale.Points;
import com.example.dragandscale.R;

public class RectView extends View {

    public RectView(Context context) {
        super(context);
        init(null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }
    public void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.colorAccent));

        Points.setA(getX(),getY());
        Points.setB(getX()+getWidth(),getY());
        Points.setC(getX()+getWidth(),getY()+getHeight());
        Points.setD(getX(),getY()+getHeight());



        invalidate();
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}
