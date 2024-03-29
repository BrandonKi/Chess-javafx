package pieces;

import point.Point;
import run.Main;

public class King extends Piece{

    public King(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        
        if(!hasMoved() && start.getY() == end.getY() && (end.getX() == 2 && Main.getBoard()[0][end.getY()].getClass().getName().equals("pieces.Rook") && !Main.getBoard()[0][end.getY()].hasMoved()) || (end.getX() == 6 && Main.getBoard()[7][end.getY()].getClass().getName().equals("pieces.Rook") && !Main.getBoard()[7][end.getY()].hasMoved()))
            {
                for(int i = start.getX() > end.getX() ? end.getX() + 1 : start.getX() + 1; i < (start.getX() > end.getX() ? start.getX() : end.getX()); i++){
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