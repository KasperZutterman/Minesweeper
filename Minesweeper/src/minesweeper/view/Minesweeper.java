package minesweeper.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeper.model.Board;
import minesweeper.model.Status;

/**
 *
 * @author Kasper Zutterman
 */
public class Minesweeper extends Application implements EventHandler<MouseEvent> {

    private MyButton[][] mineField;
    private Board board;
    public static final int ROWS = 11;
    public static final int COLUMNS = 11;

    @Override
    public void start(final Stage primaryStage) {
        mineField = new MyButton[ROWS][COLUMNS];
        board = new Board(ROWS, COLUMNS, 10, this);
        BorderPane root = new BorderPane();
        GridPane rootBoard = new GridPane();
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField.length; j++) {
                mineField[i][j] = new MyButton(i, j);
                mineField[i][j].setOnMousePressed(this);
                Image image = new Image(getClass().getResourceAsStream("/Images/Minesz.gif"));
                mineField[i][j].setGraphic(new ImageView(image));
                rootBoard.add(mineField[i][j], j, i);
            }

        }
        
        rootBoard.setAlignment(Pos.CENTER);
        root.setTop(makeMenuBar());
        root.setCenter(rootBoard);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("minesweeper/view/minesweeperCSS.css");

        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

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
    
    public MenuBar makeMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Game");
        menuBar.getMenus().add(menu);
        
        MenuItem miNewGame = new MenuItem("New Game");
        
        MenuItem miStatistics = new MenuItem("Statistics");
        
        MenuItem miOptions = new MenuItem("Options");
        
        MenuItem miClose = new MenuItem("Close");
        miClose.setOnAction(e -> System.exit(0));
        
        menu.getItems().addAll(miNewGame, new SeparatorMenuItem(), miStatistics, miOptions, new SeparatorMenuItem(), miClose);
        return menuBar;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}