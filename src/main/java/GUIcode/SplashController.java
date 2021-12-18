package GUIcode;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    private static Scene mainmenuscene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController.getFlightReservationSystem().LoadDataFromDatabases();
        splash();
    }

    private void splash()
    {
        new Thread()
        {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {

                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainMenu.fxml"));
                            mainmenuscene = new Scene(fxmlLoader.load(),500,400);
                            Stage stage = new Stage();
                            stage.setScene(mainmenuscene);
                            stage.setHeight(430);
                            stage.setWidth(500);
                            stage.setResizable(false);
                            HelloApplication.getWindow().close();
                            HelloApplication.setWindow(stage);
                            HelloApplication.getWindow().show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
    }

    public static Scene getMainmenuscene() {
        return mainmenuscene;
    }

    public static void setMainmenuscene(Scene mainmenuscene) {
        SplashController.mainmenuscene = mainmenuscene;
    }
}
