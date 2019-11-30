package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class King extends Piece{

    public King(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        return super.isValid(start, end);
    }

}