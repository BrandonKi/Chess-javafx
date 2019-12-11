package pieces;

import java.util.ArrayList;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import point.Point;
import run.Main;
import aicontroller.AIController;

public abstract class Piece extends StackPane{

    private boolean color; // true = black, false = white
    private static final short MOUSE_IMG_DRAG_OFFSET = 30;
    
    private Point pos;
    public Point start, end;
    private boolean hasMoved = false;
    protected boolean castle = false, passant = false;
    public boolean AIControlled = false;                                 // AI is default false
    protected static boolean passantHelperW = false, passantHelperB = false;
    public static Point pawnProPos;
    private static ImageView unknown;
    private AIController AI;

    public Piece(int x, int y, boolean color){
        if(AIControlled)
            AI = new AIController();
        pos = new Point(x, y);

        this.color = color;
        if(!isPlaceHolder()){
            getChildren().add(new ImageView(new Image("pieces\\resource\\" + getClass().getName().substring(getClass().getName().indexOf(".")+1) + (color ? "B" : "W") + ".png")));
            makeDraggable(this);
        }
    }
   
    public Image getImage(){
        if(!isPlaceHolder())
            return new Image("pieces\\resource\\" + getClass().getName().substring(getClass().getName().indexOf(".")+1) + (color ? "B" : "W") + ".png");
        return null;
    }

    public String toString(){
        return isPlaceHolder() ? "Empty" : (color ? "Black " : "White ") + getClass().getName().substring(getClass().getName().indexOf(".")+1);
    }

    public Point getGridPosition(){
        return pos;
    }

    public void setX(int x){
        pos = new Point(x,pos.getY());
    }

    public void setY(int y){
        pos = new Point(pos.getX(), y);
    }

    public boolean getColor(){
        return color;
    }

