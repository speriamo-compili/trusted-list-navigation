package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import trustedlist.models.DTO.Country;
import trustedlist.models.DTO.Provider;
import trustedlist.models.DTO.Status;
import trustedlist.models.DTO.Type;
import trustedlist.models.engine.Selection;

/**
 * Controller class for the SummaryCard view
 */
public class SummaryCardController {
    @FXML
    private VBox countries;

    @FXML
    private VBox providers;

    @FXML
    private VBox status;

    @FXML
    private VBox types;

    private final Selection s;

    /**
     * Create a new instance of this class setting the selection used to
     * fill information when initialized
     * @param selection Selection instance used to fill data
     */
    public SummaryCardController(Selection selection){
        s = selection;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Fill with selected countries
        for(Country c : s.getSelectedCountries())
            countries.getChildren().add(new Label(c.getName()));

        // Fill with selected providers
        for(Provider p : s.getSelectedProviders())
            providers.getChildren().add(new Label(p.getName()));

        // Fill with selected types
        for(Type t : s.getSelectedTypes())
            types.getChildren().add(new Label(t.toString()));

        // Fill with selected status
        for(Status s : s.getSelectedStatus())
            status.getChildren().add(new Label(s.toString()));
    }
}
