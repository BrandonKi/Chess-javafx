package run;

import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pieces.*;

// import java.awt.Dimension;
// import java.awt.Toolkit;

public class Main extends Application {

    public final static boolean WHITE = false;
    public final static boolean BLACK = true;
    //private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //Laptop
    // public static final int TILE_SIZE = (int)(screenSize.getWidth()/16);
    // private static final int HEIGHT = TILE_SIZE * 8 + (int)(screenSize.getHeight()/19);
    // private static final int WIDTH = TILE_SIZE * 8 + (int)(screenSize.getWidth()/99);
    // public static final short IMG_X_OFFSET = 10;
    // public static final short IMG_Y_OFFSET = 10;

    //Desktop
    // public static final int TILE_SIZE = (int)(screenSize.getWidth()/20); // 96
    // private static final int HEIGHT = TILE_SIZE * 8 + (int)(screenSize.getHeight()/30); // 804
    // private static final int WIDTH = TILE_SIZE * 8 + (int)(screenSize.getWidth()/150); // 780
    // public static final short IMG_X_OFFSET = 15;
    // public static final short IMG_Y_OFFSET = 15;

    //Everything Hopefully
    public static final byte TILE_SIZE = 70;
    public static final byte IMG_WIDTH = (byte)Math.round(TILE_SIZE * 0.85714285714);
    public static final byte IMG_HEIGHT = IMG_WIDTH;
    private static final short HEIGHT = TILE_SIZE * 8;
    private static final short WIDTH = TILE_SIZE * 8;
    public static final byte IMG_X_OFFSET = (byte)((TILE_SIZE - IMG_WIDTH)/2); // img size is 60 x 60
    public static final byte IMG_Y_OFFSET = (byte)((TILE_SIZE - IMG_HEIGHT)/2);



    private static Piece[][] currentBoard;
    private static Piece[][] initialBoard;
    public static Pane pane;
    public static Pane selFrameW, selFrameB;
    public static boolean turn = WHITE;

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        pane = new Pane();
        stage.getIcons().add(new Image("pieces\\resource\\PawnW.png"));
        stage.setTitle("Chess");
        // stage.setHeight(HEIGHT);
        // stage.setWidth(WIDTH);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(false);

