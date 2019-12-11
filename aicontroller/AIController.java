package aicontroller;

import java.util.ArrayList;

import pieces.Piece;
import point.Point;
import run.Main;

public class AIController{

    private ArrayList<Piece> pieces;
    private Piece[][] board;

    public AIController(){
        pieces = new ArrayList<Piece>();
    }

    public void start(){
        board = Main.getBoard();
        updateAvailiblePieces();
        getAvailableMoves();

    }

    private void updateAvailiblePieces(){
        for(int i = 0; i < 8; i++){
            for(int x = 0; x < 8; x++){
                if(board[i][x].getColor() == Main.BLACK)
                    pieces.add(board[i][x]);
            }
        }
    }

    private void getAvailableMoves(){
        for(Piece p: pieces){
            
        }
    }

}