package com.example.drawingpen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Paper extends SurfaceView  {
    Context context;
    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    public Path path;
    static int RED,GREEN,BLUE;
    private float mX,mY;
    private static final float TOLERANCE=5;



    public  Paper (Context context,AttributeSet attrs){
        super(context,attrs);



        // Initialize ourHolder and paint objects
        holder = getHolder();



        paint=new Paint();
        path=new Path();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);


    }

        public void Draw() {


        if(holder.getSurface().isValid()){


            canvas=holder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.argb(255,RED,GREEN,BLUE));


            canvas.drawPath(path,paint);

            holder.unlockCanvasAndPost(canvas);
        }

    }

    private void startTouch(float x,float y){
        path.moveTo(x,y);
        mX=x;
        mY=y;
        Draw();
    }
    private void moveTouch(float x,float y){
        float dx=Math.abs(x-mX);
        float dy=Math.abs(y-mY);
        if(dx>=TOLERANCE || dy>=TOLERANCE){
            path.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX=x;
            mY=y;
        }
        Draw();
    }
    private  void upTouch(){
        path.lineTo(mX,mY);
        Draw();
    }
    public void clearCanvas(){


        path.reset();
        Draw();
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent e){


        float x=e.getX();
        float y=e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                invalidate();
                break;


            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;

        }


        return true;
    }


}
