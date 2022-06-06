package trustedlist.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import trustedlist.models.engine.TrustedListNavigation;
import trustedlist.views.panes.*;

/**
 * Controller class for the main view
 */
public class MainController {

    @FXML
    private HBox closeButton;

    @FXML
    private HBox contentPane;

    @FXML
    private HBox countriesButton;

    @FXML
    private HBox dashboardButton;

    @FXML
    private HBox providersButton;

    @FXML
    private HBox searchButton;

    @FXML
    private HBox servicesButton;

    @FXML
    private HBox typesButton;

    private static MainController mc;

    @FXML
    public void initialize(){
        TrustedListNavigation.getInstance();
        contentPane.getChildren().add(new DashboardPane());
        mc = this;

    }

    @FXML
    void goDashboardPane() {
        createPane(dashboardButton, new DashboardPane());
    }

    @FXML
    void goCountriesPane() {
        createPane(countriesButton, new CountriesPane());
    }

    @FXML
    void goProvidersPane() { createPane(providersButton, new ProvidersPane()); }

    @FXML
    void goServicesPane() {
        createPane(servicesButton, new ServicesPane());
    }

    @FXML
    void goTypesPane() {
        createPane(typesButton, new TypesPane());
    }

    @FXML
    void goSearchPane() {
        createPane(searchButton, new SearchPane());
    }

    @FXML
    void handleClose(){
        ((Stage)closeButton.getScene().getWindow()).close();
    }


    private void resetSidepaneButtons(){
        dashboardButton.getStyleClass().remove("active");
        countriesButton.getStyleClass().remove("active");
        providersButton.getStyleClass().remove("active");
        servicesButton.getStyleClass().remove("active");
        typesButton.getStyleClass().remove("active");
        searchButton.getStyleClass().remove("active");
    }

    private void createPane(Node button, Node pane){
        resetSidepaneButtons();
        button.getStyleClass().add("active");
        contentPane.getChildren().clear();
        contentPane.getChildren().add(pane);
    }

    public void searchPane(){
        createPane(searchButton, new SearchPane());
    }

    public static MainController getInstance(){
        return mc;
    }

    public HBox getContentPane(){
        return contentPane;
    }

}