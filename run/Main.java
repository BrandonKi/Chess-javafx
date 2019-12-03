package run;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pieces.*;
import point.Point;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Main extends Application {

    public final static boolean WHITE = false;
    public final static boolean BLACK = true;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int TILE_SIZE = (int)(screenSize.getWidth()/16);
    private static final int HEIGHT = TILE_SIZE * 8 + (int)(screenSize.getHeight()/19);
    private static final int WIDTH = TILE_SIZE * 8 + (int)(screenSize.getWidth()/99);
    private static Piece[][] currentBoard;
    private static Piece[][] initialBoard;
    public static Pane pane;
    public static boolean turn = WHITE;

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        pane = new Pane();
        stage.setTitle("Chess");
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);

        Canvas backCanvas = new Canvas(stage.getWidth(), stage.getHeight());
        GraphicsContext gcBack = backCanvas.getGraphicsContext2D();
        initBoard(gcBack);
        root.getChildren().add(backCanvas);
        drawPieces(pane);
        root.getChildren().add(pane);

        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }

    private Piece createPiece(int x, int y, boolean color, String piece) {
        Piece temp = null;
        ;
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
            { createPiece(0, 0, BLACK, "Rook"), createPiece(0, 1, BLACK, "Pawn"), createPiece(0, 2, false, "PlaceHolder"), createPiece(0, 3, false, "PlaceHolder"), createPiece(0, 4, false, "PlaceHolder"), createPiece(0, 5, false, "PlaceHolder"), createPiece(0, 6, WHITE, "Pawn"), createPiece(0, 7, WHITE, "Rook") },
            { createPiece(1, 0, BLACK, "Knight"), createPiece(1, 1, BLACK, "Pawn"), createPiece(1, 2, false, "PlaceHolder"), createPiece(1, 3, false, "PlaceHolder"), createPiece(1, 4, false, "PlaceHolder"), createPiece(1, 5, false, "PlaceHolder"), createPiece(1, 6, WHITE, "Pawn"), createPiece(1, 7, WHITE, "Knight") },
            { createPiece(2, 0, BLACK, "Bishop"), createPiece(2, 1, BLACK, "Pawn"), createPiece(2, 2, false, "PlaceHolder"), createPiece(2, 3, false, "PlaceHolder"), createPiece(2, 4, false, "PlaceHolder"), createPiece(2, 5, false, "PlaceHolder"), createPiece(2, 6, WHITE, "Pawn"), createPiece(2, 7, WHITE, "Bishop") },
            { createPiece(3, 0, BLACK, "Queen"), createPiece(3, 1, BLACK, "Pawn"), createPiece(3, 2, false, "PlaceHolder"), createPiece(3, 3, false, "PlaceHolder"), createPiece(3, 4, false, "PlaceHolder"), createPiece(3, 5, false, "PlaceHolder"), createPiece(3, 6, WHITE, "Pawn"), createPiece(3, 7, WHITE, "Queen")},
            { createPiece(4, 0, BLACK, "King"), createPiece(4, 1, BLACK, "Pawn"), createPiece(4, 2, false, "PlaceHolder"), createPiece(4, 3, false, "PlaceHolder"), createPiece(4, 4, false, "PlaceHolder"), createPiece(4, 5, false, "PlaceHolder"), createPiece(4, 6, WHITE, "Pawn"), createPiece(4, 7, WHITE, "King")},
            { createPiece(5, 0, BLACK, "Bishop"), createPiece(5, 1, BLACK, "Pawn"), createPiece(5, 2, false, "PlaceHolder"), createPiece(5, 3, false, "PlaceHolder"), createPiece(5, 4, false, "PlaceHolder"), createPiece(5, 5, false, "PlaceHolder"), createPiece(5, 6, WHITE, "Pawn"), createPiece(5, 7, WHITE, "Bishop")},
            { createPiece(6, 0, BLACK, "Knight"), createPiece(6, 1, BLACK, "Pawn"), createPiece(6, 2, false, "PlaceHolder"), createPiece(6, 3, false, "PlaceHolder"), createPiece(6, 4, false, "PlaceHolder"), createPiece(6, 5, false, "PlaceHolder"), createPiece(6, 6, WHITE, "Pawn"), createPiece(6, 7, WHITE, "Knight")},
            { createPiece(7, 0, BLACK, "Rook"), createPiece(7, 1, BLACK, "Pawn"), createPiece(7, 2, false, "PlaceHolder"), createPiece(7, 3, false, "PlaceHolder"), createPiece(7, 4, false, "PlaceHolder"), createPiece(7, 5, false, "PlaceHolder"), createPiece(7, 6, WHITE, "Pawn"), createPiece(7, 7, WHITE, "Rook")}
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

    public static void drawPieces(Pane p) {
        for (short i = 0; i < 8; i++) {
            for (short x = 0; x < 8; x++) {
                if (!currentBoard[i][x].isPlaceHolder()) {
                    currentBoard[i][x].relocate(Piece.IMG_X_OFFSET + i * TILE_SIZE, Piece.IMG_Y_OFFSET + x * TILE_SIZE);
                    p.getChildren().add(currentBoard[i][x]);
                }
            }
        }
    }

    public static Piece[][] getBoard() {
        return currentBoard;
    }

    public static void printBoard() {
        for (short i = 0; i < 8; i++){
            for (short x = 0; x < 8; x++){
                System.out.print((currentBoard[x][i].isPlaceHolder() ? "Empty " : currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1).equals("Pawn") ? currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1) + "  " : currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1).length() == 6 ? currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1) : currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1).length() == 5 ? currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1) + " " : currentBoard[x][i].toString().substring(currentBoard[x][i].toString().indexOf(' ')+1) + "  ") + " ");
            }
            System.out.println();
        }
    }
}