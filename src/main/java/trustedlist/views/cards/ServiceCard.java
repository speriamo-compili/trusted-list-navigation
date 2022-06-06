package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.cards.ServiceCardController;
import trustedlist.models.DTO.Service;

import java.io.IOException;

public class ServiceCard extends HBox {
    private final Service service;

    public ServiceCard(Service s){
        this.service = s;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ServiceCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new ServiceCardController(s));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAlignment(Pos.CENTER);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Service getCountry(){
        return service;
    }
}
