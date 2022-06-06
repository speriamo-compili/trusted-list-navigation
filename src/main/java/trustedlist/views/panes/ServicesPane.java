package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.MainApplication;
import trustedlist.controllers.panes.ServicesPaneController;

import java.io.IOException;

public class ServicesPane extends VBox {
    public ServicesPane(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ServicesPane.fxml"));
            this.getStylesheets().add(MainApplication.class.getResource("views/assets/styles/Providers.css").toExternalForm());
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new ServicesPaneController());
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setAlignment(Pos.CENTER);
    }
}
