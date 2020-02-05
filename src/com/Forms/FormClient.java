package com.Forms;

import com.DAO_Database.DAO;
import com.IMPL.ClientDAOIMPL;
import com.classes.Categorie;
import com.classes.Client;
import com.classes.Produit;
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
import scenes.SceneInterface;
import scenes.SceneManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FormClient implements SceneInterface {
    SceneManager sceneManager;
    Scene ClientScene;
    BorderPane root = new BorderPane();
    GridPane gridpane = new GridPane();
    GridPane SmallGridPane = new GridPane();
    HBox HboxTop = new HBox();
    VBox Vboxright = new VBox();
    VBox Vboxleft = new VBox();
    List<Client> clients = new ArrayList<>();
    TableView<Client> tableViewClients = new TableView<>();
    //liste
    ObservableList<Client> observableTable = FXCollections.observableArrayList();
    Button buttonAdd = new Button("Ajouter");
    Button buttonModifier = new Button("Modifier");
    Button buttonDelete = new Button("Supprimer");

    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");
    Button buttonHome=new Button("Home");


    TextFlow textFlow=new TextFlow();
    TextField textField1 = new TextField();TextField textField2 = new TextField();TextField textField3 = new TextField();
    TextField textField4 = new TextField();TextField textField5 = new TextField();TextField textField6 = new TextField();
    TextField filterField = new TextField();
    DAO client = new ClientDAOIMPL();


    //Creating a Grid Pane
    //Setting size for the pane
    public FormClient(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    private void filterData(String str) {
        tableViewClients.getItems().clear();
        List<Client> filteredList = client.findAll(str);
        if (filteredList != null) {//tableViewProduct.getItems().addAll(filteredList);
            observableTable.setAll(filteredList);
            tableViewClients.setItems(observableTable);
        }
    }

    private void filteredList() {
        filterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (filterField.getText().length() != 0) {
                    filterData(filterField.getText());
                } else loadDataToTable();
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
        buttonClients.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;");
        buttonCommandes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonVentes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonPaiements.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        Vboxleft.getChildren().addAll(buttonHome,buttonCategories,buttonProduits,buttonClients,buttonCommandes,buttonVentes,buttonPaiements);

    }
    public void loadDataToTable() {
        tableViewClients.getItems().clear();
        List<Client> t = client.findAll();
        if (t != null)
            tableViewClients.getItems().addAll(t);
    }
    private void CreateGridPane() throws  FileNotFoundException{
        //creating label id/designation/prix
        textField1.setPromptText("Id");
        textField2.setPromptText("Nom");
        textField3.setPromptText("Prenom");
        textField4.setPromptText("Email");
        textField5.setPromptText("Adresse");
        textField6.setPromptText("Telephone");
        Label id = new Label("Id_Client");
        Label nom = new Label("Nom");
        Label prenom = new Label("Prenom");
        Label email = new Label("Email");
        Label adresse = new Label("Adresse");
        Label telephone = new Label("Telephone");

        id.setStyle("-fx-font: 15px Serif ;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");
        nom.setStyle("-fx-font: 15px Serif;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");
        prenom.setStyle("-fx-font: 15px Serif;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");
        email.setStyle("-fx-font: 16px Serif ;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");
        adresse.setStyle("-fx-font: 15px Serif;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");
        telephone.setStyle("-fx-font: 15px Serif ;-fx-padding: 5;-fx-text-fill:black; -fx-font-weight:bold;");


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
       // gridpane.setVgap(10);
        //gridpane.setHgap(10);
        SmallGridPane.setStyle("-fx-background-color:#EBECEC;"+"-fx-border-style: solid;"+"-fx-border-width: 2;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;"+ "-fx-border-color: #5D5D5E;");
        SmallGridPane.addRow(1, nom, textField2);
        SmallGridPane.addRow(2, prenom, textField3);
        SmallGridPane.addRow(3, email, textField4);
        SmallGridPane.addRow(4, adresse, textField5);
        SmallGridPane.addRow(5, telephone, textField6);
        SmallGridPane.setTranslateX(10);
        SmallGridPane.setTranslateY(30);
        SmallGridPane.getChildren().addAll(buttonAdd,buttonModifier,buttonDelete);
        SmallGridPane.setPrefWidth(380);
        SmallGridPane.setPrefHeight(280);
        gridpane.setPrefHeight(400);
        gridpane.setPrefWidth(400);
        gridpane.getChildren().add(SmallGridPane);

    }

    private void setForm(Client client) {
        textField1.setText(Long.toString(client.getId_client()));
        textField2.setText(client.getNom());
        textField3.setText(client.getPrenom());
        textField4.setText(client.getEmail());
        textField5.setText(client.getAdresse());
        textField6.setText(client.getTele());

    }

    private void eventHandling() {
        buttonAdd.setOnAction
                (
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent action) {
                                client.create(new Client(textField2.getText(), textField3.getText(), textField4.getText(), textField6.getText(), textField5.getText()));
                                loadDataToTable();
                                textField1.clear();textField2.clear();textField3.clear();
                                textField4.clear();textField5.clear();textField6.clear();
                            }
                        });
        buttonModifier.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action) {
                        client.update(new Client(Long.parseLong(textField1.getText()),textField2.getText(), textField3.getText(), textField4.getText(), textField6.getText(), textField5.getText()));
                        loadDataToTable();
                        textField1.clear();textField2.clear();textField3.clear();
                        textField4.clear();textField5.clear();textField6.clear();
                    }
                });
        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action) {
                        client.delete(new Client(Long.parseLong(textField1.getText()),textField2.getText(), textField3.getText(), textField4.getText(), textField6.getText(), textField5.getText()));
                        loadDataToTable();
                        textField1.clear();textField2.clear();textField3.clear();
                        textField4.clear();textField5.clear();textField6.clear();


                    }
                });
        buttonCommandes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCommandeScene(sceneManager);
                    }
                }
        );
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
        buttonPaiements.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToPaymentScene(sceneManager);
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
        buttonHome.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToHomeScene(sceneManager);
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

        tableViewClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableViewClients.getSelectionModel().getSelectedItem() != null) {
                    Client clt = tableViewClients.getSelectionModel().getSelectedItem();
                    setForm(clt);
                }
            }
        });
    }
    private void CreateHbox() {
        Label label=new Label("Gestion des Clients");
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
        HboxTop.setPrefWidth(900);
        HboxTop.setPrefHeight(60);
    }
    private void createVboxright() {
        TableColumn<Client, Long> IdColon = new TableColumn<>("Id");
        IdColon.setPrefWidth(50);
        IdColon.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tableViewClients.getColumns().add(IdColon);

        TableColumn<Client, Long> NomColon = new TableColumn<>("Nom");
        NomColon.setPrefWidth(100);
        NomColon.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableViewClients.getColumns().add(NomColon);

        TableColumn<Client, Long> PrenomColon = new TableColumn<>("Prenom");
        PrenomColon.setPrefWidth(100);
        PrenomColon.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tableViewClients.getColumns().add(PrenomColon);

        TableColumn<Client, Long> EmailColon = new TableColumn<>("Email");
        EmailColon.setPrefWidth(100);
        EmailColon.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewClients.getColumns().add(EmailColon);

        TableColumn<Client, Long> AdresseColon = new TableColumn<>("Adresse");
        AdresseColon.setPrefWidth(100);
        AdresseColon.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tableViewClients.getColumns().add(AdresseColon);

        TableColumn<Client, Long> TeleColon = new TableColumn<>("Telephone");
        TeleColon.setPrefWidth(100);
        TeleColon.setCellValueFactory(new PropertyValueFactory<>("tele"));
        tableViewClients.getColumns().add(TeleColon);

        tableViewClients.setStyle("-fx-background-color: #FFFFFF;"+"-fx-base: #AEAEB1;"+"-fx-control-inner-background: #484848;"+"-fx-table-cell-border-color: transparent;"+"-fx-table-header-border-color: transparent;"+"-fx-padding: 5;");
        Image imageSearch = null;
        try {
            imageSearch = new Image(new FileInputStream("picturesProject/search.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        filterField.setPrefWidth(530);
        ImageView imageView=new ImageView(imageSearch);
        imageView.setTranslateY(4);
        textFlow.getChildren().addAll(imageView,filterField);
        Vboxright.getChildren().addAll(textFlow);
        Vboxright.setPrefWidth(550);
        Vboxright.setPrefHeight(400);
        Vboxright.getChildren().add(tableViewClients);
        loadDataToTable();
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
    public Scene init(double width, double height){
        initPane();
        CreateHbox();
        CreateVboxleft();
        createVboxright();
        loadDataToTable();
        eventHandling();
        filteredList();
        ClientScene=new Scene(root);
//        ProduitScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return ClientScene;
    }
}
