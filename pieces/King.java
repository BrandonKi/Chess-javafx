package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class King extends Piece{

    public King(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        
        if(!hasMoved() && Main.getBoard()[end.getX()][end.getY()].getClass().getName().equals("pieces.Rook") && !Main.getBoard()[end.getX()][end.getY()].hasMoved()){
                for(int i = start.getX() + 1; i < end.getX(); i++){
                    System.out.println(i + " " + end.getY());
                    System.out.println(Main.getBoard()[i][end.getY()]);
                    if(!Main.getBoard()[i][end.getY()].isPlaceHolder())
                        return false;
                }
                setCastle();
                return true;
        }
                
        if(super.isValid(start, end)){
            if(
                (Math.abs(end.getX() - start.getX()) == 1 && Math.abs(end.getY() - start.getY()) == 1) ||
                (Math.abs(end.getX() - start.getX()) == 1 && Math.abs(end.getY() - start.getY()) == 0) ||
                (Math.abs(end.getX() - start.getX()) == 0 && Math.abs(end.getY() - start.getY()) == 1))
                    return true;
        }
        return false;
    }
}