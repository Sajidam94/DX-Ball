package et.sajid.dxball;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

class Stage {
    public void stage_level_one(Canvas canvas, float brickX, float brickY, ArrayList<Bricks> bricks){
        int col;
        for (int i = 0; i < 10; i++) {
            if (brickX >= canvas.getWidth() - (canvas.getWidth() / 7) * 2) {
                brickX = canvas.getWidth() / 7;
                brickY += brickY+31;
            }
            if (i % 2 == 0)
                col = Color.MAGENTA;
            else
                col = Color.GRAY;
            bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 7, brickY + canvas.getHeight() / 10, col));
            brickX += canvas.getWidth() / 7;
        }
    }

    public void stage_level_two(Canvas canvas, float brickX, float brickY, ArrayList<Bricks> bricks){
        for (int i = 0; i < 15; i++) {
            int col;
            if (brickX >= canvas.getWidth() - (canvas.getWidth() / 7) * 2) {
                brickX = canvas.getWidth() / 7;
                brickY += brickY+31;
            }
            if (i % 2 == 0)
                col = Color.MAGENTA;
            else
                col = Color.DKGRAY;
            bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 7, brickY + canvas.getHeight() / 15, col));
            brickX += canvas.getWidth() / 7;
        }
    }
}
