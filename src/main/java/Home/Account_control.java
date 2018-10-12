package Home;

import Database.DBconnector;
import Model.Income;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;


public class Account_control {

    private ArrayList<String> getIncomeInTableView = new ArrayList<>();
    private String readDB = DBconnector.readFile();

    @FXML
    private Label balance ;

    @FXML
    private TextField tf_type , tf_comment , tf_price;

    @FXML
    TableView table = new TableView();

    @FXML
    private Button Add , btn_modify , btn_ok;

    @FXML
    TableColumn<Account_control,String> numberRank ;

    @FXML
    TableColumn<Income, String> type;

    @FXML
    TableColumn<Income, String> comment;

    @FXML
    TableColumn<Income, String> price;

    private ObservableList<Income> data = FXCollections.observableArrayList();
    private ObservableList<Income> modifyData = FXCollections.observableArrayList();
    private Income income;
    private Income addNewIncome ;
    private int Money = 0 ;

    @FXML
    private void incomeMenu(ActionEvent event){
        tf_type.setText("Income");
    }

    @FXML
    private void expendsMenu(ActionEvent event){
        tf_type.setText("Expends");
    }



    @FXML
    public void checkTableView(ActionEvent event){
        Add = (Button) event.getSource();
        income = new Income(tf_type.getText(),tf_comment.getText(),tf_price.getText());
        data.add(income);


        String sType = ""+tf_type.getText();
        String sPrice = "" + tf_price.getText();
        System.out.println(sPrice);

        if (sType.equals("Income")){
            Money += Integer.parseInt(sPrice);
        }
        else {
            Money -= Integer.parseInt(sPrice);
        }
        balance.setText("" + Money);


        tf_type.clear();
        tf_comment.clear();
        tf_price.clear();

        DBconnector.writer(income);
        show();

    }
    @FXML
    public void show(){
        type.setCellValueFactory(cellData -> cellData.getValue().typeTableProperty());
        comment.setCellValueFactory(cellData -> cellData.getValue().commentTableProperty());
        price.setCellValueFactory(cellDate -> cellDate.getValue().priceTableProperty());

        table.setItems(data);
    }

    public void start(){
        String[] splitDB = readDB.split("#");
        for (String s : splitDB) {
            String[] word = s.split(",");
            income = new Income(word[0],word[1],word[2]);
            data.add(income);
            if (word[0].equals("Income")){
                Money += Integer.parseInt(word[2]);
            }
            else {
                Money -= Integer.parseInt(word[2]);
            }
        }

        show();
        balance.setText("" + Money);
    }

    @FXML
    public void modify(ActionEvent event){
        btn_modify = (Button) event.getSource();
        status(false,true);
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        comment.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    public void clickOkButton(ActionEvent event){
        btn_ok = (Button) event.getSource();
        status(true,false);



        Income product = new Income();
        List <List<String>> arrList = new ArrayList<>();
        for (int i=0 ; i<table.getItems().size() ; i++){
            product = (Income) table.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(product.getTypeTable());
            arrList.get(i).add(product.getCommentTable());
            arrList.get(i).add(product.getPriceTable());
        }

        for (int i=0 ; i<arrList.size() ; i++){
            for (int j=0 ; j< arrList.get(i).size() ; j++){
//                System.out.println(arrList.get(i).get(j));
                getIncomeInTableView.add(arrList.get(i).get(j));
            }
        }

        System.out.println(getIncomeInTableView);
        getToNewDb();

    }

    public void getToNewDb(){

        for (int i=0 ; i<getIncomeInTableView.size() ; i++){
            if (i==0) {
                addNewIncome = new Income(getIncomeInTableView.get(i),getIncomeInTableView.get(i+1),getIncomeInTableView.get(i+2));
            }
            else if (i%3==0){
                addNewIncome = new Income(getIncomeInTableView.get(i),getIncomeInTableView.get(i+1),getIncomeInTableView.get(i+2));
            }

            modifyData.add(addNewIncome);
            DBconnector.writer(addNewIncome);

            type.setCellValueFactory(cellData -> cellData.getValue().typeTableProperty());
            comment.setCellValueFactory(cellData -> cellData.getValue().commentTableProperty());
            price.setCellValueFactory(cellDate -> cellDate.getValue().priceTableProperty());

            table.setItems(modifyData);
        }
    }


    public void status(boolean ok , boolean tbView){
        btn_ok.setDisable(ok);
        tf_type.setDisable(tbView);
        tf_price.setDisable(tbView);
        tf_comment.setDisable(tbView);
        Add.setDisable(tbView);
        table.setEditable(tbView);
    }


}
