package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Rook extends Piece {

    public Rook(int x, int y, boolean color) {
        super(x, y, color);
    }
 
    protected boolean isValid(Point start, Point end){
        return super.isValid(start, end);
    }

    

}