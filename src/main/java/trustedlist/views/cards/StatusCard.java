package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.cards.StatusCardController;
import trustedlist.models.DTO.Status;

import java.io.IOException;

public class StatusCard extends HBox {
    private final Status status;

    public StatusCard(Status s){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatusCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new StatusCardController(s));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        status = s;
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Status getStatus(){
        return status;
    }

}
