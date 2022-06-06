package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.TilePane;
import trustedlist.models.DTO.Type;
import trustedlist.views.cards.TypeCard;

/**
 * Controller class for the TypesPane view
 */
public class TypesPaneController {
    @FXML
    private PieChart chart;

    @FXML
    private TilePane types;

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        for(Type t : Type.values()){
            TypeCard ti = new TypeCard(t);
            ti.setMaxWidth(200);
            types.getChildren().add(ti);
        }
    }
}
