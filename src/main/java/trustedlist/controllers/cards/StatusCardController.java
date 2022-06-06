package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import trustedlist.models.DTO.Status;

/**
 * Controller class for the StatusCard view
 */
public class StatusCardController {
    @FXML
    private Label code;

    @FXML
    private Text desc;

    private final Status s;

    /**
     * Create a new instance of this class setting the status used to
     * fill information when initialized
     * @param status Status instance used to fill data
     */
    public StatusCardController(Status status){
        s = status;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        code.setText(s.toString());
        desc.setText(s.getDescription());
    }
}
