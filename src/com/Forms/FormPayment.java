package com.Forms;

import com.DAO_Database.DAO;
import com.IMPL.*;
import com.classes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import scenes.SceneInterface;
import scenes.SceneManager;

import javax.swing.text.Style;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FormPayment implements SceneInterface {

    SceneManager sceneManager;

    public FormPayment(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    Scene PaymentScene;
    BorderPane root = new BorderPane();
    GridPane gridpane=new GridPane();
    GridPane SmallGridPane=new GridPane();
    HBox HboxTop =new HBox();
    VBox Vboxright =new VBox();
    VBox Vboxleft=new VBox();

    TableView<Payment>tableViewPayment=new TableView<>();
    ObservableList<Payment> observableTablePayments=FXCollections.observableArrayList();
   // private ComboBox<Client>comboBox=new ComboBox<>();
    ComboBox<String>comboBoxType=new ComboBox<>();
    List<String>listType=new ArrayList<>();
    Button buttonAdd=new Button("Ajouter");
    Button buttonModifier=new Button("Modifier");
    Button buttonDelete=new Button("Supprimer");
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    TextField filterFieldPayment=new TextField();
    DAO<Payment> p=new PaymentDAOIMPL();
    DAO<Client>ClientDAO=new ClientDAOIMPL();
    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");
    Button buttonHome=new Button("Home");
    TextFlow textFlow=new TextFlow();
    DatePicker datePicker=new DatePicker();
    LocalTime timePicker;
    //Creating a Grid Pane
    //Setting size for the pane
    private void filterData(String str) {
        tableViewPayment.getItems().clear();
        List<Payment> filteredList =  p.findAll(str);
        if(filteredList !=null) {//tableViewProduct.getItems().addAll(filteredList);
            observableTablePayments.setAll(filteredList);
            tableViewPayment.setItems(observableTablePayments);}


    }

    private void filteredList(){
        filterFieldPayment.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(filterFieldPayment.getText().length()!=0) {
                    filterData(filterFieldPayment.getText());
                }
                else loadDataToTable();
                //refreshContentProduit();
            }
        });
    }
    private void CreateIcons() throws FileNotFoundException {

        Image imageHome = new Image(new FileInputStream("picturesProject/home.png"));
        Image imageCategorize = new Image(new FileInputStream("picturesProject/categorize.png"));
        Image imageProduct = new Image(new FileInputStream("picturesProject/product.png"));
        Image imageCommande = new Image(new FileInputStream("picturesProject/commande.png"));
        Image imageCustomer = new Image(new FileInputStream("picturesProject/customer.png"));
        Image imageSale = new Image(new FileInputStream("picturesProject/sale.png"));
        Image imagePayment= new Image(new FileInputStream("picturesProject/payment.png"));

        buttonHome.setGraphic(new ImageView(imageHome));
        buttonProduits.setGraphic(new ImageView(imageProduct));
        buttonCommandes.setGraphic(new ImageView(imageCommande));
        buttonVentes.setGraphic(new ImageView(imageSale));
        buttonClients.setGraphic(new ImageView(imageCustomer));
        buttonCategories.setGraphic(new ImageView(imageCategorize));
        buttonPaiements.setGraphic(new ImageView(imagePayment));
    }
    private void CreateVboxleft(){
        Vboxleft.setStyle("-fx-background-color: #777777;");
        Vboxleft.setSpacing(10);
        Vboxleft.setPrefWidth(250);
        buttonHome.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonCategories.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonProduits.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonClients.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonCommandes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonVentes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonPaiements.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;");
        Vboxleft.getChildren().addAll(buttonHome,buttonCategories,buttonProduits,buttonClients,buttonCommandes,buttonVentes,buttonPaiements);

    }
  /*  private void ComboBoxTitle(){
        Callback<ListView<Client>, ListCell<Client>> factory = lv -> new ListCell<Client>() {

            @Override
            protected void updateItem(Client item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getNom()+" "+item.getPrenom());
            }

        };

        comboBox.setCellFactory(factory);
        comboBox.setButtonCell(factory.call(null));
    }
*/    private void CreateGridPane() throws FileNotFoundException{
        //creating label id/designation/prix
        textField2.setPromptText("Id_vente");
        textField3.setPromptText("Date");
        textField4.setPromptText("montant");
        Label id_vente=new Label("Id_vente");
        Label type=new Label("Type");
        Label montant=new Label("montant");
        Label date=new Label("Date");
        id_vente.setStyle("-fx-font: 15px Serif;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
        montant.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+" -fx-font-weight:bold;");
        type.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
        date.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");

      //Creating Text Filed for id/designation/prix


        buttonAdd.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width:   100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonModifier.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonDelete.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 110;" + "    -fx-text-fill:white;" + "   -fx-background-color: #004080;");
        buttonAdd.setTranslateX(10);buttonDelete.setTranslateX(250);buttonModifier.setTranslateX(130);
        buttonAdd.setTranslateY(210);buttonModifier.setTranslateY(210);buttonDelete.setTranslateY(210);

        Image imageAdd = new Image(new FileInputStream("picturesProject/plus.png"));
        Image imageDelete = new Image(new FileInputStream("picturesProject/delete.png"));
        Image imageUpdate= new Image(new FileInputStream("picturesProject/update.png"));
        buttonAdd.setGraphic(new ImageView(imageAdd));
        buttonDelete.setGraphic(new ImageView(imageDelete));
        buttonModifier.setGraphic(new ImageView(imageUpdate));

        gridpane.setStyle("-fx-background-color: white;");
        /*gridpane.setVgap(10);
        gridpane.setHgap(10);#13D3C2*/
        listType.add("Espece");listType.add("Cheque");listType.add("Trait");
        comboBoxType.getItems().addAll(listType);
       // comboBox.getItems().addAll(ClientDAO.findAll());
        SmallGridPane.setStyle("-fx-background-color:#EBECEC;"+"-fx-border-style: solid;"+"-fx-border-width: 2;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;"+ "-fx-border-color: #5D5D5E;");
        SmallGridPane.addRow(1,id_vente,textField2);
        SmallGridPane.addRow(2,type,comboBoxType);
       // ComboBoxTitle();
        //comboBox.getSelectionModel().selectFirst();
        SmallGridPane.addRow(3 ,montant,  textField4);
      SmallGridPane.addRow(4 ,date,  textField3);
        //comboBox.getSelectionModel().selectFirst();
        SmallGridPane.setTranslateX(10);
        SmallGridPane.setTranslateY(30);
        SmallGridPane.getChildren().addAll(buttonAdd,buttonModifier,buttonDelete);
        SmallGridPane.setPrefWidth(380);
        SmallGridPane.setPrefHeight(280);
        gridpane.setPrefHeight(400);
        gridpane.setPrefWidth(400);
        gridpane.getChildren().add(SmallGridPane);
    }

    private void setForm(Payment p) {
        textField1.setText(Long.toString(p.getId()));
        textField2.setText(Long.toString(p.getVente().getId()));
        textField4.setText(Double.toString(p.getMontant()));
        textField3.setText(p.getDate());
        comboBoxType.getSelectionModel().select(p.getType());
        //datePicker.setValue(p.getDate());
    }
    /* public void refreshContentProduit() {

         observableTableProduits.setAll(p.findAll());
         tableViewProduct.setItems(observableTableProduits);

     }*/
    public void loadDataToTable() {
        tableViewPayment.getItems().clear();
        List<Payment> t = p.findAll();
        if (t != null)
            tableViewPayment.getItems().addAll(t);
       /* comboBox.getItems().clear();
        List<C> p = ClientDAO.findAll();
        if(p!=null)
            comboBox.getItems().addAll(p);
    */}
    private boolean isPaymentFieldsFilled(){
        return textField3.getText().length() > 0  && textField2.getText().length() > 0 && textField4.getText().length() > 0 && comboBoxType.getSelectionModel().getSelectedItem()!=null;
    }
    private void eventHandling() {
        buttonAdd.setOnAction
                (
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent action){
                                //if (isPaymentFieldsFilled() && tableViewPayment.getSelectionModel().getSelectedItem() != null) {
                                    Payment p1=new Payment(Double.parseDouble(textField4.getText()),new Vente(Long.parseLong(textField2.getText())),textField3.getText(),comboBoxType.getSelectionModel().getSelectedItem(),false);
                                    if (p.create(p1)){
                                        System.out.println("succes");
                                    }
                                    else
                                        System.out.println("faild");
                                    loadDataToTable();
                                    //refreshContentProduit();
                                    textField2.clear();textField3.clear();textField4.clear();
                                    comboBoxType.getSelectionModel().clearSelection();
                        //        }
                        }});
        buttonModifier.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                       /* Timestamp date = new Timestamp(datePicker.getValue().getYear() - 1900,datePicker.getValue().getMonthValue(),datePicker.getValue().getDayOfMonth(),timePicker.getLocalTime().getHour(),timePicker.getLocalTime().getMinute(),0,0);*/
                        if (isPaymentFieldsFilled() && tableViewPayment.getSelectionModel().getSelectedItem() != null) {
                            Vente v=new Vente(Long.parseLong(textField2.getText()));
                            //Payment p3=p.getPayement(v);
                            Payment p1=new Payment(Long.parseLong(textField1.getText()),Double.parseDouble(textField4.getText()),v,textField3.getText(),comboBoxType.getSelectionModel().getSelectedItem(),false);

                            p.update(p1);
                            loadDataToTable();
                            //refreshContentProduit();
                            textField1.clear();textField2.clear();textField3.clear();textField4.clear();comboBoxType.getSelectionModel().clearSelection();
                        }}});
        buttonHome.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToHomeScene(sceneManager);
                    }
                }
        );
        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        /*Timestamp date = new Timestamp(datePicker.getValue().getYear() - 1900,datePicker.getValue().getMonthValue(),datePicker.getValue().getDayOfMonth(),timePicker.getLocalTime().getHour(),timePicker.getLocalTime().getMinute(),0,0);*/
                        if (isPaymentFieldsFilled() && tableViewPayment.getSelectionModel().getSelectedItem() != null) {
                            Payment p1=new Payment(Long.parseLong(textField1.getText()),Double.parseDouble(textField4.getText()),new Vente(Long.parseLong(textField2.getText())),textField3.getText(),comboBoxType.getSelectionModel().getSelectedItem(),false);
                            p.delete(p1);
                            loadDataToTable();
                            // refreshContentProduit();
                            textField1.clear();textField2.clear();textField3.clear();textField4.clear();comboBoxType.getSelectionModel().clearSelection();
                        }}});
        buttonCategories.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCategorieScene(sceneManager);
                    }
                }
        );
        buttonProduits.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToProduitScene(sceneManager);
                    }
                }
        );
        buttonClients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToClientScene(sceneManager);
                    }
                }
        );
        buttonCommandes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCommandeScene(sceneManager);
                    }
                }
        );
        buttonPaiements.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToPaymentScene(sceneManager);
                    }
                }
        );
        buttonVentes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToVenteScene(sceneManager);
                    }
                }
        );
       /* buttonDisplay.setOnAction(
                new EventHandler<ActionEvent>() {

                    BorderPane root1=new BorderPane();

                    public void handle(ActionEvent actionEvent) {
                        try {
                            root1.setCenter(vbox);
                            createVboxrightproduit();
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initOwner(primaryStage);
                            stage.setTitle("My New Stage Title");
                            Scene newscene=new Scene(root1, 700, 700);
                            stage.setScene(newscene);
                            vbox.setPrefWidth(newscene.getWidth()*0.7);
                            tableViewProduct.prefHeightProperty().bind(vbox.heightProperty());
                            tableViewProduct.prefWidthProperty().bind(vbox.widthProperty());
                            stage.show();

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });*/

        tableViewPayment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tableViewPayment.getSelectionModel().getSelectedItem() != null)
                {
                    Payment prd = tableViewPayment.getSelectionModel().getSelectedItem();
                    setForm(prd);
                }
            }
        });

    }

    private void CreateHbox() {
        Label label=new Label("Gestion des Paiementes");
        label.setStyle("-fx-font: 20px Serif;"+"-fx-padding: 20;"+"-fx-text-fill:#414141;"+"-fx-font-weight:bold;");
        //label.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        Image imageProject = null;
        try {
            imageProject = new Image(new FileInputStream("picturesProject/project.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        label.setGraphic(new ImageView(imageProject));
        HboxTop.setSpacing(5);
        HboxTop.setStyle("-fx-background-color: #EBECEC;");
        HboxTop.getChildren().add(label);
        HboxTop.setAlignment(Pos.CENTER);
        HboxTop.setPrefWidth(1000);
        HboxTop.setPrefHeight(60);
    }
    private void createVboxright() {
        TableColumn<Payment, Long> Id_PaymentColon=new TableColumn<>("Id");
        Id_PaymentColon.setPrefWidth(60);
        Id_PaymentColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableViewPayment.getColumns().add(Id_PaymentColon);

        TableColumn<Payment, Long> VenteColon=new TableColumn<>("Vente");
        VenteColon.setPrefWidth(60);
        VenteColon.setCellValueFactory(new PropertyValueFactory<>("vente"));
        tableViewPayment.getColumns().add(VenteColon);

        TableColumn<Payment, Double> montantColon=new TableColumn<>("Montant");
        montantColon.setPrefWidth(60);
        montantColon.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tableViewPayment.getColumns().add(montantColon);

       /* TableColumn<Payment, Double> restColon=new TableColumn<>("rest");
        restColon.setPrefWidth(60);
        restColon.setCellValueFactory(new PropertyValueFactory<>("reste"));
        tableViewPayment.getColumns().add(restColon);*/

        TableColumn<Payment, String> TypeColon=new TableColumn<>("Type");
        TypeColon.setPrefWidth(100);
        TypeColon.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableViewPayment.getColumns().add(TypeColon);

       /* TableColumn<Payment, Boolean> payeColon=new TableColumn<>("Payed");
        payeColon.setPrefWidth(60);
        payeColon.setCellValueFactory(new PropertyValueFactory<>("payed"));
        tableViewPayment.getColumns().add(payeColon);*/

        TableColumn<Payment, DatePicker> DateColon=new TableColumn<>("Date");
        DateColon.setPrefWidth(150);
        DateColon.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableViewPayment.getColumns().add(DateColon);

        tableViewPayment.setStyle("-fx-background-color: #FFFFFF;"+"-fx-base: #AEAEB1;"+"-fx-control-inner-background: #484848;"+"-fx-table-cell-border-color: transparent;"+"-fx-table-header-border-color: transparent;"+"-fx-padding: 5;");
        Image imageSearch = null;
        try {
            imageSearch = new Image(new FileInputStream("picturesProject/search.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        filterFieldPayment.setPrefWidth(480);
        ImageView imageView=new ImageView(imageSearch);
        imageView.setTranslateY(4);
        textFlow.getChildren().addAll(imageView,filterFieldPayment);
        Vboxright.getChildren().addAll(textFlow);
        Vboxright.setPrefWidth(550);
        Vboxright.setPrefHeight(400);
        Vboxright.getChildren().add(tableViewPayment);
        loadDataToTable();
        //refreshContentProduit();
    }
    private void initPane() {
        try {
            CreateGridPane();
            CreateIcons();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root.setTop(HboxTop);
        root.setCenter(gridpane);
        root.setLeft(Vboxleft);
        root.setRight(Vboxright);
    }

    @Override
    public Scene init(double width, double height) {
        initPane();
        CreateHbox();
        CreateVboxleft();
        createVboxright();
        loadDataToTable();
        eventHandling();
        filteredList();
        PaymentScene=new Scene(root);
//        ProduitScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return PaymentScene;
    }
}
