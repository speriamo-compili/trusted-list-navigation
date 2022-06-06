package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.MainApplication;
import trustedlist.controllers.panes.CountriesPaneController;

import java.io.IOException;

public class CountriesPane extends VBox {
    public CountriesPane(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CountriesPane.fxml"));
            this.getStylesheets().add(MainApplication.class.getResource("views/assets/styles/Countries.css").toExternalForm());
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new CountriesPaneController());
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setAlignment(Pos.CENTER);
    }

}
