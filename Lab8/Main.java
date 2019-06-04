package com.dypko;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Main extends Application {
    public Person person;
    private Desktop desktop = Desktop.getDesktop();
    private ObservableList<Person> persons = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab8");
        //primaryStage.getIcons().add(new Image("file:persons.png"));

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        TableView<Person> table = new TableView<Person>(persons);
        table.prefHeightProperty().bind(primaryStage.heightProperty());
        TableColumn<Person, Integer> idColumn = new TableColumn<Person, Integer>("Index");
        idColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        idColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(idColumn);
        TableColumn<Person, String> nameColumn = new TableColumn<Person, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firm"));
        nameColumn.prefWidthProperty().bind(table.widthProperty().divide(2.05));
        table.getColumns().add(nameColumn);

        ScrollPane sp = new ScrollPane();
        sp.setPadding(new Insets(10));
        Button add_btn = new Button("Add person");
        Button delete_btn = new Button("Delete person");
        Button get_btn = new Button("Get from file(XML)");
        Button put_btn = new Button("Put to file(XML)");
        HBox buttons = new HBox(add_btn,delete_btn,get_btn,put_btn);
        VBox hb = new VBox();
        get_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(new FileInputStream(file.toString())))){
                        Integer size_ = (Integer) decoder_.readObject();
                        for (int i = 0; i < size_; i++) {
                            persons.add((Person) decoder_.readObject());
                        }
                    } catch (FileNotFoundException ex) {
                        System.err.println("FileNotFound");
                    }
                }
            }
        });
        put_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("XML", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.toString())))){
                        encoder.writeObject(persons.size());
                        for (int i = 0; i < persons.size(); i++) {
                            encoder.writeObject(persons.get(i));
                        }
                        encoder.close();
                        System.out.println("Успішно записано!");
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                    }

                }
            }
        });
        delete_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(person != null){
                    persons.remove(person);
                    hb.getChildren().clear();
                    table.refresh();
                    hb.getChildren().addAll(buttons);

                }
            }
        });
        add_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label error_l = new Label();
                TextField name_add = new TextField();
                TextField address_add = new TextField();
                TextField number_add = new TextField();
                TextField dateOfBirth_add = new TextField();
                TextField dateOfEditing_add = new TextField();
                Button save = new Button("Save");
                HBox name_g = new HBox(new Label("Name: "),name_add);
                HBox address_g = new HBox(new Label("Address: "),address_add);
                HBox number_g = new HBox(new Label("Number: "),number_add);
                HBox dateOfBirth_g = new HBox(new Label("Date of Birth: "),dateOfBirth_add);
                HBox dateOfEditing_g = new HBox(new Label("Date of editing: "),dateOfEditing_add);
                hb.getChildren().clear();
                hb.getChildren().addAll(name_g,address_g,number_g,dateOfBirth_g,dateOfEditing_g,save,error_l);

                save.setOnMouseClicked(e -> {
                    error_l.setText("");
                    if(name_add.getText().length() > 0){
                        Person add_pers = new Person();
                        add_pers.setName(name_add.getText());
                        add_pers.setAddress(address_add.getText());
                        if(number_add.getText().matches("[0-9]*")) {
                            if(number_add.getText().length() > 0) {
                                add_pers.setNumber(Integer.parseInt(number_add.getText()));
                            }
                            else{
                                add_pers.setNumber(null);
                            }
                        }
                        if(dateOfBirth_add.getText().matches("[0-9]*")) {
                            if(dateOfBirth_add.getText().length() > 0) {
                                add_pers.setDateOfBitrh(Integer.parseInt(dateOfBirth_add.getText()));
                            }
                            else{
                                add_pers.setDateOfBitrh(null);
                            }
                        }
                        if(dateOfEditing_add.getText().matches("[0-9]*")) {
                            if(dateOfEditing_add.getText().length() > 0) {
                                add_pers.setDateOfEditing(Integer.parseInt(dateOfEditing_add.getText()));
                            }
                            else{
                                add_pers.setDateOfEditing(null);
                            }
                        }
                        persons.add(add_pers);
                        hb.getChildren().clear();
                        table.refresh();
                        hb.getChildren().addAll(buttons);
                    }
                    else{
                        error_l.setText("Name must be not empty!");
                    }
                });
            }
        });
        getVacancy(hb,table);
        hb.getChildren().addAll(buttons);
        TableView.TableViewSelectionModel<Person> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Person>(){

            public void changed(ObservableValue<? extends Person> val, Person oldVal, Person newVal){
                if(newVal != null){
                    person = newVal;
                    hb.getChildren().clear();
                    hb.getChildren().addAll(buttons);
                    getVacancy(hb,table);

                }
            }
        });
        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints(150,150,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        root.getColumnConstraints().add(column2);
        root.setColumnIndex(table, 0);
        sp.setContent(hb);
        root.setColumnIndex(sp, 1);

        root.getChildren().addAll(table, sp);

        Scene scene =  new Scene(root, 1000, 500);

        scene.getStylesheets().add((getClass().getResource("main.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void getVacancy(VBox hb,TableView<Person> table){
        if(persons.size() > 0) {
            if (person == null) {
                person = persons.get(0);
            }
            Label lbl1 = new Label("Index:");
            TextField name = new TextField();
            TextField address = new TextField();
            TextField number = new TextField();
            TextField dateOfBirth = new TextField();
            TextField dateOfEdting = new TextField();
            lbl1.setText("Index: " + person.getIndex());
            name.setText(person.getName());
            address.setText(person.getAddress());
            number.setText("" + ((person.getNumber() == null) ? "" : person.getNumber()));
            dateOfBirth.setText("" + ((person.getDateOfBirth() == null) ? "" : person.getDateOfBirth()));
            dateOfEdting.setText("" + ((person.getDateOfEditing() == null) ? "" : person.getDateOfEditing()));
            HBox name_g = new HBox(new Label("Name: "), name);
            HBox address_g = new HBox(new Label("Address:"), address);
            HBox number_g = new HBox(new Label("Number: "), number);
            HBox dateOfBirth_g = new HBox(new Label("Date of Birth: "), dateOfBirth);
            HBox dateOfEditing_g = new HBox(new Label("Date of editing: "), dateOfEdting);

            name.textProperty().addListener((observable, oldValue, newValue) -> {
                if (person != null) {
                    person.setName(newValue);
                    table.refresh();
                }
            });
            address.textProperty().addListener((observable, oldValue, newValue) -> {
                if (person != null) {
                    person.setAddress(newValue);
                }
            });
            number.textProperty().addListener((observable, oldValue, newValue) -> {
                if (person != null) {
                    if (newValue.matches("[0-9]*")) {
                        if (newValue.length() > 0) {
                            person.setNumber(Integer.parseInt(newValue));
                        } else {
                            person.setNumber(null);
                        }
                    } else {
                        number.setText("" + ((person.getNumber() == null) ? "" : person.getNumber()));
                    }
                }
            });
            dateOfBirth.textProperty().addListener((observable, oldValue, newValue) -> {
                if (person != null) {
                    if (newValue.matches("[0-9]*")) {
                        if (newValue.length() > 0) {
                            person.setDateOfBitrh(Integer.parseInt(newValue));
                        } else {
                            person.setDateOfBitrh(null);
                        }
                    } else {
                        dateOfBirth.setText("" + ((person.getDateOfBirth() == null) ? "" : person.getDateOfBirth()));
                    }
                }
            });
            dateOfEdting.textProperty().addListener((observable, oldValue, newValue) -> {
                if (person != null) {
                    if (newValue.matches("[0-9]*")) {
                        if (newValue.length() > 0) {
                            person.setDateOfEditing(Integer.parseInt(newValue));
                        } else {
                            person.setDateOfEditing(null);
                        }
                    } else {
                        dateOfEdting.setText("" + ((person.getDateOfEditing() == null) ? "" : person.getDateOfEditing()));
                    }
                }
            });
            hb.getChildren().addAll(lbl1, name_g, address_g, number_g, dateOfBirth_g, dateOfEditing_g);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}