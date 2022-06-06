package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import trustedlist.MainApplication;
import trustedlist.controllers.MainController;
import trustedlist.models.DTO.Country;
import trustedlist.models.engine.TrustedListNavigation;
import trustedlist.models.utility.Stats;
import trustedlist.views.panes.SearchPane;

import java.util.List;

/**
 * Controller class for the DashboardPane view
 */
public class DashboardPaneController {
    @FXML
    private Label countriesNumber;

    @FXML
    private Label providersNumber;

    @FXML
    private Label servicesNumber;

    @FXML
    private PieChart piechart;

    @FXML
    private ImageView firstImage;

    @FXML
    private ImageView secondImage;

    @FXML
    private ImageView thirdImage;

    @FXML
    private Button viewMore;

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Get TLN instance
        TrustedListNavigation tln = TrustedListNavigation.getInstance();

        // Fill counters
        countriesNumber.setText(Stats.getCountriesCount() + "");
        providersNumber.setText(Stats.getProvidersCount() + "");
        servicesNumber.setText(Stats.getServicesCount() + "");

        // Fill pie chart based on country's service count
        for(Country c : TrustedListNavigation.getInstance().getAllCountries())
            piechart.getData().add(new PieChart.Data(c.getName(), Stats.getCountryServicesCount(c)));

        // Fill the top 3 countries flags
        List<Country> sorted = tln.getAllCountries().stream().sorted().toList();
        firstImage.setImage(new Image(MainApplication.class.getResource("views/assets/flags/" + sorted.get(0).getCode() + ".png").toExternalForm()));
        secondImage.setImage(new Image(MainApplication.class.getResource("views/assets/flags/" + sorted.get(1).getCode() + ".png").toExternalForm()));
        thirdImage.setImage(new Image(MainApplication.class.getResource("views/assets/flags/" + sorted.get(2).getCode() + ".png").toExternalForm()));

        // Add event handler of click on view more
        viewMore.setOnAction(e -> MainController.getInstance().searchPane());
    }
}
