package minesweeper.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Kasper Zutterman
 */
public class OptionsWindow {
    
    public static void display(Window owner, Minesweeper parent) {
        Stage optionsWindow = new Stage();

        optionsWindow.initModality(Modality.WINDOW_MODAL);
        optionsWindow.initOwner(owner);
        optionsWindow.setTitle("Options");

        Label lblDifficulty = new Label("Difficulty");

        ToggleGroup tgDifficulty = new ToggleGroup();
        
        RadioButton btnBeginner = new RadioButton("Beginner\n10 mines\n9 x 9 tile grid");
        btnBeginner.setUserData("Beginner");
        btnBeginner.setToggleGroup(tgDifficulty);
        btnBeginner.setSelected(true);
        
        RadioButton btnIntermediate = new RadioButton("Intermediate\n40 mines\n16 x 16 tile grid");
        btnIntermediate.setUserData("Intermediate");
        btnIntermediate.setToggleGroup(tgDifficulty);
        
        RadioButton btnAdvanced = new RadioButton("Advanced\n99 mines\n16 x 30 tile grid");
        btnAdvanced.setUserData("Advanced");
        btnAdvanced.setToggleGroup(tgDifficulty);
        
        RadioButton btnCustom = new RadioButton("Custom");
        btnCustom.setUserData("Custom");
        btnCustom.setToggleGroup(tgDifficulty);
        TextField txtMines = new TextField("10");
        TextField txtColumns = new TextField("9");
        TextField txtRows = new TextField("9");
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> {
            int mines;
            int rows;
            int columns;
            
            if (tgDifficulty.getSelectedToggle().getUserData() == "Beginner") {
                mines = 10;
                rows = 9;
                columns = 9;
            }
            else if (tgDifficulty.getSelectedToggle().getUserData() == "Intermediate") {
                mines = 40;
                rows = 16;
                columns = 16;
            }
            else if (tgDifficulty.getSelectedToggle().getUserData() == "Advanced") {
                mines = 40;
                rows = 16;
                columns = 16;
            }
            else {
                mines = Integer.parseInt(txtMines.getText());
                rows = Integer.parseInt(txtRows.getText());
                columns = Integer.parseInt(txtColumns.getText());
            }
            parent.setParameters(mines, columns, rows);
            optionsWindow.close();
        });
        
        Button btnClose = new Button("Cancel");
        btnClose.setOnAction(e -> optionsWindow.close());
        
        VBox setDifficulty = new VBox();
        setDifficulty.getChildren().addAll(btnBeginner, btnIntermediate, btnAdvanced);
        
        GridPane customDifficulty = new GridPane();
        customDifficulty.add(btnCustom, 0, 0);
        customDifficulty.add(new Label("Height (9 - 24):"), 0, 1);
        customDifficulty.add(txtRows, 1, 1);
        customDifficulty.add(new Label("Width (9 - 30):"), 0, 2);
        customDifficulty.add(txtColumns, 1, 2);
        customDifficulty.add(new Label("Height (10 - 668):"), 0, 3);
        customDifficulty.add(txtMines, 1, 3);
        
        HBox difficulties = new HBox();
        difficulties.getChildren().addAll(setDifficulty, customDifficulty);

        FlowPane buttons = new FlowPane();
        buttons.getChildren().addAll(btnOK, btnClose);
        buttons.setAlignment(Pos.TOP_RIGHT); 
        
        VBox layout = new VBox();
        layout.getChildren().addAll(lblDifficulty, difficulties, buttons);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);

        optionsWindow.setScene(scene);
        optionsWindow.showAndWait();
    } 
}
