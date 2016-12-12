package com.example.dmitryvedmed.tetrissecond;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameField {
    private int blockHeight;
    private final int fieldWidth = 10;
    private final int fieldHeight = 20;
    int matrix[][] = new int[fieldHeight][fieldWidth];
    private int blockSize;
    ArrayList<int[]> lines;
    Point startPoint;


    Random random;

    private static boolean needRedraw;
    public GameField() {
        blockHeight = Constants.SCREEN_HEIGHT/ fieldHeight;
        startPoint = new Point(Constants.SCREEN_WIDTH/2-blockHeight*5,
                Constants.SCREEN_HEIGHT-blockHeight);
        random = new Random();
        lines = new ArrayList<>();
        blockSize = Constants.SCREEN_HEIGHT/fieldHeight;

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                matrix[i][j] = 0;
            }
        }
        /*

        setValue(19,0, Color.DKGRAY);
        setValue(18,0, Color.LTGRAY);
        setValue(19,9, Color.DKGRAY);
        setValue(18,9, Color.LTGRAY);
         */
    }
    public static boolean isNeedRedraw() {
       return needRedraw;
    }


    public void setValue(int x,int y, int value) {
        if(x>-1 && x < fieldHeight && y > -1 && y < fieldWidth)
        matrix[x][y] = value;
    }

    public void draw(Canvas canvas){
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if(matrix[i][j] != 0){
                    drawBlock(canvas,i,j);
                }
            }
        }
    }

       public boolean check(int x, int y){
      //  Log.i("TAG", x+":"+y);

        if(x < fieldHeight && y < fieldWidth && y > -1) {
            if (matrix[x][y] == 0)
                return true;
        }
        return false;

    }

    public static void setNeedRedraw(boolean needRedraw) {
        GameField.needRedraw = needRedraw;
    }

    public void checkLines(){
        Log.i("TAG", "CHECk LINES");
        ArrayList<int[]> lines = new ArrayList<>();

        for (int i = 0; i < fieldHeight; i++) {
            int count = 0;
            for (int j = 0; j < fieldWidth; j++) {
                if(matrix[i][j] != 0)
                    count++;
            }
            if(count!= fieldWidth){
                lines.add(matrix[i]);
            }
        }
        while (lines.size()< fieldHeight){
            lines.add(0,new int[fieldWidth]);
            Log.i("TAG", "ADD EMPTY LINE");
            needRedraw = true;
        }
        matrix = lines.toArray(new int[fieldHeight][fieldWidth]);

        Log.i("TAG", "Need reDraw = " + needRedraw);
    }


    private void drawBlock(Canvas canvas, int i, int j){
        Paint paint = new Paint();
        paint.setColor(matrix[i][j]);
        paint.setAlpha(200);
        //paint.setARGB(255, random.nextInt(255),random.nextInt(255),random.nextInt(255));

        canvas.drawRect( startPoint.x + j*blockSize,
                blockHeight*i,
                startPoint.x + j * blockHeight + blockHeight,
                blockHeight*i+blockHeight,
                paint);
    }
}
