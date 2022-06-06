package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import trustedlist.models.DTO.Service;
import trustedlist.models.DTO.Type;

/**
 * Controller class for the ServiceCard view
 */
public class ServiceCardController {
    @FXML
    private Label name;

    @FXML
    private TilePane types;

    private final Service s;

    /**
     * Create a new instance of this class setting the service used to
     * fill information when initialized
     * @param service Service instance used to fill data
     */
    public ServiceCardController(Service service){
        s = service;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        name.setText(s.getName());

        for(Type t : s.getTypes()){
            Label l = new Label(t.toString());
            l.getStyleClass().add("type");
            types.getChildren().add(l);
        }
    }
}
