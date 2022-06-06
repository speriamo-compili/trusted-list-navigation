package trustedlist.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import trustedlist.models.DTO.Service;
import trustedlist.models.engine.TrustedListNavigation;
import trustedlist.views.cards.ServiceCard;

import java.util.List;

/**
 * Controller class for the ServicesPane view
 */
public class ServicesPaneController {
    @FXML
    private Button nextpage;

    @FXML
    private Label pages;

    @FXML
    private Button prevpage;

    @FXML
    private ScrollPane scrollpane;

    // Paginated navigation variables
    private final int ROWS_PER_PAGE = 20;
    private int currentProviderPage = 0;
    private int TOTAL_PAGES;
    private List<Service> services;
    private VBox container;

    /**
     * Method called by FXML loader when the controller get initialized
     */
    @FXML
    public void initialize(){
        // Set visual properties
        HBox.setHgrow(scrollpane, Priority.ALWAYS);
        prevpage.setVisible(false);

        // Create container
        container = new VBox();
        container.getStyleClass().add("container");
        scrollpane.setContent(container);

        // Get all services
        services = TrustedListNavigation.getInstance().getAllServices();

        // Calculate the number of total pages
        TOTAL_PAGES = (int)Math.ceil((double)TrustedListNavigation.getInstance().getAllServices().size() / ROWS_PER_PAGE);

        // Initialize GUI data
        pages.setText((currentProviderPage+1) + "/" + TOTAL_PAGES +"");
        createProvidersList(0);

        // Add event handlers
        nextpage.setOnAction(e -> handleNextProviderClick());
        prevpage.setOnAction(e -> handlePreviousProviderClick());
    }

    private void handlePreviousProviderClick(){
        // Check if the user is on the first page
        if(currentProviderPage != 0){
            scrollpane.setVvalue(0);
            nextpage.setVisible(true);
            createProvidersList(--currentProviderPage);
            pages.setText((currentProviderPage+1) + "/" + TOTAL_PAGES +"");

            // If the page we went on is the first, we remove the previous button
            if(currentProviderPage == 0)
                prevpage.setVisible(false);
        }
    }

    private void handleNextProviderClick(){
        // Check if the user is on the last page
        if(currentProviderPage != TOTAL_PAGES-1){
            scrollpane.setVvalue(0);
            prevpage.setVisible(true);
            createProvidersList(++currentProviderPage);
            pages.setText((currentProviderPage+1) + "/" + TOTAL_PAGES +"");

            // If the page we went on is the last, we remove the next button
            if(currentProviderPage == TOTAL_PAGES - 1)
                nextpage.setVisible(false);
        }
    }

    private void createProvidersList(int index) {

        // Check if the specified page is in the range 0-n where n is the number of pages
        if (index < 0 || index > TOTAL_PAGES)
            throw new IndexOutOfBoundsException("Tried to access page number " + index + " but the range is 0-" + TOTAL_PAGES);


        int fromIndex = index * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, services.size());



        List<Service> newList = services.subList(fromIndex, toIndex);
        container.getChildren().clear();
        for(Service s : newList)
            container.getChildren().add(new ServiceCard(s));

    }
}
