package fi.tuni.prog3.sisu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Lauri Puoskari
 */
public class SisuGUI extends Application {
    
    private final static int SPACING = 10;
    private final static int VGAP = 10;
    private final static int HGAP = 30;
    private final static int SHORTFIELDWIDTH = 150;
    private final static int LONGFIELDWIDTH = 330;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SISU");
        Tab studentInfoTab = new Tab("Opiskelijan tiedot");
        studentInfoTab.setClosable(false);
        TabPane tabPane = new TabPane(studentInfoTab);
        Scene scene = new Scene(tabPane, 400, 300);
        GridPane grid = new GridPane();
        studentInfoTab.setContent(grid);
        grid.setHgap(HGAP);
        grid.setVgap(VGAP);
        
        Label studentNameLabel = new Label("Opiskelijan nimi *");
        grid.add(studentNameLabel, 1, 1);
        TextField studentNameField = new TextField();
        studentNameField.setMaxWidth(LONGFIELDWIDTH);
        grid.add(studentNameField, 1, 2, 2, 1);
        Label studentNrLabel = new Label("Opiskelijanumero *");
        grid.add(studentNrLabel, 1, 3);
        TextField studentNrField = new TextField();
        studentNrField.setMaxWidth(LONGFIELDWIDTH);
        grid.add(studentNrField, 1, 4, 2, 1);
        grid.setPrefWidth(350);
        
        Label startYearLabel = new Label("Aloitusvuosi (vvvv)");
        grid.add(startYearLabel, 1, 5);
        TextField startYearField = new TextField();
        grid.add(startYearField, 1, 6);
        Label finYearLabel = new Label("Valmistumisvuosi (vvvv)");
        grid.add(finYearLabel, 2, 5);
        TextField finYearField = new TextField();
        grid.add(finYearField, 2, 6);
        
        Label infoLabel = new Label("");
        grid.add(infoLabel, 1, 7);
        Button contButton = new Button("Jatka");
        contButton.setPrefWidth(SHORTFIELDWIDTH);
        grid.add(contButton, 2, 7);
        
        stage.setScene(scene);
        stage.show();
        
        contButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                infoLabel.setText("");
                if (studentNameField.getText().isBlank() | 
                        studentNrField.getText().isBlank()) {
                    infoLabel.setText("Syötä pakolliset tiedot (*)");
                }
                else if (startYearField.getText().isBlank() &
                            finYearField.getText().isBlank()) {
                    // Kutsu Sisu-rakentajalle (ilman vuositietoja)
                    // Siirtyminen opintojen rakenne -näkymään
                }
                else {
                    if (startYearField.getText().isBlank() | 
                            !startYearField.getText().matches("\\d\\d\\d\\d")) {
                        infoLabel.setText("Tarkista aloitusvuosi");
                    }
                    else {
                        if (finYearField.getText().isBlank()) {
                            // Kutsu Sisu-rakentajalle (ilman valmistumisvuotta)
                            // Siirtyminen opintojen rakenne -näkymään
                        }
                        else {
                            if (!finYearField.getText().matches("\\d\\d\\d\\d")) {
                                infoLabel.setText("Tarkista valmistumisvuosi");
                            }
                            else if (Integer.parseInt(finYearField.getText()) 
                                 < Integer.parseInt(startYearField.getText())) {
                                infoLabel.setText("Tarkista valmistumisvuosi");
                            }
                            else {
                                // Kutsu Sisu-rakentajalle
                                // Siirtyminen opintojen rakenne -näkymään
                            }
                        }
                    }
                }    
            } 
        });
    }
}
