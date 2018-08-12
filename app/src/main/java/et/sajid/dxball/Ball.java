package et.sajid.dxball;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class Ball {
    private float x,y, radious=20;
    boolean isAvailable;
    private float xStep = 5;
    private float yStep = -5;

    public void setBall(Canvas canvas, Bar bar) {
        float barMid = (bar.getRight()-bar.getLeft())/2;
        x = bar.getLeft()+barMid;
        y = bar.getTop()-radious;
        xStep=5;
        yStep=-5;
    }


    public boolean isballAvailable() {
        return isAvailable;
    }

    public void setIsBallAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


    public void drawBall(Canvas canvas, Paint paint){
        canvas.drawCircle(x,y,radious,paint);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setRadious(float radious) {
        this.radious = radious;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadious() {
        return radious;
    }
    public void nextPos(Canvas canvas, Bar bar, Paint paint) {
        if (x + radious >= canvas.getWidth() || (x + radious == bar.getLeft() && y >= bar.getTop() && y <= bar.getBottom()) && xStep > 0) {
            xStep = -xStep;
        }
        else if (y <= radious) { yStep = -yStep; }
        else if (x <= radious) { xStep = -xStep; }
        else if (y + radious > bar.getTop() && x > bar.getLeft() && x < bar.getRight()) { yStep = -yStep; }
        else if (y > bar.getTop() && y <= canvas.getHeight()) {
            xStep = 0;
            yStep = 0;
            isAvailable = false;
        }else if(x+radious == bar.getLeft()-20 && y>=bar.getTop()){
            xStep = - xStep;
        }else if(x+radious == bar.getRight() +20 && y >= bar.getTop()){
            xStep = - xStep;
        }

        x += xStep;
        y += yStep;
    }

    public float getxStep() {
        return xStep;
    }

    public float getyStep() {
        return yStep;
    }

    public void setxStep(float xStep) { this.xStep = xStep; }

    public void setyStep(float yStep) { this.yStep = yStep; }


    public void ballBrickCollision(ArrayList<Bricks> br, Ball ball){
        for(int i=0;i<br.size();i++) {
            if (((ball.getY() - ball.getRadious()) <= br.get(i).getBottom()) && ((ball.getY() + ball.getRadious()) >= br.get(i).getTop()) && ((ball.getX()) >= br.get(i).getLeft()) && ((ball.getX()) <= br.get(i).getRight())) {
                br.remove(i);
                DrawCanvas.score +=10;
                ball.setyStep(-(ball.getyStep()));
            }
            else if(((ball.getY()) <= br.get(i).getBottom()) && ((ball.getY()) >= br.get(i).getTop()) && ((ball.getX() + ball.getRadious()) >= br.get(i).getLeft()) && ((ball.getX() - ball.getRadious()) <= br.get(i).getRight())) {
                br.remove(i);
                DrawCanvas.score +=10;
                ball.setxStep(-(ball.getxStep()));
            }
        }
    }
}
