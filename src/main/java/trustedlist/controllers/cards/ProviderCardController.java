package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import trustedlist.models.DTO.Provider;
import trustedlist.models.DTO.Type;

/**
 * Controller class for the ProviderCard view
 */
public class ProviderCardController {
    @FXML
    private Label name;

    @FXML
    private TilePane types;

    private final Provider p;

    /**
     * Create a new instance of this class setting the provider used to
     * fill information when initialized
     * @param provider Provider instance used to fill data
     */
    public ProviderCardController(Provider provider){
        p = provider;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Set name
        name.setText(p.getName());

        // Add types
        for(Type t : p.getTypes()){
            Label l = new Label(t.toString());
            l.getStyleClass().add("type");
            l.getStyleClass().add("text");
            types.getChildren().add(l);
        }
    }
}
