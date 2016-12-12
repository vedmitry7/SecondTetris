package com.example.dmitryvedmed.tetrissecond;


import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {
    private String shape;
    private int state = 1;

    public void setState(int state) {
        this.state = state;
        Log.i("TAG", "state " + state);

    }

    public String getShape() {
        return shape;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public int getState() {
        return state;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    GameField gameField;
    private List<Block> blocks;
    private boolean isLanded;

    public Figure(GameField gameField) {

        this.gameField = gameField;
        initBlocks();
        Log.i("TAG", "new Figure()" + " " + this.getShape());
    }

    public void step(){
        clearCurrentFigureSight();
        moveDown();
        draw();
        canDown();
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void rotate(){
        clearCurrentFigureSight();
        FigureFactory.rotate(this);
        draw();
    }

    private void draw(){
        for (Block b:blocks
                ) {
            b.draw();
        }
    }

    private void clearCurrentFigureSight(){
        for (Block block:blocks
                ) {
            gameField.setValue(block.getX(), block.getY(), 0);
        }
    }

    public boolean canDown(){
        clearCurrentFigureSight();
        boolean canDown = true;
        for (Block block:blocks
                ) {
            canDown = canDown && block.canDown();
        }
        if(!canDown)
            isLanded = true;
        draw();
        return canDown;
    }

    public boolean isLanded() {
        return isLanded;
    }

    private void moveDown(){
//test
        for (Block block:blocks
                ) {
            block.setX(block.getX()+1);
        }
    }

    public void moveRight(){
        if(isLanded)
            return;
        clearCurrentFigureSight();
        boolean canRight = true;
        for (Block block : blocks
                ) {
            canRight = canRight && block.canRight();
        }
        if(canRight) {
            Log.i("TAG", "right");
            for (Block block : blocks
                    ) {
                block.setY(block.getY() + 1);
            }
        }
        draw();
    }

    public void moveLeft(){
        if(isLanded)
            return;
        clearCurrentFigureSight();
        boolean canLeft = true;
        for (Block block : blocks
                ) {
            canLeft = canLeft && block.canLeft();
        }
        if(canLeft) {
            Log.i("TAG", "left");
            for (Block block : blocks
                    ) {
                block.setY(block.getY() - 1);
            }
        }
        draw();
    }

    public void initBlocks() {
        blocks = new ArrayList<>();

        int i = new Random().nextInt(1);
        switch (i){
            case 5:{
                //11
                //11
                setShape("square");
                blocks.add(new Block(-1,3, Color.BLUE, gameField));
                blocks.add(new Block(-1,4, Color.BLUE, gameField));
                blocks.add(new Block(0,3, Color.BLUE, gameField));
                blocks.add(new Block(0,4, Color.BLUE, gameField));
                break;
            }
            case 1:{
                //1
                //1
                //1
                //1
                setShape("line");
                blocks.add(new Block(-1,6, Color.GREEN, gameField));
                blocks.add(new Block(-1,5, Color.GREEN, gameField));
                blocks.add(new Block(-1,4, Color.GREEN, gameField));
                blocks.add(new Block(-1,3, Color.GREEN, gameField));
                break;
            }
            case 2:{
                //011
                //110
                setShape("s-shape");
                blocks.add(new Block(-1,5, Color.RED, gameField));
                blocks.add(new Block(-1,4, Color.RED, gameField));
                blocks.add(new Block(0,4, Color.RED, gameField));
                blocks.add(new Block(0,3, Color.RED, gameField));
                break;
            }
            case 3: {
                //010
                //111
                setShape("tee");
                blocks.add(new Block(-1, 4, Color.YELLOW, gameField));
                blocks.add(new Block(0, 5, Color.YELLOW, gameField));
                blocks.add(new Block(0, 4, Color.YELLOW, gameField));
                blocks.add(new Block(0, 3, Color.YELLOW, gameField));
                break;
            }
            case 4:{
                //110
                //011
                setShape("z-shape");
                blocks.add(new Block(-1,3, Color.BLACK, gameField));
                blocks.add(new Block(-1,4, Color.BLACK, gameField));
                blocks.add(new Block(0,4, Color.BLACK, gameField));
                blocks.add(new Block(0,5, Color.BLACK, gameField));
                break;
            }
            case 0:{
                //110
                //011
                setShape("L-shape");
                setState(1);
                blocks.add(new Block(-1,4, Color.WHITE, gameField));
                blocks.add(new Block(0,4, Color.WHITE, gameField));
                blocks.add(new Block(1,4, Color.WHITE, gameField));
                blocks.add(new Block(1,5, Color.WHITE, gameField));
                break;
            }
            case 6:{
                //110
                //011
                setShape("J-shape");
                blocks.add(new Block(-1,5, Color.RED, gameField));
                blocks.add(new Block(0,5, Color.RED, gameField));
                blocks.add(new Block(1,5, Color.RED, gameField));
                blocks.add(new Block(1,4, Color.RED, gameField));
                break;
            }
        }
    }
}
