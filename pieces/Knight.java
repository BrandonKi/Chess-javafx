package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Knight extends Piece{

    public Knight(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        if(super.isValid(start, end)){
            if(
                (Math.abs(end.getX() - start.getX()) == 2 && Math.abs(end.getY() - start.getY()) == 1) ||
                (Math.abs(end.getX() - start.getX()) == 1 && Math.abs(end.getY() - start.getY()) == 2))
                    return true;
        }
        return false;
    }

}