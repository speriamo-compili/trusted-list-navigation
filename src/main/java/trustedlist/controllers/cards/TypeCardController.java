package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import trustedlist.models.DTO.Type;

/**
 * Controller class for the CountryCard view
 */
public class TypeCardController {
    @FXML
    private Label code;

    @FXML
    private Text desc;

    private final Type t;

    /**
     * Create a new instance of this class setting the type used to
     * fill information when initialized
     * @param type Type instance used to fill data
     */
    public TypeCardController(Type type){
        t = type;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        code.setText(t.toString());
        desc.setText(t.getDescription());
    }



}
