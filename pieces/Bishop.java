package pieces;

import java.util.ArrayList;

import point.Point;
import run.Main;

public class Bishop extends Piece{

    public Bishop(int x, int y, boolean color) {
        super(x, y, color);
    }

    protected boolean isValid(Point start, Point end){
        if(super.isValid(start, end)){
            if(Math.abs(end.getY() - start.getY()) == Math.abs(end.getX() - start.getX())){
                for(int i = 0; i < Math.abs(start.getY() - end.getY()) - 1; i++){
                    System.out.println(start.getX() - i + " " + (start.getY() + i));
                    System.out.println(Main.getBoard()[start.getX() - i][start.getY() + i]);
                    if(start.getX() < end.getX() && start.getY() < end.getY())
                        if(start.getX() + i < Main.getBoard().length && start.getY() + i < start.getX() && !Main.getBoard()[start.getX() + i][start.getY() + i].isPlaceHolder()){
                            System.out.println(1);
                            return false;
                        }
                    if(start.getX() > end.getX() && start.getY() < end.getY())
                        if(start.getX() - i >= 0 && start.getY() + i < Main.getBoard().length)
                            if(!Main.getBoard()[start.getX() - i][start.getY() + i].isPlaceHolder()){
                                System.out.println(2);
                                return false;
                            }
                    if(start.getX() < end.getX() && start.getY() > end.getY())
                        if(start.getX() + i < Main.getBoard().length && start.getY() - i >= 0 && !Main.getBoard()[start.getX() + i][start.getY() - i].isPlaceHolder()){
                            System.out.println(3);
                            return false;
                        }
                    if(start.getX() > end.getX() && start.getY() > end.getY())
                        if(start.getX() - i >= 0 && start.getY() - i >= 0 && !Main.getBoard()[start.getX() - i][start.getY() - i].isPlaceHolder()){
                            System.out.println(4);
                            return false;
                        }
                }
                return true;
            }
        }
        return false;
    }

}