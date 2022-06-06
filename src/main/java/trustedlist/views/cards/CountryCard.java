package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.cards.CountryCardController;
import trustedlist.models.DTO.Country;

import java.io.IOException;

public class CountryCard extends HBox {
    private final Country country;

    public CountryCard(Country c){
        this.country = c;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CountryCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new CountryCardController(c));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAlignment(Pos.CENTER);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Country getCountry(){
        return country;
    }
}
