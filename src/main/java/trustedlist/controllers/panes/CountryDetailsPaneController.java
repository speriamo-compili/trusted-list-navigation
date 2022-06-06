package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import trustedlist.MainApplication;
import trustedlist.controllers.MainController;
import trustedlist.models.DTO.Country;
import trustedlist.models.DTO.Status;
import trustedlist.models.DTO.Type;
import trustedlist.models.utility.Stats;
import trustedlist.views.panes.CountriesPane;

import java.util.Map;

/**
 * Controller class for the CountryDetailsPane view
 */
public class CountryDetailsPaneController {
    @FXML
    private Button moreinfo;

    @FXML
    private Label name;

    @FXML
    private Text providers;

    @FXML
    private Text services;

    @FXML
    private Text types;

    @FXML
    private HBox back;

    @FXML
    private PieChart statusChart;


    @FXML
    private BarChart<?, ?> typesChart;

    private final Country c;

    /**
     * Create a new instance of this class setting the country used to
     * fill information when initialized
     * @param country Country instance used to fill data
     */
    public CountryDetailsPaneController(Country country){
        c = country;
    }

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Initialize data
        name.setText(c.getName());
        providers.setText(c.getProviders().size() + "");
        services.setText(c.getServices().size() + "");
        types.setText(c.getTypes().size() + "");

        // Fill pie chart based on service statuses
        Map<Status, Integer> map = Stats.getServicesPerStatus(c);
        for(Status s : map.keySet())
            statusChart.getData().add(new PieChart.Data(s.getDescription(), map.get(s)));

        // Fill XY chart based on service types
        XYChart.Series data = new XYChart.Series<>();
        Map<Type, Integer> typesCount = Stats.getServicesPerType(c);
        for(Type t : typesCount.keySet())
            data.getData().add(new XYChart.Data<>(t.toString(), typesCount.get(t)));
        typesChart.getData().add(data);

        // Add event handlers
        moreinfo.setOnMouseClicked(e -> MainApplication.getInstance().getHostServices().showDocument("https://esignature.ec.europa.eu/efda/tl-browser/#/screen/tl/" + c.getCode().toUpperCase()));
        back.setOnMouseClicked(e -> {
            HBox content = MainController.getInstance().getContentPane();
            content.getChildren().clear();
            content.getChildren().add(new CountriesPane());
        });
    }
}