    public void move(Point start, Point end){

        if(inCheck(this.color))
            System.out.println(color + "CHECK!!!");

        convertToBoardCoords(start);
        convertToBoardCoords(end);
        System.out.println(start.getX() + " " + start.getY());
        System.out.println(end.getX() + " " + end.getY());
        if(isValid(start, end)){
        	if(isCastle()){
                castle = false;
                System.out.println("castle");
                Main.getBoard()[start.getX()][start.getY()] = Main.getBoard()[end.getX()][end.getY()];
                Main.getBoard()[end.getX()][end.getY()].relocate(Main.IMG_X_OFFSET + (start.getX() * Main.TILE_SIZE), Main.IMG_Y_OFFSET + (start.getY() * Main.TILE_SIZE));
                Main.getBoard()[end.getX()][end.getY()] = this;
                this.relocate(Main.IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), Main.IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE));
                hasMoved = true;
                Main.turn = !Main.turn;
            }else if (isPawnPromotion(end)){
                this.setVisible(false);
                Main.getBoard()[start.getX()][start.getY()] = new PlaceHolder(start.getX(), start.getY());
                Main.getBoard()[end.getX()][end.getY()].setVisible(false);
                unknown = new ImageView(new Image("pieces\\resource\\Unknown.png"));
                Main.pane.getChildren().add(unknown);
                unknown.relocate(Main.IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), Main.IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE));
                pawnProPos = end;
                Main.showSelectionFrame(this.color);
                // goto promote method
                //The rest is carried out by the "promote" method which is called after a selection is made
            }
            else if(!Main.getBoard()[end.getX()][end.getY()].getClass().getName().equals("pieces.King")){
                Main.getBoard()[end.getX()][end.getY()].setVisible(false);  
                Main.getBoard()[start.getX()][start.getY()] = new PlaceHolder(start.getX(), start.getY());
                Main.getBoard()[end.getX()][end.getY()] = this;
                this.relocate(Main.IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), Main.IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE));
                hasMoved = true;
                Main.turn = !Main.turn;
            }else
                this.relocate(Main.IMG_X_OFFSET + start.getX() * Main.TILE_SIZE, Main.IMG_Y_OFFSET + start.getY() * Main.TILE_SIZE);
        }else
            this.relocate(Main.IMG_X_OFFSET + start.getX() * Main.TILE_SIZE, Main.IMG_Y_OFFSET + start.getY() * Main.TILE_SIZE);
        if(Main.turn == Main.BLACK && passantHelperB){
            passantHelperB = false;
            setAllPassantFalse(Main.BLACK);
        }
        if(Main.turn == Main.WHITE && passantHelperW){
            passantHelperW = false;
            setAllPassantFalse(Main.WHITE);
        }
        System.out.println("\n\n");

        if(AIControlled && Main.turn == Main.BLACK){
            AI.start();
        }
    }


    private boolean inCheck(boolean color){
        ArrayList<Piece> list = new ArrayList<Piece>();
        Point king = getKingPos(color);
        for(byte i = 0; i < 8; i++){
            for(byte x = 0; x < 8; x++){
                if(Main.getBoard()[i][x].getColor() == !color && !Main.getBoard()[i][x].isPlaceHolder())
                    list.add(Main.getBoard()[i][x]);
            }
        }
        for(Piece p: list){
            if(p.isValid(p.getGridPosition(), king)){
                System.out.println(p + " " + Main.getBoard()[king.getX()][king.getY()] + " " + p.getGridPosition() + " " + king);
                return true;
            }
            System.out.println(p + " " + p.getGridPosition() + " " + king + " " + p.isValid(p.getGridPosition(), king));
        }
        return false;
    }

    private Point getKingPos(boolean color){
        for(byte i = 0; i < 8; i++){
            for(byte x = 0; x < 8; x++){
                if(Main.getBoard()[i][x].getClass().getName().equals("pieces.King") && Main.getBoard()[i][x].getColor() == color)
                    return new Point(i, x);
            }
        }
        return null;
    }

    private void setAllPassantFalse(boolean color){
        for(byte i = 0; i < 8; i++){
            for(byte x = 0; x < 8; x++){
                if (Main.getBoard()[i][x].getColor() == color)
                    Main.getBoard()[i][x].passant = false;
            }
        }
    }
    private void convertToBoardCoords(Point p){
        p.setX(p.getX()/Main.TILE_SIZE);
        p.setY(p.getY()/Main.TILE_SIZE);
    }

    protected boolean isValid(Point start, Point end){
        //System.out.println(Main.getBoard()[end.getX()][end.getY()]);
        if(Main.getBoard()[end.getX()][end.getY()] )
        if(this.isPlaceHolder())
            return false;
        if((Main.getBoard()[end.getX()][end.getY()].isPlaceHolder() || Main.getBoard()[end.getX()][end.getY()].getColor() != Main.getBoard()[start.getX()][start.getY()].getColor())){
            return true;
        }
        return false;
    }

    protected void setCastle(){
        castle = true;
    }

    protected boolean isCastle(){
        return castle;
    }

    protected boolean isPawnPromotion(Point end){
        if(this.getClass().getName().indexOf("Pawn") != -1 && (end.getY() == 0 || end.getY() == 7))
            return true;
        return false;
    }

    public boolean isPlaceHolder(){
        return getClass().getName().substring(getClass().getName().indexOf('.')+1).indexOf("Place") != -1;
    }

    protected boolean hasMoved(){
        return hasMoved;
    }

    public static void promote(String sel){
        unknown.setVisible(false);
        Main.getBoard()[pawnProPos.getX()][pawnProPos.getY()].setVisible(false);
        Piece temp = Main.createPiece(pawnProPos.getX(), pawnProPos.getY(), sel.charAt(sel.length()-1) == 'B' ? true : false, sel.substring(0,sel.length()-1));
        Main.pane.getChildren().add(temp);
        Main.getBoard()[pawnProPos.getX()][pawnProPos.getY()] = temp;     
        temp.relocate(Main.IMG_X_OFFSET + pawnProPos.getX() * Main.TILE_SIZE, Main.IMG_Y_OFFSET + pawnProPos.getY() * Main.TILE_SIZE); // and this
        temp.setVisible(true);
        Main.turn = !Main.turn;
    }

    private void makeDraggable(Piece p) {
        Node node = (Node)p;
        node.setOnMouseEntered(e -> {
            if(color == Main.turn)
                node.getScene().setCursor(Cursor.HAND);
        });

        node.setOnMouseExited(e -> {
            node.getScene().setCursor(Cursor.DEFAULT);
        });

        node.setOnMousePressed(e -> {
            if(color == Main.turn){
                node.getScene().setCursor(Cursor.CLOSED_HAND);
                start = new Point((int)e.getSceneX(), (int)e.getSceneY());
            }
        });

        node.setOnMouseReleased(e -> {
            if(color == Main.turn){
                node.getScene().setCursor(Cursor.HAND);
                end = new Point((int)e.getSceneX(), (int)e.getSceneY());
                p.move(start, end);
            }
        });

        node.setOnMouseDragged(e -> {
            if(color == Main.turn){
                node.setLayoutX(node.getLayoutX() + e.getX() - MOUSE_IMG_DRAG_OFFSET);
                node.setLayoutY(node.getLayoutY() + e.getY() - MOUSE_IMG_DRAG_OFFSET);
            }
        });
    }
}
