package et.sajid.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bar {

    float top, bottom, left, right,width;


    public void setBar(Canvas canvas) {
        left = canvas.getWidth()-(2*canvas.getWidth()/3);
        right = left+(canvas.getWidth()/3);
        bottom = canvas.getHeight();
        top = bottom - 40;
    }

    public void drawBar(Canvas canvas, Paint paint){
        /*if(left>=0) {
            canvas.drawRect(left, top, right, bottom, paint);
        }else if(right<=canvas.getWidth()){
            canvas.drawRect(left, top, right, bottom, paint);
        }else if (left<0){
            canvas.drawRect(0, top, canvas.getWidth()-canvas.getWidth()/3, bottom, paint);
        }else if(right>canvas.getWidth()){
            canvas.drawRect(canvas.getWidth()-(2*(canvas.getWidth()/3)), top, canvas.getWidth(), bottom, paint);
        }*/
        right = left+(canvas.getWidth()/3);
        canvas.drawRect(left, top, right, bottom, paint);
        width=canvas.getWidth();

    }



    public void setLeft(float cLeft) {
        if(cLeft<0){
            this.left=0;
        }else {
            this.left = cLeft;
        }
    }

    public void setTop(float top) {
        this.top = top;
    }

    public void setRight(float cRight) {
        if(cRight>=width){
            this.right=width-30;
        }else {
            this.right = cRight;
        }
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getBottom() {
        return bottom;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }
}
