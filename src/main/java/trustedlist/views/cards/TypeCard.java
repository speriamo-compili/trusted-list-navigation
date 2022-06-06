package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import trustedlist.controllers.cards.TypeCardController;
import trustedlist.models.DTO.Type;

import java.io.IOException;

public class TypeCard extends HBox {
    private final Type type;

    public TypeCard(Type t){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TypeCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new TypeCardController(t));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        type = t;
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Type getType(){
        return type;
    }

}
