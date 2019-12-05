package pieces;

import java.util.ArrayList;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import point.Point;
import run.Main;

public abstract class Piece extends StackPane{

    private boolean color; // true = black, false = white
    private static final short MOUSE_IMG_DRAG_OFFSET = 30;
    public static final short IMG_X_OFFSET = 10;
    public static final short IMG_Y_OFFSET = 10;
    private Point pos;
    public Point start, end;
    private boolean hasMoved = false;
    private boolean castle = false, pawnPromotion = false;

    public Piece(int x, int y, boolean color){
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
        convertToBoardCoords(start);
        convertToBoardCoords(end);
        System.out.println(start.getX() + " " + start.getY());
        System.out.println(end.getX() + " " + end.getY());
        if(isValid(start, end)){
        	if(isCastle()){
                castle = false;
                System.out.println("castle");
                Main.getBoard()[start.getX()][start.getY()] = Main.getBoard()[end.getX()][end.getY()];
                Main.getBoard()[end.getX()][end.getY()].relocate(IMG_X_OFFSET + (start.getX() * Main.TILE_SIZE), IMG_Y_OFFSET + (start.getY() * Main.TILE_SIZE));
                Main.getBoard()[end.getX()][end.getY()] = this;
                this.relocate(IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE));
                hasMoved = true;
                Main.turn = !Main.turn;
            }else if (isPawnPromotion(end)){
                Main.showSelectionFrame(this.color);
                Main.getBoard()[end.getX()][end.getY()].setVisible(false);
                this.setVisible(false);
                Main.getBoard()[start.getX()][start.getY()] = new PlaceHolder(start.getX(), start.getY());
                Piece temp = Main.getPawnProSelection(end.getX(), end.getY(), color);
                Main.getBoard()[end.getX()][end.getY()] = temp;                                                             // change this  
                temp.relocate(IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE)); // and this
                Main.turn = !Main.turn;
            }
            else{
                Main.getBoard()[end.getX()][end.getY()].setVisible(false);  
                Main.getBoard()[start.getX()][start.getY()] = new PlaceHolder(start.getX(), start.getY());
                Main.getBoard()[end.getX()][end.getY()] = this;
                this.relocate(IMG_X_OFFSET + (end.getX() * Main.TILE_SIZE), IMG_Y_OFFSET + (end.getY() * Main.TILE_SIZE));
                hasMoved = true;
                Main.turn = !Main.turn;
            }
        }else
            this.relocate(IMG_X_OFFSET + start.getX() * Main.TILE_SIZE, IMG_Y_OFFSET + start.getY() * Main.TILE_SIZE);
        //Main.printBoard();
        System.out.println("\n\n");
    }

    private void convertToBoardCoords(Point p){
        p.setX(p.getX()/Main.TILE_SIZE);
        p.setY(p.getY()/Main.TILE_SIZE);
    }

    protected boolean isValid(Point start, Point end){
        //System.out.println(Main.getBoard()[end.getX()][end.getY()]);
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

    public boolean hasMoved(){
        return hasMoved;
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
