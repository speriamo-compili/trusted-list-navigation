package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.cards.ProviderCardController;
import trustedlist.models.DTO.Provider;

import java.io.IOException;

public class ProviderCard extends HBox {
    private final Provider provider;

    public ProviderCard(Provider p){
        this.provider = p;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProviderCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new ProviderCardController(p));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAlignment(Pos.CENTER);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Provider getProvider(){
        return provider;
    }
}
