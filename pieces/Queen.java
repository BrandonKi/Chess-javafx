package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Queen extends Piece{

    public Queen(int x, int y, boolean color) {
        super(x, y, color);
    }
    
    protected boolean isValid(Point start, Point end){
        return super.isValid(start, end);
    }
    
}