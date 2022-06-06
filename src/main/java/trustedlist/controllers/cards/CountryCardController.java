package trustedlist.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import trustedlist.MainApplication;
import trustedlist.models.DTO.Country;
import trustedlist.models.utility.Stats;

/**
 * Controller class for the CountryCard view
 */
public class CountryCardController {
    @FXML
    private Label code;

    @FXML
    private ImageView flag;

    @FXML
    private Label name;

    @FXML
    private Label providers;

    @FXML
    private Label services;

    private Country c;

    /**
     * Create a new instance of this class setting the country used to
     * fill information when initialized
     * @param country Country instance used to fill data
     */
    public CountryCardController(Country country){
        c = country;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Data initialization
        flag.setImage(new Image(MainApplication.class.getResource("views/assets/flags/" + c.getCode() + ".png").toString()));
        name.setText(c.getName());
        code.setText(c.getCode());
        providers.setText(Stats.getCountryProvidersCount(c) + "");
        services.setText(Stats.getCountryServicesCount(c) + "");
    }
}
