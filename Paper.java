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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Paper  extends View {
    Context context;
    private Bitmap mBitmap;

    private Canvas canvas;
    private Paint paint;
    public Path path;
    static int RED,GREEN,BLUE;
    private float mX,mY;
    private static final float TOLERANCE=5;


    Map<Path,Paint> list ;

    public  Paper (Context context,AttributeSet attrs){
        super(context,attrs);
        this.context=context;
        list = new HashMap<Path,Paint>();
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
        for(Path i:list.keySet()){

            canvas.drawPath(i,list.get(i));

        }



    }

    private void renew(){
        paint=new Paint();
        path=new Path();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paint.setColor(Color.argb(255,RED,GREEN,BLUE));

        list.put(path,paint);
    }



    private void startTouch(float x,float y){
        renew();


        path.moveTo(x,y);

        mX=x;
        mY=y;


    }
    private void moveTouch(float x,float y){
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
        list.clear();

        invalidate();


    }


    public void clear(){

        Iterator<Path> it = list.keySet().iterator();

        // Iterate over all the elements
        if (it.hasNext()) {
           it.next();
            // Check if Value associated with Key is ODD

                // Remove the element
                it.remove();

        }
        invalidate();






      //

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
