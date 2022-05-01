package fi.tuni.prog3.sisu;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Lauri Puoskari
 */
public class SisuGUI extends Application {

    private final static int VGAP = 10;
    private final static int HGAP = 30;
    private final static int SHORT_FIELD_WIDTH = 150;
    private final static int LONG_FIELD_WIDTH = 330;
    private final static int INFO_WINDOW_WIDTH = 400;
    private final static int INFO_WINDOW_HEIGHT = 300;
    private final static int STUDY_VIEW_WINDOW_WIDTH = 800;
    private final static int STUDY_VIEW_WINDOW_HEIGHT = 650;
    private final static int STUDY_VIEW_WINDOW_X = 250;
    private final static int STUDY_VIEW_WINDOW_Y = 0;
    private final static int COURSE_LIST_HEIGHT = 400;
    private final static int COMPLETED_LIST_HEIGHT = 435;
    private static Sisu activeSisu;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SISU");
        Tab studentInfoTab = new Tab("Opiskelijan tiedot");
        studentInfoTab.setClosable(false);
        TabPane tabPane = new TabPane(studentInfoTab);
        Scene scene = new Scene(tabPane, INFO_WINDOW_WIDTH, INFO_WINDOW_HEIGHT);
        GridPane grid = new GridPane();
        studentInfoTab.setContent(grid);
        grid.setHgap(HGAP);
        grid.setVgap(VGAP);
        
        Label studentNameLabel = new Label("Opiskelijan nimi (A-Z) *");
        grid.add(studentNameLabel, 1, 1);
        TextField studentNameField = new TextField();
        studentNameField.setMaxWidth(LONG_FIELD_WIDTH);
        grid.add(studentNameField, 1, 2, 2, 1);
        Label studentNrLabel = new Label("Opiskelijanumero *");
        grid.add(studentNrLabel, 1, 3);
        TextField studentNrField = new TextField();
        studentNrField.setMaxWidth(LONG_FIELD_WIDTH);
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
        Button continueButton = new Button("Jatka");
        continueButton.setPrefWidth(SHORT_FIELD_WIDTH);
        grid.add(continueButton, 2, 7);
        
