package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.models.DTO.*;
import trustedlist.models.engine.Search;
import trustedlist.models.engine.Selection;
import trustedlist.models.engine.TrustedListNavigation;
import trustedlist.views.cards.*;

/**
 * Controller class for the SearchPane view
 */
public class SearchPaneController {
    @FXML
    private Button back;

    @FXML
    private Button next;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Label title;

    @FXML
    private VBox result;


    // Step-by-step selection variables
    private Selection selection;
    private VBox container;
    private int stage = 0;

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Set visual properties
        HBox.setHgrow(scrollpane, Priority.ALWAYS);
        back.setVisible(false);
        next.setVisible(false);

        // Create container
        container = new VBox();
        container.getStyleClass().add("container");
        scrollpane.setContent(container);

        // Create a selection
        selection = new Selection();

        // Add event handlers
        back.setOnAction(e -> handleBackClick());
        next.setOnAction(e -> handleNextClick());

        // Fill with data
        createCountriesList();
    }

    private void handleCountryClick(CountryCard ci) {
        toggleSelected(ci);
        selection.toggle(ci.getCountry());
        next.setVisible(selection.getSelectedCountries().size() != 0);
    }

    private void handleNextClick() {

        // This method use stage variable
        // The selection is made out of 4 stages
        //  0 = Country
        //  1 = Provider
        //  2 = Type
        //  3 = Status

        if(selection.getSelectedStatus().size() != 0){
            stage++;
            createServicesList();
            return;
        }

        if(selection.getSelectedTypes().size() != 0){
            stage++;
            createStatusList();
            return;
        }

        if(selection.getSelectedProviders().size() != 0){
            stage++;
            createTypesList();
            return;
        }

        if(selection.getSelectedCountries().size() != 0){
            stage++;
            createProvidersList();
        }
    }

    private void createStatusList() {
        resetPane();
        title.setText("Select one or more status");
        for(Status s : selection.getStatusList()){
            StatusCard si = new StatusCard(s);
            si.getStyleClass().add("backgrounded");
            si.setOnMouseClicked(e -> handleStatusClick(si));
            container.getChildren().add(si);
        }
    }

    private void handleStatusClick(StatusCard si) {
        toggleSelected(si);
        selection.toggle(si.getStatus());
        next.setVisible(selection.getSelectedStatus().size() != 0);
    }

    private void createServicesList() {
        resetPane();
        result.getChildren().add(new SummaryCard(selection));
        title.setText("Search result:");
        for(Service s : Search.searchService(selection))
            container.getChildren().add(new ServiceCard(s));
    }

    private void handleBackClick() {

        // This method use stage variable
        // The selection is made out of 4 stages
        //  0 = Country
        //  1 = Provider
        //  2 = Type
        //  3 = Status
        //  4 = Search result

        selection.getSelectedStatus().clear();

        if(stage == 4){
            stage = 0;
            selection = new Selection();
            createCountriesList();
            return;
        }

        if(stage == 3){
            stage--;
            selection.getSelectedTypes().clear();
            createTypesList();
            return;
        }

        if(stage == 2){
            stage--;
            selection.getSelectedProviders().clear();
            createProvidersList();
            return;
        }

        if(stage == 1){
            stage--;
            selection.getSelectedCountries().clear();
            createCountriesList();
        }
    }

    private void createProvidersList() {
        resetPane();
        title.setText("Select one or more providers");
        for(Provider p : selection.getProvidersList()){
            ProviderCard pi = new ProviderCard(p);
            pi.setOnMouseClicked(e -> handleProviderClick(pi));
            container.getChildren().add(pi);
        }
    }

    private void handleProviderClick(ProviderCard pi) {
        toggleSelected(pi);
        selection.toggle(pi.getProvider());
        next.setVisible(selection.getSelectedProviders().size() != 0);
    }

    private void createTypesList() {
        resetPane();
        title.setText("Select one or more types");
        for(Type t : selection.getTypesList()){
            TypeCard ti = new TypeCard(t);
            ti.getStyleClass().add("backgrounded");
            ti.setOnMouseClicked(e -> handleTypeClick(ti));
            container.getChildren().add(ti);
        }
    }

    private void handleTypeClick(TypeCard ti) {
        toggleSelected(ti);
        selection.toggle(ti.getType());
        next.setVisible(selection.getSelectedTypes().size() != 0);
    }

    private void createCountriesList(){
        resetPane();
        title.setText("Select one or more countries");
        back.setVisible(false);
        for(Country c : TrustedListNavigation.getInstance().getAllCountries()){
            CountryCard ci = new CountryCard(c);
            ci.setOnMouseClicked(e -> handleCountryClick(ci));
            container.getChildren().add(ci);
        }
    }

    private void resetPane(){
        // Reset the pane to initial conditions
        back.setVisible(true);
        next.setVisible(false);
        container.getChildren().clear();
        result.getChildren().clear();
        scrollpane.setVvalue(0);
    }

    private void toggleSelected(Node node){
        // Toggle the "selected" CSS class to the element node
        if(node.getStyleClass().contains("selected"))
            node.getStyleClass().remove("selected");
        else
            node.getStyleClass().add("selected");
    }
}
