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
public class Minesweeper extends Application {//implements EventHandler<MouseEvent> {

    private MyButton[][] mineField;
    private Board board;
    private BoardView boardView;
    public static final int ROWS = 11;
    public static final int COLUMNS = 11;
    private Stage primaryStage;
    private OptionsWindow optionsWindow;

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        optionsWindow = new OptionsWindow();
        primaryStage.setTitle("Minesweeper");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        newGame(primaryStage);
    }

    public void updateButtons() {
        boardView.updateButtons();
    }

    public void gameOver() {
        boardView.gameOver();
    }
    
    public MenuBar makeMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Game");
        menuBar.getMenus().add(menu);
        
        MenuItem miNewGame = new MenuItem("New Game");
        miNewGame.setOnAction(e -> newGame(primaryStage));
        
        MenuItem miStatistics = new MenuItem("Statistics");
        
        MenuItem miOptions = new MenuItem("Options");
        miOptions.setOnAction(e -> optionsWindow.display(primaryStage));
        
        MenuItem miClose = new MenuItem("Close");
        miClose.setOnAction(e -> System.exit(0));
        
        menu.getItems().addAll(miNewGame, new SeparatorMenuItem(), miStatistics, miOptions, new SeparatorMenuItem(), miClose);
        return menuBar;
    }

    public void newGame(final Stage primaryStage) {
        mineField = new MyButton[ROWS][COLUMNS];
        board = new Board(ROWS, COLUMNS, 10, this);
        boardView = new BoardView(ROWS, COLUMNS, board);
        BorderPane root = new BorderPane();
        
        boardView.setAlignment(Pos.CENTER);
        
        root.setTop(makeMenuBar());
        root.setCenter(boardView);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("minesweeper/view/minesweeperCSS.css");

       
        primaryStage.setScene(scene);
        primaryStage.show();
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