import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.image.*;

public class Main extends Application{

    private final static int WHITE = 0;
    private final static int BLACK = 1;
    private static final int HEIGHT = 690;
    private static final int WIDTH = 670;
    private static final int TILE_SIZE = 660/8;
    private static int[][] board = 
    {
        {WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK},
        {BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE},
        {WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK},
        {BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE},
        {WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK},
        {BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE},
        {WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK},
        {BLACK,WHITE,BLACK,WHITE,BLACK,WHITE,BLACK,WHITE}
    };

    public void start(Stage stage){
        stage.setTitle("Chess");
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        Group root = new Group();
        Canvas canvas = new Canvas(stage.getHeight(), stage.getWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawBoard(gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void drawBoard(GraphicsContext gc){
        for(int i = 0; i < 8; i++){ 
            for(int x = 0 ; x < 8; x++){
                if(board[i][x] == BLACK)
                    gc.setFill(Color.GRAY);
                else
                    gc.setFill(Color.WHITE);
                gc.setStroke(Color.BLACK);
                gc.drawImage(new Image("\\resource\\QueenB.png"), i*TILE_SIZE, i*TILE_SIZE);
                gc.fillRect(0 + (x * TILE_SIZE), 0 + (i * TILE_SIZE), TILE_SIZE, TILE_SIZE);
            }
        }
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}