package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.controllers.panes.CountryDetailsPaneController;
import trustedlist.models.DTO.Country;

import java.io.IOException;

public class CountryDetailsPane extends VBox {
    public CountryDetailsPane(Country c){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CountryDetailsPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new CountryDetailsPaneController(c));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);
    }

}
