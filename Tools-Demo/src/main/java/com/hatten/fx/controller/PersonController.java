package com.hatten.fx.controller;

import com.hatten.fx.MainApp;
import com.hatten.fx.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

/**
 * @Author: jerrylee
 * @Date: 2020/7/16 1:24 下午
 * @Desc: javaFX测试controller
 */
public class PersonController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;

    public PersonController() {
    }

    private ObservableList<Person> plist = FXCollections.observableArrayList();

    private ObservableList getPersonList() {
        plist.add(new Person("tony", "zhang"));
        plist.add(new Person("tony", "ma"));
        plist.add(new Person("jack", "liu"));
        plist.add(new Person("peter", "lee"));
        plist.add(new Person("jenny", "gui"));
        return plist;
    }

    private void showPersonDetail(Person p) {
        if (p != null) {
            firstNameLabel.setText(p.getFirstName());
            lastNameLabel.setText(p.getLastName());
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        //添加表格改变监听
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetail(newValue)
        );
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            personTable.getItems().remove(selectedIndex);
        }else{
            Dialogs.create()
                    .title("非法操作")
                    .masthead("没有选择用户")
                    .message("请选择一个用户")
                    .showWarning();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person("","");
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            this.getPersonList().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                this.showPersonDetail(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }

    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(this.getPersonList());
    }
}
