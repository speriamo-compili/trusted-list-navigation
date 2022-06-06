package trustedlist.views.panes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ErrorPane extends HBox {
    public ErrorPane(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ErrorPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

