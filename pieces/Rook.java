package pieces;

import point.Point;
import run.Main;

public class Rook extends Piece {

    public Rook(int x, int y, boolean color) {
        super(x, y, color);
    }
 
    protected boolean isValid(Point start, Point end){
        if(super.isValid(start, end) && (Math.abs(end.getY() - start.getY()) == 0 || Math.abs(end.getX() - start.getX()) == 0)){
            short difX = (short)(end.getX() - start.getX());
            short difY = (short)(end.getY() - start.getY());
            if(difY > 0 && difX == 0){
                for(short i = 1; i < difY; i++){
                    if(!Main.getBoard()[end.getX()][end.getY() - i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY < 0 && difX == 0){
                for(short i = 1; i < -difY; i++){
                    if(!Main.getBoard()[end.getX()][end.getY() + i].isPlaceHolder())
                        return false;
                }
            }
            else if(difY == 0 && difX > 0){
                for(short i = 1; i < -difX; i++){
                    if(!Main.getBoard()[end.getX() - i][end.getY()].isPlaceHolder())
                        return false;
                }
            }
            else if(difY == 0 && difX < 0){
                for(short i = 1; i < -difX; i++){
                    if(!Main.getBoard()[end.getX() + i][end.getY()].isPlaceHolder())
                        return false;
                }
            }       
            return true;
        }
        return false;
    }
}