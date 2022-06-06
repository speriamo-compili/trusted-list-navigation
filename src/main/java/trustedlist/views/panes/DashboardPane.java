package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.panes.DashboardPaneController;

import java.io.IOException;

public class DashboardPane extends GridPane {
    public DashboardPane(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new DashboardPaneController());
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
