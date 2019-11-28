package run;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Main extends Application {
    private final static boolean WHITE = false;
    private final static boolean BLACK = true;
    private static final int HEIGHT = 750;
    private static final int WIDTH = 720;
    public static final int TILE_SIZE = 720/8;
    private static Piece[][] currentBoard;
    private static Piece[][] initialBoard;


    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        Pane p = new Pane();
        stage.setTitle("Chess");
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);

        Canvas backCanvas = new Canvas(stage.getWidth(), stage.getHeight());
        GraphicsContext gcBack = backCanvas.getGraphicsContext2D();
        initBoard(gcBack);
        root.getChildren().add(backCanvas);
        drawBoard(p);
        root.getChildren().add(p);
        
        
        stage.setScene(new Scene(root,WIDTH, HEIGHT));
        stage.show();
    }

    private Piece createPiece(int x, int y, boolean color, String piece) {
        Piece temp = null;;
        switch(piece){
            case "Pawn":
                temp = new Pawn(x,y,color);
                break;
            case "Rook":
                temp = new Rook(x,y,color);
                break;
            case "Bishop":
                temp = new Bishop(x,y,color);
                break;
            case "Knight":
                temp = new Knight(x,y,color);
                break;
            case "King":
                temp = new King(x,y,color);
                break;
            case "Queen":
                temp = new Queen(x,y,color);
                break;
        }
        
        temp.relocate(0, 0);

        return temp;
    }

    private void makeDraggable(Node node) {
        final Delta dragDelta = new Delta();

        node.setOnMouseEntered(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.HAND);
            }
        });
        node.setOnMouseExited(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        node.setOnMousePressed(me -> {
            if (me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
            dragDelta.x = me.getX();
            dragDelta.y = me.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });
        node.setOnMouseReleased(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        node.setOnMouseDragged(me -> {
            node.setLayoutX(node.getLayoutX() + me.getX() - dragDelta.x);
            node.setLayoutY(node.getLayoutY() + me.getY() - dragDelta.y);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class Delta {
        public double x;
        public double y;
    }
    private void initBoard(GraphicsContext gcBack) {
                initialBoard = new Piece[][] {
                        { createPiece(0, 0, BLACK, "Rook"), createPiece(1, 0, BLACK, "Knight"), createPiece(2, 0, BLACK, "Bishop"), createPiece(3, 0, BLACK, "Queen"), createPiece(4, 0, BLACK, "King"), createPiece(5, 0, BLACK, "Bishop"), createPiece(6, 0, BLACK, "Knight"), createPiece(7, 0, BLACK, "Rook")},
                        { createPiece(0, 1, BLACK, "Pawn"), createPiece(1, 1, BLACK, "Pawn"), createPiece(2, 1, BLACK, "Pawn"), createPiece(3, 1, BLACK, "Pawn"), createPiece(4, 1, BLACK, "Pawn"), createPiece(5, 1, BLACK, "Pawn"), createPiece(6, 1, BLACK, "Pawn"), createPiece(7, 1, BLACK, "Pawn"),},
                        { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
                        { createPiece(0, 6, WHITE, "Pawn"), createPiece(1, 6, WHITE, "Pawn"), createPiece(2, 6, WHITE, "Pawn"), createPiece(3, 6, WHITE, "Pawn"), createPiece(4, 6, WHITE, "Pawn"), createPiece(5, 6, WHITE, "Pawn"), createPiece(6, 6, WHITE, "Pawn"), createPiece(7, 6, WHITE, "Pawn"),},
                        { createPiece(0, 7, WHITE, "Rook"), createPiece(1, 7, WHITE, "Knight"), createPiece(2, 7, WHITE, "Bishop"), createPiece(3, 7, WHITE, "Queen"), createPiece(4, 7, WHITE, "King"), createPiece(5, 7, WHITE, "Bishop"), createPiece(6, 7, WHITE, "Knight"), createPiece(7, 7, WHITE, "Rook")}
                };
                currentBoard = Arrays.copyOf(initialBoard, initialBoard.length);
                for (short i = 0; i < 8; i++) {
                    for (short x = 0; x < 8; x++) {
                        if ((i % 2 == 0 && x % 2 != 0) || (i % 2 != 0 && x % 2 == 0))
                            gcBack.setFill(Color.GRAY);
                        else
                            gcBack.setFill(Color.WHITE);
                        gcBack.fillRect(5 + (x * TILE_SIZE), 20 + (i * TILE_SIZE), TILE_SIZE, TILE_SIZE);
                    }
                }
            }

            private void drawBoard(Pane p) {
                        for (short i = 0; i < 8; i++) {
                            for (short x = 0; x < 8; x++) {
                                if (currentBoard[i][x] != null) {
                                    makeDraggable(currentBoard[i][x]);
                                    currentBoard[i][x].relocate(5 + x * TILE_SIZE, 15 + i * TILE_SIZE);
                                    p.getChildren().add(currentBoard[i][x]);
                                }
                            }
                        }
                    }
        
        
}


    

//     public void start(Stage stage) {

//         stage.setTitle("Chess");
//         stage.setHeight(HEIGHT);
//         stage.setWidth(WIDTH);

//         StackPane sp = new StackPane();
//         Pane back = new Pane();
//         // Pane root = new Pane();
//         Canvas backCanvas = new Canvas(stage.getHeight(), stage.getWidth());
//         GraphicsContext gcBack = backCanvas.getGraphicsContext2D();
//         //initBoard(gcBack);
//         back.getChildren().add(backCanvas);
//         Canvas canvas = new Canvas(stage.getHeight(), stage.getWidth());
//         GraphicsContext gc = canvas.getGraphicsContext2D();
//         sp.getChildren().add(back);
//         //drawBoard(gc, sp);
//         Pawn p = new Pawn(1,1,WHITE);
//         sp.getChildren().add(p);
//         makeDraggable(p);

//         // root.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
//         // drawBoard(gc);
//         // start = new Point((int)e.getSceneX(), (int)e.getSceneY());
//         // });

//         // root.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
//         // drawBoard(gc);
//         // try{
//         // Thread.sleep(2);
//         // }catch(Exception ex){}
//         // gc.drawImage(new Bishop(10,10,BLACK).getImage(),
//         // e.getSceneX()-MOUSE_IMG_OFFSET, e.getSceneY()-MOUSE_IMG_OFFSET);
//         // });

//         // root.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
//         // drawBoard(gc);
//         // end = new Point((int)e.getSceneX(), (int)e.getSceneY());
//         // });

//         // root.getChildren().add(canvas);

//         // sp.getChildren().add(root);
//         stage.setScene(new Scene(sp));
//         stage.show();
//     }

//     
//     }

//     
// }