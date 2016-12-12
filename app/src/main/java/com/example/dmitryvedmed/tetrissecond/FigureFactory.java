package com.example.dmitryvedmed.tetrissecond;


import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class FigureFactory {
    public static void rotate(Figure figure){
        int x = figure.getBlocks().get(0).getX();
        int y = figure.getBlocks().get(0).getY();
        for (Block block:figure.getBlocks()
                ) {
            if(block.getX() < x)
                x = block.getX();
            if(block.getY() > y)
                y = block.getY();
        }
       switch (figure.getShape()){
           case "L-shape":{
               switch (figure.getState()){
                   case 1:{
                       Point point = new Point(x - 1, y);
                       List<Block> blocks = new ArrayList<>();
                       blocks.add(new Block(point.x-2, point.y, Color.GREEN, figure.gameField));
                       blocks.add(new Block(point.x-1, point.y, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x - 1, point.y+1, Color.GREEN, figure.gameField));
                       blocks.add(new Block(point.x - 2, point.y+1, Color.RED, figure.gameField));
                       figure.setBlocks(blocks);
                       figure.setState(2);
                       break;
                   }
                   case 2:{
                       Point point = new Point(x, y + 1);
                       List<Block> blocks = new ArrayList<>();
                       blocks.add(new Block(point.x, point.y, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x +1, point.y, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 1, point.y-1, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 1, point.y-2, Color.RED, figure.gameField));
                       figure.setBlocks(blocks);
                       figure.setState(3);
                        break;
                   }
                   case 3 :{
                       Point point = new Point(x , y);
                       List<Block> blocks = new ArrayList<>();
                       blocks.add(new Block(point.x, point.y-2, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x, point.y-1, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 1, point.y-1, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 2, point.y-1, Color.RED, figure.gameField));
                       figure.setBlocks(blocks);
                       figure.setState(4);
                       break;
                   }
                   case 4:{
                       Point point = new Point(x , y);
                       List<Block> blocks = new ArrayList<>();
                       blocks.add(new Block(point.x+1, point.y, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x+1, point.y-1, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 1, point.y-2, Color.RED, figure.gameField));
                       blocks.add(new Block(point.x + 2, point.y-2, Color.RED, figure.gameField));
                       figure.setBlocks(blocks);
                       figure.setState(1);
                       break;
                   }
               }
           }
       }
    }
}
