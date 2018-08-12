package et.sajid.dxball;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class DrawCanvas extends View {
    int count = 0;
    int level;
    public static int life = 2;
    public static boolean gameOver;
    float brickX =0, brickY =0;
    static int score =0;
    Canvas canvas;
    boolean createGame;
    Paint paint;
    Bar bar;
    Ball ball;
    float touchPoint;
    boolean gameStart = true;
    ArrayList<Bricks> bricks =new ArrayList<Bricks>();
    Stage stage=new Stage();
    public float barLength;
    int action;
    //public boolean isInGame;

    public DrawCanvas(Context context) {
        super(context);
        paint =new Paint();
        level = 1;
        createGame = true;
        gameOver = false;
        bar = new Bar();
        ball = new Ball();





        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                action = MotionEventCompat.getActionMasked(event);
                if(action==MotionEvent.ACTION_MOVE) {


                    if (ball.isballAvailable()) {
                        touchPoint = event.getX();
                        if((touchPoint-barLength/2)==0){
                            bar.setLeft(0);
                        }else {
                            bar.setLeft(touchPoint - (barLength / 2));
                        }

                        if((touchPoint+barLength/2)>=canvas.getWidth()){
                            bar.setRight(canvas.getWidth());
                        }else {
                            bar.setRight(touchPoint + (barLength / 2));
                        }
                       // if(isInGame) {
                      //      ball.setX(touchPoint);
                      //      isInGame=false;
                     //   }
                        //ball.setIsBallAvailable(false);

                    }

                }

                if(action == MotionEvent.ACTION_UP){
                    if(count <2){
                        count +=1;
                    }
                }
                return true;
            }
        });
    }



    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (gameStart) {
            gameStart = false;

            bar.setBar(canvas);
            ball.setBall(canvas, bar);
            barLength = bar.getRight() - bar.getLeft();
        }
        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        ball.drawBall(canvas, paint);
        paint.setColor(Color.BLACK);
        bar.drawBar(canvas, paint);

        if(count == 2){
            ball.nextPos(canvas, bar, paint);
        }
        if(count <= 1){
            paint.setColor(Color.RED);
            paint.setTextSize(30);
            paint.setFakeBoldText(true);
            canvas.drawText("Double Tap to Start",canvas.getWidth()/2-canvas.getWidth()/4,canvas.getHeight()/2+canvas.getHeight()/40, paint);
            //isInGame=true;
        }

        this.canvas = canvas;

        brickX = canvas.getWidth() / 7;
        brickY = (canvas.getHeight() / 15) ;

        if (createGame) {
            createGame = false;
            if (level == 1) {

                stage.stage_level_one(canvas, brickX, brickY, bricks);
            }
            else if (level == 2) {
                stage.stage_level_two(canvas, brickX, brickY, bricks);
            }
            else
                gameOver = true;
        }

        for(int i = 0; i< bricks.size(); i++){
            canvas.drawRect(bricks.get(i).getLeft(), bricks.get(i).getTop(), bricks.get(i).getRight(), bricks.get(i).getBottom(), bricks.get(i).getPaint());
        }

        ball.ballBrickCollision(bricks, ball);

        if(bricks.size() == 0){
            count = -1;
            level += 1;
            createGame = true;
            gameStart = true;
        }

        if( count == -1 ){

            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(30);
            paint.setFakeBoldText(true);
            canvas.drawText("Level 2",canvas.getWidth()/2-canvas.getWidth()/9,canvas.getHeight()/2, paint);
            canvas.drawText("Your Score: "+ score,canvas.getWidth()/2-canvas.getWidth()/5,canvas.getHeight()/2+134, paint);
            canvas.drawText("Tap To Next Level",canvas.getWidth()/2-canvas.getWidth()/3+50,canvas.getHeight()/2+268, paint);
        }

        if(ball.isballAvailable() == false){
            ball.setIsBallAvailable(true);
            count = 0;
            gameStart = true;
            life -= 1;
        }

        paint.setTextSize(20);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: " + life, 20, 40, paint);
        canvas.drawText("LEVEL " + level, canvas.getWidth() - 150 , 40, paint);
        canvas.drawText( "Score: " + score,canvas.getWidth() / 2 - 60, 40, paint);

        if(life == 0 || gameOver){
            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("Game Over",canvas.getWidth()/2-canvas.getWidth()/4,canvas.getHeight()/2, paint);
            canvas.drawText("Your Score: "+ score,canvas.getWidth()/2-canvas.getWidth()/3,canvas.getHeight()/2+134, paint);
            gameOver = false;
            level = 0;
            canvas.drawText("Tap To Play Again.",canvas.getWidth()/2-canvas.getWidth()/3-20,canvas.getHeight()/2+268, paint);
            if(action==MotionEvent.ACTION_DOWN){
                life=1;
                level=1;
                score=0;
                stage=new Stage();
                stage.stage_level_one(canvas, brickX, brickY, bricks);
                new DrawCanvas(getContext());



            }
        }

        invalidate();
    }

}

