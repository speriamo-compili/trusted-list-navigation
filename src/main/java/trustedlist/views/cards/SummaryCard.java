package trustedlist.views.cards;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import trustedlist.controllers.cards.SummaryCardController;
import trustedlist.models.engine.Selection;

import java.io.IOException;

public class SummaryCard extends VBox {
    public SummaryCard(Selection selection){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SummaryCard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(new SummaryCardController(selection));
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