        stage.setScene(scene);
        stage.show();
        
        
        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                infoLabel.setText("");
                if (studentNameField.getText().isBlank() | 
                        studentNrField.getText().isBlank()) {
                    infoLabel.setText("Syötä pakolliset tiedot (*)");
                }
                else if (startYearField.getText().isBlank() &
                            finYearField.getText().isBlank()) {
                    try {
                        activeSisu = new Sisu(studentNameField.getText(),
                                studentNrField.getText());
                        startStudyView(stage, scene, tabPane);
                    } catch (IOException ex) {
                        Logger.getLogger(SisuGUI.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
                else {
                    if (startYearField.getText().isBlank() | 
                            !startYearField.getText().matches("\\d\\d\\d\\d")) {
                        infoLabel.setText("Tarkista aloitusvuosi");
                    }
                    else {
                        if (finYearField.getText().isBlank()) {
                            try {
                                activeSisu = new Sisu(studentNameField.getText(),
                                        studentNrField.getText(),
                                        startYearField.getText());
                            } catch (IOException ex) {
                                Logger.getLogger(SisuGUI.class.getName()).log(
                                        Level.SEVERE, null, ex);
                            }
                            startStudyView(stage, scene, tabPane);
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
                                try {
                                    activeSisu = new Sisu(studentNameField.getText(), 
                                            studentNrField.getText(),
                                            startYearField.getText(),
                                            finYearField.getText());
                                } catch (IOException ex) {
                                    Logger.getLogger(SisuGUI.class.getName()).
                                            log(Level.SEVERE, null, ex);
                                }
                                startStudyView(stage, scene, tabPane);
                                continueButton.setDisable(true);
                            }
                        }
                    }
                }    
            } 
        });
    }
    
    public void startStudyView(Stage stage, Scene scene, TabPane tabPane)
    {   
        double studyViewWindowX = scene.getWindow().getX() - 
                (STUDY_VIEW_WINDOW_WIDTH  - INFO_WINDOW_WIDTH) / 2;
        
        Tab studyViewTab = new Tab("Opintojen rakenne");
        tabPane.getTabs().add(studyViewTab);
        tabPane.getSelectionModel().select(studyViewTab);
        
        GridPane studyViewGrid = new GridPane();
        studyViewGrid.setVgap(VGAP);
        studyViewGrid.setHgap(HGAP);
        
        Label titleLabel = new Label("Opintojen rakenne - " 
                + activeSisu.getActiveStudent().getName());
        titleLabel.setFont(new Font(20));
        studyViewGrid.add(titleLabel, 1, 1, 5, 1);
        
        Label degreeLabel = new Label("Valitse tutkinto-ohjelma");
        studyViewGrid.add(degreeLabel, 1, 2);
        Label fieldLabel = new Label("Valitse opintosuunta");
        studyViewGrid.add(fieldLabel, 2, 2);

        ObservableList<String> degrees = FXCollections.
                observableArrayList(activeSisu.getDegrees());
        
        ChoiceBox degreeChoiceBox = new ChoiceBox(degrees);
        degreeChoiceBox.setPrefWidth(SHORT_FIELD_WIDTH);
        
        studyViewGrid.add(degreeChoiceBox, 1, 3);
        ChoiceBox fieldChoiceBox = new ChoiceBox();
        fieldChoiceBox.setPrefWidth(SHORT_FIELD_WIDTH);
        studyViewGrid.add(fieldChoiceBox, 2, 3);
        
        TreeView courseTreeView = new TreeView();
        studyViewGrid.add(courseTreeView, 1, 4, 2, 1);
        courseTreeView.setPrefHeight(COURSE_LIST_HEIGHT);
        
        Button confirmButton = new Button("Vahvista valinnat");
        confirmButton.setPrefWidth(SHORT_FIELD_WIDTH);
        studyViewGrid.add(confirmButton, 2, 5);
        
        Label completionsTitle = new Label("Suoritukset");
        completionsTitle.setFont(new Font(14));
        completionsTitle.setPrefWidth(SHORT_FIELD_WIDTH);
        studyViewGrid.add(completionsTitle, 4, 2, 1, 1);
        
        ListView completionsView = new ListView();
        completionsView.setPrefSize(LONG_FIELD_WIDTH, COMPLETED_LIST_HEIGHT);
        studyViewGrid.add(completionsView, 4, 3, 2, 2);
        
        Button saveButton = new Button("Tallenna ja sulje");
        saveButton.setPrefWidth(SHORT_FIELD_WIDTH);
        studyViewGrid.add(saveButton, 5, 5);
        
        studyViewTab.setContent(studyViewGrid);
        scene.getWindow().setWidth(STUDY_VIEW_WINDOW_WIDTH);
        scene.getWindow().setHeight(STUDY_VIEW_WINDOW_HEIGHT);
        scene.getWindow().setX(studyViewWindowX);
        scene.getWindow().setY(STUDY_VIEW_WINDOW_Y);
        
        degreeChoiceBox.setOnHidden(new EventHandler<>(){
            @Override
            public void handle(Event t) {
//                if (courseTreeView.getRoot() != null) {
//                    if (!courseTreeView.getRoot().getChildren().isEmpty()) {
//                        for (int i = 0; i < courseTreeView.getRoot().
//                                                getChildren().size() ; i++) {
//                            courseTreeView.getRoot().getChildren().remove(i);
//                        }
//                    }
//                }
//                
//                TreeItem<String> root = new TreeItem<>("Root Node");
//                courseTreeView.setShowRoot(false);
//                courseTreeView.setRoot(root);
//                
//                if (degreeChoiceBox.getSelectionModel().getSelectedItem() != null) {
//                    String selectedDegree = (String) degreeChoiceBox.
//                                        getSelectionModel().getSelectedItem();
//                    activeSisu.setSelectedDegree(selectedDegree);
//                    try {
//                        activeSisu.setModules();
//                    } catch (IOException ex) {
//                        Logger.getLogger(SisuGUI.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    root.setExpanded(true);
//                    for (int i = 0; i < activeSisu.getModules().size(); i++) {
//                        TreeItem module = new TreeItem<>(activeSisu.getModules().get(i));
//                        root.getChildren().add(module);
//                    }
//                }
                if (!fieldChoiceBox.getItems().isEmpty()) {
                    fieldChoiceBox.getSelectionModel().clearSelection();
                    fieldChoiceBox.getItems().clear();
                }
                if (degreeChoiceBox.getSelectionModel().getSelectedItem() != null) {
                    String selectedDegree = (String) degreeChoiceBox.
                                        getSelectionModel().getSelectedItem();
                    activeSisu.setSelectedDegree(selectedDegree);
                    try {
                        activeSisu.setModules();
                    } catch (IOException ex) {
                        Logger.getLogger(SisuGUI.class.getName()).log(
                                                        Level.SEVERE, null, ex);
                    }
                    if (activeSisu.getSelectedDegree().getStudyFields().
                                                                    isEmpty()) {
                        try {
                            for (String module : activeSisu.getSelectedDegree().
                                                        getModules().keySet()) {
                                populateTreeView(courseTreeView, 
                                    activeSisu.getSelectedDegree().getModules().
                                    get(module));   
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(SisuGUI.class.getName()).log(
                                                        Level.SEVERE, null, ex);
                        }
                    }
                    else {
                        ObservableList<String> studyFields = FXCollections.
                            observableArrayList(activeSisu.getSelectedDegree().
                            getStudyFields().keySet());
                        fieldChoiceBox.getItems().addAll(studyFields);
//                        for (int i = 0; i < studyFields.size(); i++) {
//                            
//                            fieldChoiceBox.getItems().add(studyFields.get(i));
//                        }
                    }
                }

            }
        });
        
        fieldChoiceBox.setOnHidden(new EventHandler<>(){
            @Override
            public void handle(Event t) {
                if (fieldChoiceBox.getSelectionModel().getSelectedItem() != null) {
                    String selectedField = (String) fieldChoiceBox.
                                        getSelectionModel().getSelectedItem();
                    activeSisu.getSelectedDegree().setSelectedField(selectedField);
                    try {
                        activeSisu.setModules();
                    } catch (IOException ex) {
                        Logger.getLogger(SisuGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        populateTreeView(courseTreeView, 
                                activeSisu.getSelectedDegree().getStudyFields().
                                get((String) fieldChoiceBox.getSelectionModel().
                                getSelectedItem()));
                    } catch (IOException ex) {
                        Logger.getLogger(SisuGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // TODO: Vahvista valinnat ja anna ne Sisu-luokalle.
                
                activeSisu.getActiveStudent().setActiveDegree(
                                                activeSisu.getSelectedDegree());
                activeSisu.getActiveStudent().setActiveStudyField(
                            activeSisu.getSelectedDegree().getSelectedField());
//                for (int i = 0; i < completionsView.getItems().size(); i++) {
//                    completionsView.getItems().remove(i);
//                }
                if (!completionsView.getItems().isEmpty()) {
                    completionsView.getSelectionModel().clearSelection();
                    completionsView.getItems().clear();
                }
                completionsView.getItems().addAll(initCourseCheckBoxes());
            }
        });
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    activeSisu.saveStudentData();
                } catch (IOException ex) {
                    Logger.getLogger(SisuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                Dialog saveDialog = new Dialog();
                saveDialog.setTitle("HUOM!");
                DialogPane dialogPane = new DialogPane();
                dialogPane.getButtonTypes().add(
                            new ButtonType("OK", 
                                    ButtonBar.ButtonData.OK_DONE));
                if (activeSisu.getIsSuccessfullySaved()) {
                    dialogPane.setHeaderText("Tietojen tallennus onnistui.");
                }
                else {
                    dialogPane.setHeaderText("Tietojen tallennus epäonnistui. "
                            + "Yritä uudelleen.");
                }
                saveDialog.setDialogPane(dialogPane);
                saveDialog.show();
                
                saveDialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        if (activeSisu.getIsSuccessfullySaved()) {
                            closeTab(studyViewTab);
                            stage.close();
                        }
                    }
                });
            }
        });
    }
    
    public ObservableList<CheckBox> initCourseCheckBoxes() {
        if (!activeSisu.getSelectedDegree().equals(activeSisu.getActiveStudent().
                getActiveDegree()) | !activeSisu.getSelectedDegree().
                getSelectedField().equals(activeSisu.getActiveStudent().
                getActiveStudyField())) {
            activeSisu.getActiveStudent().clearCompletions();
        }
        activeSisu.getActiveStudent().clearCompletions();
        ObservableList<CheckBox> checkBoxes = FXCollections
                                                        .observableArrayList();
        ArrayList<String> courses = new ArrayList<>(
                                    activeSisu.getAllSubCourses().keySet());
        for (var course : courses) {
            CheckBox checkBox = new CheckBox(course);
            checkBox.setOnAction(new EventHandler<>(){
                @Override
                public void handle(ActionEvent t) {
                    String courseStr = checkBox.getText();
                    TreeMap<String, Course> courses = 
                                        activeSisu.getAllSubCourses();
                    if (checkBox.isSelected()) {
                        if (courses.containsKey(courseStr)) {
                            activeSisu.getActiveStudent().
                                        completeCourse(courses.get(courseStr));
                        }
                    }
                    else {
                        activeSisu.getActiveStudent().
                                           removeCourse(courses.get(courseStr));
                    }
                }
            });
            checkBoxes.add(checkBox);
        }
        return checkBoxes;
    }
    
    private void populateTreeView(TreeView treeView, Module module) throws IOException {
        activeSisu.clearAllSubCourses();
        TreeItem<String> root = new TreeItem<>("Root Node");
        treeView.setShowRoot(false);
        treeView.setRoot(root);
        if (!treeView.getRoot().getChildren().isEmpty()) {
            for (int i = 0; i < treeView.getRoot().getChildren().size(); i++) {
                treeView.getRoot().getChildren().remove(i);
            }
        }
        TreeItem<String> content = activeSisu.getModuleContent(module);
        root.getChildren().add(content);
    }
    
    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }
    }
}
