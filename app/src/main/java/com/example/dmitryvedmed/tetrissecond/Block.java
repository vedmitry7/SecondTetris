package com.example.dmitryvedmed.tetrissecond;



public class Block {
    GameField gameField;

    public GameField getGameField() {
        return gameField;
    }

    private int x;
    private int y;
    private int color;

    public Block(int x, int y, int color, GameField gameField) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.gameField = gameField;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void draw(){
        gameField.setValue(x,y,color);
    }

    public boolean canDown(){
       return gameField.check(x + 1, y);
    }

    public boolean canLeft(){
        return gameField.check(x , y - 1);
    }

    public boolean canRight(){
        return gameField.check(x , y + 1);
    }
}