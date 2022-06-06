package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.controllers.MainController;
import trustedlist.models.DTO.Country;
import trustedlist.models.engine.TrustedListNavigation;
import trustedlist.views.cards.CountryCard;
import trustedlist.views.panes.CountryDetailsPane;

/**
 * Controller class for the CountriesPane view
 */
public class CountriesPaneController {
    @FXML
    private ScrollPane scrollpane;

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        HBox.setHgrow(scrollpane, Priority.ALWAYS);

        VBox container = new VBox();
        container.getStyleClass().add("container");
        scrollpane.setContent(container);


        for(Country c : TrustedListNavigation.getInstance().getAllCountries()){
            CountryCard ci = new CountryCard(c);
            ci.setOnMouseClicked(e -> handleCountryClick(c));
            container.getChildren().add(ci);
        }
    }

    private void handleCountryClick(Country c) {
        // Method used to handle the click on a country card
        HBox content = MainController.getInstance().getContentPane();
        content.getChildren().clear();
        content.getChildren().add(new CountryDetailsPane(c));
    }

}
