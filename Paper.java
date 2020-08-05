package com.example.drawingpen;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.MotionEvent;

import android.view.View;
import java.util.ArrayList;


public class Paper  extends View {
    Context context;
    private Bitmap mBitmap;

    private Canvas canvas;
    private Paint paint;
    public Path path;
    static int RED,GREEN,BLUE;
    private float mX,mY;
    private static final float TOLERANCE=5;
    private boolean justTouch=true;



    ArrayList<Path> pathlist ;
    ArrayList<Paint> paintlist ;

    public  Paper (Context context,AttributeSet attrs){
        super(context,attrs);
        this.context=context;
        pathlist = new ArrayList<Path>();
        paintlist = new ArrayList<Paint>();
        paint=new Paint();
        path=new Path();




    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(mBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       for(int i=0;i<paintlist.size();i++){
           canvas.drawPath(pathlist.get(i),paintlist.get(i));


       }


    }

    private void renew(){
        paint=new Paint();
        path=new Path();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(7f);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setColor(Color.argb(255,RED,GREEN,BLUE));


        pathlist.add(path);
        paintlist.add(paint);
    }


    private void startTouch(float x, float y){

        renew();


        path.moveTo(x,y);

        mX=x;
        mY=y;
        path.setLastPoint(x, y);
        x+=4;
        path.lineTo(x, y);



    }
    private void moveTouch(float x,float y){
        justTouch=false;
        float dx=Math.abs(x-mX);
        float dy=Math.abs(y-mY);
        if(dx>=TOLERANCE || dy>=TOLERANCE){
            path.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX=x;
            mY=y;
        }

    }
    private  void upTouch(){
        path.lineTo(mX,mY);



    }
    public void clearAll() {
        pathlist.clear();
        paintlist.clear();

        invalidate();


    }


    public void clear(){

        if(!paintlist.isEmpty()){
            paintlist.remove(paintlist.size()-1);
            pathlist.remove(pathlist.size()-1);
        }
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
