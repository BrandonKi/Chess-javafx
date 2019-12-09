package pieces;

import point.Point;
import run.Main;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean color) {
        super(x, y, color);

    }

    protected boolean isValid(Point start, Point end) {
        if (super.isValid(start, end)) {
            if ((Main.getBoard()[start.getX()][start.getY()].getColor() ? start.getY() == 1 : start.getY() == 6)
                    && end.getY() == (Main.getBoard()[start.getX()][start.getY()].getColor() ? 3 : 4)
                    && start.getX() == end.getX()
                    && Main.getBoard()[start.getX()][(Main.getBoard()[start.getX()][start.getY()].getColor() ? 2 : 5)].isPlaceHolder()
                    && Main.getBoard()[start.getX()][(Main.getBoard()[start.getX()][start.getY()].getColor() ? 3 : 4)].isPlaceHolder()) {
                        System.out.println(Main.getBoard()[start.getX()][start.getY()].getColor());
                if(Main.getBoard()[start.getX()][start.getY()].getColor() == Main.BLACK)
                    passantHelperB = true;
                else
                    passantHelperW = true;
                super.passant = true;
                return true;
            }
            System.out.println(Main.getBoard()[end.getX()][end.getY()
                + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1)].passant == true);
            System.out.println(Main.getBoard()[end.getX()][end.getY()
                    + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1)]);
            if (Main.getBoard()[end.getX()][end.getY()].isPlaceHolder()
                    && (Main.getBoard()[start.getX()][start.getY()].getColor() ? end.getY() - start.getY() == 1
                            : start.getY() - end.getY() == 1)
                    && ((end.getX() - start.getX() == 1 && end.getX() > start.getX())
                            || (end.getX() - start.getX() == -1 && end.getX() < start.getX()))
                    && Main.getBoard()[end.getX()][end.getY()
                            + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1)].passant == true &&
                            (Main.getBoard()[start.getX()][start.getY()].getColor() ? passantHelperW == true : passantHelperB == true)) {
                        
                Main.getBoard()[end.getX()][end.getY() + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1)].setVisible(false);
                Main.getBoard()[end.getX()][end.getY() + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1)] = new PlaceHolder(end.getX(), end.getY() + (Main.getBoard()[start.getX()][start.getY()].getColor() ? -1 : 1));
                return true;
            }
            if ((Main.getBoard()[end.getX()][end.getY()].isPlaceHolder()
                    && (Main.getBoard()[start.getX()][start.getY()].getColor() ? end.getY() - start.getY() == 1
                            : start.getY() - end.getY() == 1)
                    && start.getX() == end.getX())
                    || (!Main.getBoard()[end.getX()][end.getY()].isPlaceHolder()
                            && (start.getX() - end.getX() == 1 || start.getX() - end.getX() == -1)
                            && (Main.getBoard()[start.getX()][start.getY()].getColor() ? end.getY() - start.getY() == 1
                                    : start.getY() - end.getY() == 1))) {
                return true;
            }
        }
        return false;
    }
}