        Canvas backCanvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gcBack = backCanvas.getGraphicsContext2D();
        initBoard(gcBack);
        root.getChildren().add(backCanvas);
        drawPieces(pane);
        root.getChildren().add(pane);
        selFrameW = initSelectionFrameW();
        selFrameB = initSelectionFrameB();
        root.getChildren().add(selFrameW);
        root.getChildren().add(selFrameB);
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
        System.out.println(stage.getHeight());

    }

    public static Piece createPiece(int x, int y, boolean color, String piece) {
        Piece temp = null;
        
        switch (piece) {
        case "Pawn":
            temp = new Pawn(x, y, color);
            break;
        case "Rook":
            temp = new Rook(x, y, color);
            break;
        case "Bishop":
            temp = new Bishop(x, y, color);
            break;
        case "Knight":
            temp = new Knight(x, y, color);
            break;
        case "King":
            temp = new King(x, y, color);
            break;
        case "Queen":
            temp = new Queen(x, y, color);
            break;
        case "PlaceHolder":
            temp = new PlaceHolder(x, y);
        }
        return temp;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initBoard(GraphicsContext gcBack) {
        initialBoard = new Piece[][] {
                { createPiece(0, 0, BLACK, "Rook"), createPiece(0, 1, BLACK, "Pawn"),
                        createPiece(0, 2, false, "PlaceHolder"), createPiece(0, 3, false, "PlaceHolder"),
                        createPiece(0, 4, false, "PlaceHolder"), createPiece(0, 5, false, "PlaceHolder"),
                        createPiece(0, 6, WHITE, "Pawn"), createPiece(0, 7, WHITE, "Rook") },
                { createPiece(1, 0, BLACK, "Knight"), createPiece(1, 1, BLACK, "Pawn"),
                        createPiece(1, 2, false, "PlaceHolder"), createPiece(1, 3, false, "PlaceHolder"),
                        createPiece(1, 4, false, "PlaceHolder"), createPiece(1, 5, false, "PlaceHolder"),
                        createPiece(1, 6, WHITE, "Pawn"), createPiece(1, 7, WHITE, "Knight") },
                { createPiece(2, 0, BLACK, "Bishop"), createPiece(2, 1, BLACK, "Pawn"),
                        createPiece(2, 2, false, "PlaceHolder"), createPiece(2, 3, false, "PlaceHolder"),
                        createPiece(2, 4, false, "PlaceHolder"), createPiece(2, 5, false, "PlaceHolder"),
                        createPiece(2, 6, WHITE, "Pawn"), createPiece(2, 7, WHITE, "Bishop") },
                { createPiece(3, 0, BLACK, "Queen"), createPiece(3, 1, BLACK, "Pawn"),
                        createPiece(3, 2, false, "PlaceHolder"), createPiece(3, 3, false, "PlaceHolder"),
                        createPiece(3, 4, false, "PlaceHolder"), createPiece(3, 5, false, "PlaceHolder"),
                        createPiece(3, 6, WHITE, "Pawn"), createPiece(3, 7, WHITE, "Queen") },
                { createPiece(4, 0, BLACK, "King"), createPiece(4, 1, BLACK, "Pawn"),
                        createPiece(4, 2, false, "PlaceHolder"), createPiece(4, 3, false, "PlaceHolder"),
                        createPiece(4, 4, false, "PlaceHolder"), createPiece(4, 5, false, "PlaceHolder"),
                        createPiece(4, 6, WHITE, "Pawn"), createPiece(4, 7, WHITE, "King") },
                { createPiece(5, 0, BLACK, "Bishop"), createPiece(5, 1, BLACK, "Pawn"),
                        createPiece(5, 2, false, "PlaceHolder"), createPiece(5, 3, false, "PlaceHolder"),
                        createPiece(5, 4, false, "PlaceHolder"), createPiece(5, 5, false, "PlaceHolder"),
                        createPiece(5, 6, WHITE, "Pawn"), createPiece(5, 7, WHITE, "Bishop") },
                { createPiece(6, 0, BLACK, "Knight"), createPiece(6, 1, BLACK, "Pawn"),
                        createPiece(6, 2, false, "PlaceHolder"), createPiece(6, 3, false, "PlaceHolder"),
                        createPiece(6, 4, false, "PlaceHolder"), createPiece(6, 5, false, "PlaceHolder"),
                        createPiece(6, 6, WHITE, "Pawn"), createPiece(6, 7, WHITE, "Knight") },
                { createPiece(7, 0, BLACK, "Rook"), createPiece(7, 1, BLACK, "Pawn"),
                        createPiece(7, 2, false, "PlaceHolder"), createPiece(7, 3, false, "PlaceHolder"),
                        createPiece(7, 4, false, "PlaceHolder"), createPiece(7, 5, false, "PlaceHolder"),
                        createPiece(7, 6, WHITE, "Pawn"), createPiece(7, 7, WHITE, "Rook") } };
        currentBoard = Arrays.copyOf(initialBoard, initialBoard.length);
        for (short i = 0; i < 8; i++) {
            for (short x = 0; x < 8; x++) {
                if ((i % 2 == 0 && x % 2 != 0) || (i % 2 != 0 && x % 2 == 0))
                    gcBack.setFill(Color.GRAY);
                else
                    gcBack.setFill(Color.WHITE);
                gcBack.fillRect((x * TILE_SIZE), (i * TILE_SIZE), TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawPieces(Pane p) {
        for (short i = 0; i < 8; i++) {
            for (short x = 0; x < 8; x++) {
                if (!currentBoard[i][x].isPlaceHolder()) {
                    currentBoard[i][x].relocate(Main.IMG_X_OFFSET + i * TILE_SIZE, Main.IMG_Y_OFFSET + x * TILE_SIZE);
                    currentBoard[i][x].setVisible(true);
                    p.getChildren().add(currentBoard[i][x]);
                }
            }
        }
    }

    public static Piece[][] getBoard() {
        return currentBoard;
    }

    public static void printBoard() {
        for (short i = 0; i < 8; i++) {
            for (short x = 0; x < 8; x++) {
                System.out.print((currentBoard[x][i].isPlaceHolder() ? "Empty "
                        : currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ') + 1)
                                .equals("Pawn")
                                        ? currentBoard[x][i].toString()
                                                .substring(currentBoard[x][i].toString().indexOf(' ') + 1) + "  "
                                        : currentBoard[x][i].toString()
                                                .substring(currentBoard[x][i].toString().indexOf(' ') + 1).length() == 6
                                                        ? currentBoard[x][i].toString().substring(
                                                                currentBoard[x][i].toString().indexOf(' ') + 1)
                                                        : currentBoard[x][i].toString()
                                                                .substring(
                                                                        currentBoard[x][i].toString().indexOf(' ') + 1)
                                                                .length() == 5
                                                                        ? currentBoard[x][i].toString()
                                                                                .substring(currentBoard[x][i].toString()
                                                                                        .indexOf(' ') + 1)
                                                                                + " "
                                                                        : currentBoard[x][i].toString()
                                                                                .substring(currentBoard[x][i].toString()
                                                                                        .indexOf(' ') + 1)
                                                                                + "  ")
                        + " ");
            }
            System.out.println();
        }
    }

    public static Pane initSelectionFrameW() {
        Pane p = new Pane();
        HBox root = new HBox();
        VBox sel1 = new VBox();
        VBox sel2 = new VBox();
        VBox sel3 = new VBox();
        VBox sel4 = new VBox();
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pawnPro = event.getSource().toString().substring(event.getSource().toString().indexOf("'") + 1,
                        event.getSource().toString().length() - 1) + "W";
                        Piece.promote(pawnPro);
                p.setVisible(false);
                event.consume();
            }
        };
        sel1.setSpacing(10);
        sel1.setPrefWidth(WIDTH / 16);
        sel2.setSpacing(10);
        sel2.setPrefWidth(WIDTH / 16);
        sel3.setSpacing(10);
        sel3.setPrefWidth(WIDTH / 16);
        sel4.setSpacing(10);
        sel4.setPrefWidth(WIDTH / 16);
        sel1.getChildren().add(new ImageView(new Image("pieces\\resource\\QueenW.png")));
        Button b1 = new Button("Queen");
        b1.setPrefWidth(WIDTH / 10);
        b1.setOnAction(buttonHandler);
        sel1.getChildren().add(b1);
        sel2.getChildren().add(new ImageView(new Image("pieces\\resource\\RookW.png")));
        Button b2 = new Button("Rook");
        b2.setPrefWidth(WIDTH / 10);
        b2.setOnAction(buttonHandler);
        sel2.getChildren().add(b2);
        sel3.getChildren().add(new ImageView(new Image("pieces\\resource\\KnightW.png")));
        Button b3 = new Button("Knight");
        b3.setPrefWidth(WIDTH / 10);
        b3.setOnAction(buttonHandler);
        sel3.getChildren().add(b3);
        sel4.getChildren().add(new ImageView(new Image("pieces\\resource\\BishopW.png")));
        Button b4 = new Button("Bishop");
        b4.setPrefWidth(WIDTH / 10);
        b4.setOnAction(buttonHandler);
        sel4.getChildren().add(b4);
        root.setBackground(
                new Background(new BackgroundFill(new Color(0.25, 0.25, 0.25, .5), new CornerRadii(20), Insets.EMPTY)));
        root.getChildren().addAll(sel1, sel2, sel3, sel4);
        root.setSpacing(5);
        root.setPrefSize(WIDTH / 4, HEIGHT / 7);
        p.getChildren().add(root);
        p.setVisible(false);
        root.relocate(HEIGHT / 2 - WIDTH / 5, WIDTH / 2 - HEIGHT / 7);
        return p;
    }

    public static Pane initSelectionFrameB() {
        Pane p = new Pane();
        HBox root = new HBox();
        VBox sel1 = new VBox();
        VBox sel2 = new VBox();
        VBox sel3 = new VBox();
        VBox sel4 = new VBox();
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pawnPro = event.getSource().toString().substring(event.getSource().toString().indexOf("'") + 1,
                        event.getSource().toString().length() - 1)  + "B";
                        Piece.promote(pawnPro);
                p.setVisible(false);
                event.consume();
            }
        };
        sel1.setSpacing(10);
        sel1.setPrefWidth(WIDTH / 5);
        sel2.setSpacing(10);
        sel2.setPrefWidth(WIDTH / 5);
        sel3.setSpacing(10);
        sel3.setPrefWidth(WIDTH / 5);
        sel4.setSpacing(10);
        sel4.setPrefWidth(WIDTH / 5);
        sel1.getChildren().add(new ImageView(new Image("pieces\\resource\\QueenB.png")));
        Button b1 = new Button("Queen");
        b1.setPrefWidth(WIDTH / 5);
        b1.setOnAction(buttonHandler);
        sel1.getChildren().add(b1);
        sel2.getChildren().add(new ImageView(new Image("pieces\\resource\\RookB.png")));
        Button b2 = new Button("Rook");
        b2.setPrefWidth(WIDTH / 5);
        b2.setOnAction(buttonHandler);
        sel2.getChildren().add(b2);
        sel3.getChildren().add(new ImageView(new Image("pieces\\resource\\KnightB.png")));
        Button b3 = new Button("Knight");
        b3.setPrefWidth(WIDTH / 5);
        b3.setOnAction(buttonHandler);
        sel3.getChildren().add(b3);
        sel4.getChildren().add(new ImageView(new Image("pieces\\resource\\BishopB.png")));
        Button b4 = new Button("Bishop");
        b4.setPrefWidth(WIDTH / 5);
        b4.setOnAction(buttonHandler);
        sel4.getChildren().add(b4);
        root.setBackground(
                new Background(new BackgroundFill(new Color(0.25, 0.25, 0.25, .5), new CornerRadii(20), Insets.EMPTY)));
        root.getChildren().addAll(sel1, sel2, sel3, sel4);
        root.setSpacing(5);
        root.setPrefSize(WIDTH / 4, HEIGHT / 7);
        p.getChildren().add(root);
        p.setVisible(false);
        root.relocate(HEIGHT / 2 - WIDTH / 5, WIDTH / 2 - HEIGHT / 7);
        return p;
    }

    public static void showSelectionFrame(boolean color) {
        if (color)
            selFrameB.setVisible(true);
        else
            selFrameW.setVisible(true);
    }
}