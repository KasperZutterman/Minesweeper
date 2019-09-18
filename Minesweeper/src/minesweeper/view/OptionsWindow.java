package minesweeper.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Kasper Zutterman
 */
public class OptionsWindow {
    
    public static void display(Window owner) {
        Stage optionsWindow = new Stage();

        optionsWindow.initModality(Modality.APPLICATION_MODAL);
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
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> {
            optionsWindow.close();
        });
        
        Button btnClose = new Button("Cancel");
        btnClose.setOnAction(e -> optionsWindow.close());
        
        VBox layout = new VBox(10);

        layout.getChildren().addAll(lblDifficulty, btnBeginner, btnIntermediate, btnAdvanced, btnCustom, btnOK, btnClose);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);

        optionsWindow.setScene(scene);
        optionsWindow.showAndWait();
    } 
}
