package minesweeper.view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import minesweeper.model.Board;
import minesweeper.model.Status;

/**
 *
 * @author Kasper Zutterman
 */
public class BoardView extends GridPane implements EventHandler<MouseEvent>{
    
    private MyButton[][] mineField;
    private Board board;
    
    public BoardView(int rows, int columns, Board board) {
        mineField = new MyButton[rows][columns];
        this.board = board;
        
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[0].length; j++) {
                mineField[i][j] = new MyButton(i, j);
                mineField[i][j].setOnMousePressed(this);
                Image image = new Image(getClass().getResourceAsStream("/Images/Minesz.gif"));
                mineField[i][j].setGraphic(new ImageView(image));
                add(mineField[i][j], j, i);
            }
        }
    }
    
    @Override
    public void handle(MouseEvent t) {
        MyButton button = (MyButton) t.getSource();
        int row = button.getRow();
        int column = button.getColumn();

        if (t.getButton().equals(MouseButton.PRIMARY) && !board.isTileMarked(row, column)) {
            //leftMouseButtonPressed(row, column);
            board.handleLeftClick(row, column);
        }
        if (t.getButton().equals(MouseButton.SECONDARY)) {
            
            board.handleRightClick(row, column);
        }

    }
    
    public void updateButtons() {
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[0].length; j++) {
                Status state = board.getTileStatus(i, j);
                if (state == Status.CLICKED) {
                    Image image = new Image(getClass().getResourceAsStream("/Images/Mines" + board.getNeighbourCount(i, j) + ".gif"));
                    mineField[i][j].setGraphic(new ImageView(image));
                } else if (board.isTileMarked(i, j)) {
                    Image image = new Image(getClass().getResourceAsStream("/Images/Minesf.gif"));
                    mineField[i][j].setGraphic(new ImageView(image));
                }
            }
        }

    }

    public void gameOver() {
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                mineField[i][j].setOnMousePressed(null);
                if (board.getTileStatus(i, j) == Status.BOMB) {
                    Image image = new Image(getClass().getResourceAsStream("/Images/Minesb.gif"));
                    mineField[i][j].setGraphic(new ImageView(image));
                } else if (board.getTileStatus(i, j) == Status.EXPLODED) {
                    Image image = new Image(getClass().getResourceAsStream("/Images/Minesn.gif"));
                    mineField[i][j].setGraphic(new ImageView(image));
                }
            }

        }
    }
}
