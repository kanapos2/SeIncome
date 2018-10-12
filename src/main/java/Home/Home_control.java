package Home;

import Database.DBconnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Home_control {

    private int money = 0 ;

    @FXML
    Label test ;

    @FXML
    Button enter ;

    @FXML
    public void initialize(){
        balance();
    }

    public void balance(){
        String readDB = DBconnector.readFile();
        String[] splitDB = readDB.split("#");
        for (String s : splitDB){
            String[] word = s.split(",");
            if (word[0].equals("Income")){
                money += Integer.parseInt(word[2]);
            }
            else
                money -= Integer.parseInt(word[2]);
        }
        test.setText("" + money);
    }


    @FXML
    public void enterToAccount(ActionEvent event) {


        enter = (Button) event.getSource();
        Stage stage = (Stage) enter.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Account_page.fxml")) ;

        try {
            stage.setScene(new Scene(loader.load(),600,600));
            stage.setTitle("Register user");

            Account_control controller = (Account_control) loader.getController();
            controller.start();

            stage.show();
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }


}
