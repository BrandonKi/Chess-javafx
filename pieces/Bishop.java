package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Bishop extends Piece{

    public Bishop(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        if(super.isValid(start, end) && Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX())){
            short difX = (short)(end.getX() - start.getX());
            short difY = (short)(end.getY() - start.getY());
            if(difY > 0 && difX > 0){
                for(short i = 1; i < difX; i++){
                    System.out.println(Main.getBoard()[end.getX() - i][end.getY() - i] + " " + (end.getX() - i) + ", " + (end.getY() - i));
                    if(!Main.getBoard()[end.getX() - i][end.getY() - i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY < 0 && difX > 0){
                for(short i = 1; i < difX; i++){
                    System.out.println(Main.getBoard()[end.getX() - i][end.getY() + i] + " " + (end.getX() - i) + ", " + (end.getY() + i));
                    if(!Main.getBoard()[end.getX() - i][end.getY() + i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY > 0 && difX < 0){
                for(short i = 1; i < difY; i++){
                    System.out.println(Main.getBoard()[end.getX() + i][end.getY() - i] + " " + (end.getX() + i) + ", " + (end.getY() - i));
                    if(!Main.getBoard()[end.getX() + i][end.getY() - i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY < 0 && difX < 0){
                for(short i = 1; i < -difX; i++){
                    System.out.println(Main.getBoard()[end.getX() + i][end.getY() + i] + " " + (end.getX() + i) + ", " + (end.getY() + i));
                    if(!Main.getBoard()[end.getX() + i][end.getY() + i].isPlaceHolder())
                        return false;
                }
            }       
            return true;
        }
        return false;
    }

}