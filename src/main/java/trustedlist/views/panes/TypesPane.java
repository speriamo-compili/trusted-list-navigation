package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.MainApplication;
import trustedlist.controllers.panes.TypesPaneController;

import java.io.IOException;

public class TypesPane extends VBox {
    public TypesPane(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TypesPane.fxml"));
            this.getStylesheets().add(MainApplication.class.getResource("views/assets/styles/Countries.css").toExternalForm());
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new TypesPaneController());
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
