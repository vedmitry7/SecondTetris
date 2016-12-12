package com.example.dmitryvedmed.tetrissecond;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;


public class GameManager extends Thread {
    private static final int MAX_FSP = 30;
    private double averageFSP;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    GameField gameField;
    private Figure figure;
    int period = 15;

    public void setPeriod(int period) {
        this.period = period;
    }

    public GameManager(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        gameField = new GameField();
        figure = new Figure(gameField);
    }

    private void update(Canvas canvas){
        Log.i("TAG", "Update");
        if(figure.isLanded()) {
            gameField.checkLines();
            figure = new Figure(gameField);
        }
        figure.step();
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FSP;
        int updateCount = 0;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    canvas.drawColor(Color.GRAY);
                    if (updateCount == 0)
                        update(canvas);
                    updateCount++;
                    if (updateCount>period)
                        updateCount = 0;
                    gameField.draw(canvas);

                    /*  this.gameView.update();
                    this.gameView.draw(canvas);*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;

           // Log.i("TAG", String.valueOf(timeMillis));

            waitTime = targetTime - timeMillis;
                try {
                if(waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e){e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FSP){
                averageFSP = 1000/(totalTime/frameCount/1000000);
                frameCount = 0;
                totalTime = 0;
                Log.i("TAG", String.valueOf(averageFSP));
            }
        }

    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean move(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            Log.i("TAG", "ROTATE");
            figure.rotate();
        }
       /* switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                // Log.i("TAG", "X = " + x + "Y = " + y);
                if(x < Constants.SCREEN_WIDTH/2  && y < Constants.SCREEN_HEIGHT && y > Constants.SCREEN_HEIGHT/10){
                    //   Log.i("TAG", "left method()");
                    figure.moveLeft();
                } else {
                    //    Log.i("TAG", "right method()");
                    figure.moveRight();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //     playerPoint.set((int)event.getX(), (int)event.getY());

        }*/
        return true;
    }
}
