package trustedlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trustedlist.views.panes.ErrorPane;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        mInstance = this;

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/MainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("EU - Trusted list navigation");
            stage.setScene(scene);
            stage.setFullScreen(true);
        } catch (Throwable clde){
            clde.printStackTrace();
            stage.setScene(new Scene(new ErrorPane()));
        }

        stage.show();
    }
    private static Application mInstance;
    public static Application getInstance() {
        return mInstance;
    }
    public static void main(String[] args) {
        launch();
    }
}