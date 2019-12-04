package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Queen extends Piece{

    public Queen(int x, int y, boolean color) {
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
        else if(super.isValid(start, end) && (Math.abs(end.getY() - start.getY()) == 0 || Math.abs(end.getX() - start.getX()) == 0)){
            short difX = (short)(end.getX() - start.getX());
            short difY = (short)(end.getY() - start.getY());
            //System.out.println(difX + " " + difY);
            if(difY > 0 && difX == 0){
                System.out.println(1);
                for(short i = 1; i < difY; i++){
                    System.out.println(Main.getBoard()[end.getX()][end.getY() - i] + " " + (end.getX()) + ", " + (end.getY() - i));
                    if(!Main.getBoard()[end.getX()][end.getY() - i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY < 0 && difX == 0){
                System.out.println(2);  
                for(short i = 1; i < -difY; i++){
                    System.out.println(Main.getBoard()[end.getX()][end.getY() + i] + " " + (end.getX()) + ", " + (end.getY() + i) + " 111111111");
                    if(!Main.getBoard()[end.getX()][end.getY() + i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY == 0 && difX > 0){
                System.out.println(3 + "   " + difY);
                for(short i = 1; i < -difX; i++){
                    System.out.println(Main.getBoard()[end.getX() - i][end.getY()] + " " + (end.getX() - i) + ", " + (end.getY()));
                    if(!Main.getBoard()[end.getX() - i][end.getY()].isPlaceHolder())
                        return false;
                }
            }
            else if(difY == 0 && difX < 0){
                System.out.println(4);
                for(short i = 1; i < -difX; i++){
                    System.out.println(Main.getBoard()[end.getX() + i][end.getY()] + " " + (end.getX() + i) + ", " + (end.getY()));
                    if(!Main.getBoard()[end.getX() + i][end.getY()].isPlaceHolder())
                        return false;
                }
            }       
            return true;
        }
        return false;
    }
    
}