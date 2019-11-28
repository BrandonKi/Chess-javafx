package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import point.Point;
import run.Main;

public abstract class Piece extends StackPane{

    private boolean color; // true = black, false = white
    private Point pos;
    public int x, y;

    public Piece(int x, int y, boolean color){
        pos = new Point(x* 82, y * 82);
        x = pos.getX();
        y = pos.getY();
        this.color = color;
        getChildren().add(new ImageView(new Image("pieces\\resource\\" + getClass().getName().substring(getClass().getName().indexOf(".")+1) + (color ? "B" : "W") + ".png")));
        //setPrefSize(Main.TILE_SIZE, Main.TILE_SIZE);
        // this.setOnMouseDragged(e -> {
        //     relocate(e.getSceneX(), e.getSceneY());
        //     System.out.println(e.getSceneX() + " " + e.getSceneY());
        // });
    }
   
    public Image getImage(){
        return new Image("pieces\\resource\\" + getClass().getName().substring(getClass().getName().indexOf(".")+1) + (color ? "B" : "W") + ".png");
    }

    public String toString(){
        return (color ? "Black " : "White ") + getClass().getName().substring(getClass().getName().indexOf(".")+1);
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

    public void moveTo(int x, int y){
        pos = new Point(x, y);
    }

    // this.setOnMousePressed(e -> {
    //     mouseX = e.getSceneX();
    //     mouseY = e.getSceneY();
    // });

    
}
