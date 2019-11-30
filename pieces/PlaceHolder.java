package pieces;

import java.util.ArrayList;

import point.Point;

public class PlaceHolder extends Piece {

    public PlaceHolder(int x, int y) {
        super(x, y, false);
    }

    protected ArrayList<Point> getAvailableMoves(Point current) {
        return new ArrayList<Point>();
    }
}