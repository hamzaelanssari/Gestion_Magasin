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
import java.util.List;


public class FormProduit implements SceneInterface {

    SceneManager sceneManager;

    public FormProduit(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    Scene ProduitScene;
    BorderPane root = new BorderPane();
    GridPane gridpane=new GridPane();
    GridPane SmallGridPane=new GridPane();
    HBox HboxTop =new HBox();
    VBox Vboxright =new VBox();
    VBox Vboxleft=new VBox();

    TableView<Produit>tableViewProduct=new TableView<>();
    ObservableList<Produit> observableTableProduits=FXCollections.observableArrayList();
    private ComboBox<Categorie>comboBox=new ComboBox<>();

    Button buttonAdd=new Button("Ajouter");
    Button buttonModifier=new Button("Modifier");
    Button buttonDelete=new Button("Supprimer");
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    TextField filterFieldProduit=new TextField();
    DAO<Produit> p=new ProduitDAOIMPL();
    DAO<Categorie>categorieDAO=new CategorieDAOIMPL();
    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");
    Button buttonHome=new Button("Home");
    TextFlow textFlow=new TextFlow();

    //Creating a Grid Pane
    //Setting size for the pane
    private void filterData(String str) {
        tableViewProduct.getItems().clear();
        List<Produit> filteredList =  p.findAll(str);
        if(filteredList !=null) {//tableViewProduct.getItems().addAll(filteredList);
            observableTableProduits.setAll(filteredList);
            tableViewProduct.setItems(observableTableProduits);}


    }

    private void filteredList(){
        filterFieldProduit.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(filterFieldProduit.getText().length()!=0) {
                    filterData(filterFieldProduit.getText());
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
        buttonProduits.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;");
        buttonClients.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonCommandes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonVentes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonPaiements.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        Vboxleft.getChildren().addAll(buttonHome,buttonCategories,buttonProduits,buttonClients,buttonCommandes,buttonVentes,buttonPaiements);

    }
    private void ComboBoxTitle(){
        Callback<ListView<Categorie>, ListCell<Categorie>> factory = lv -> new ListCell<Categorie>() {

            @Override
            protected void updateItem(Categorie item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTitle());
            }

        };

        comboBox.setCellFactory(factory);
        comboBox.setButtonCell(factory.call(null));
    }
    private void CreateGridPane() throws FileNotFoundException{
        //creating label id/designation/prix
        textField2.setPromptText("designation");
        textField3.setPromptText("prix");
        textField4.setPromptText("categorie");
        Label designation=new Label("designation");
        Label prix=new Label("prix");
        Label categorie=new Label("categorie");

        designation.setStyle("-fx-font: 15px Serif;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
        prix.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+" -fx-font-weight:bold;");
        categorie.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
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
        comboBox.getItems().addAll(categorieDAO.findAll());
        SmallGridPane.setStyle("-fx-background-color:#EBECEC;"+"-fx-border-style: solid;"+"-fx-border-width: 2;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;"+ "-fx-border-color: #5D5D5E;");
        SmallGridPane.addRow(1,designation,textField2);
        SmallGridPane.addRow(2,prix,textField3);
        ComboBoxTitle();
        //comboBox.getSelectionModel().selectFirst();

        SmallGridPane.addRow(3  ,categorie,  comboBox);
        comboBox.getSelectionModel().selectFirst();
        SmallGridPane.setTranslateX(10);
        SmallGridPane.setTranslateY(30);
        SmallGridPane.getChildren().addAll(buttonAdd,buttonModifier,buttonDelete);
        SmallGridPane.setPrefWidth(380);
        SmallGridPane.setPrefHeight(280);
        gridpane.setPrefHeight(400);
        gridpane.setPrefWidth(400);
        gridpane.getChildren().add(SmallGridPane);
    }

    private void setForm(Produit product) {
        textField1.setText(Long.toString(product.getId()));
        textField2.setText(product.getDesignation());
        textField3.setText(Double.toString(product.getPrix()));
        comboBox.getSelectionModel().select(product.getCategorie());
    }
   /* public void refreshContentProduit() {

        observableTableProduits.setAll(p.findAll());
        tableViewProduct.setItems(observableTableProduits);

    }*/
    public void loadDataToTable() {
        tableViewProduct.getItems().clear();
        List<Produit> t = p.findAll();
        if (t != null)
            tableViewProduct.getItems().addAll(t);
        comboBox.getItems().clear();
        List<Categorie> p = categorieDAO.findAll();
        if(p!=null)
           comboBox.getItems().addAll(p);
    }
    private boolean isProduitFieldsFilled(){
        return textField2.getText().length() > 0 && textField3.getText().length() > 0;
    }
    private void eventHandling() {
        buttonAdd.setOnAction
                (
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent action){
                                if (isProduitFieldsFilled() && tableViewProduct.getSelectionModel().getSelectedItem() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
                                    p.create(new Produit(textField2.getText(), Double.parseDouble(textField3.getText()),comboBox.getSelectionModel().getSelectedItem()));
                                    loadDataToTable();
                                    //refreshContentProduit();
                                    textField1.clear();textField2.clear();textField3.clear();textField4.clear();
                                    comboBox.getSelectionModel().clearSelection();
                                }}});
        buttonModifier.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        if (isProduitFieldsFilled() && tableViewProduct.getSelectionModel().getSelectedItem() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
                        p.update(new Produit(Long.parseLong(textField1.getText()),textField2.getText(),Double.parseDouble(textField3.getText()),comboBox.getSelectionModel().getSelectedItem()));
                        loadDataToTable();
                        //refreshContentProduit();
                        textField1.clear();textField2.clear();textField3.clear();textField4.clear();comboBox.getSelectionModel().clearSelection();
                    }}});
        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        if (isProduitFieldsFilled() && tableViewProduct.getSelectionModel().getSelectedItem() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
                        p.delete(new Produit(Long.parseLong(textField1.getText()),textField2.getText(),Double.parseDouble(textField3.getText()),comboBox.getSelectionModel().getSelectedItem()));
                       loadDataToTable();
                        // refreshContentProduit();
                        textField1.clear();textField2.clear();textField3.clear();textField4.clear();comboBox.getSelectionModel().clearSelection();
                    }}});
        buttonCategories.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCategorieScene(sceneManager);
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
        buttonProduits.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToProduitScene(sceneManager);
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
        buttonClients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToClientScene(sceneManager);
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
        buttonCommandes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCommandeScene(sceneManager);
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

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tableViewProduct.getSelectionModel().getSelectedItem() != null)
                {
                    Produit prd = tableViewProduct.getSelectionModel().getSelectedItem();
                    setForm(prd);
                }
            }
        });

    }

    private void CreateHbox() {
        Label label=new Label("Gestion des Produits");
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
        TableColumn<Produit, Long> Id_ProduitColon=new TableColumn<>("Id");
        Id_ProduitColon.setPrefWidth(60);
        Id_ProduitColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableViewProduct.getColumns().add(Id_ProduitColon);

        TableColumn<Produit, String> DesignationColon=new TableColumn<>("Designation");
        DesignationColon.setPrefWidth(115);
        DesignationColon.setCellValueFactory(new PropertyValueFactory<>("designation"));
        tableViewProduct.getColumns().add(DesignationColon);

        TableColumn<Produit, Double> prix_venteColon=new TableColumn<>("Prix");
        prix_venteColon.setPrefWidth(60);
        prix_venteColon.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableViewProduct.getColumns().add(prix_venteColon);
        TableColumn<Produit, String> CategorieColon=new TableColumn<>("Categorie");
        CategorieColon.setPrefWidth(115);
        CategorieColon.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tableViewProduct.getColumns().add(CategorieColon);
        tableViewProduct.setStyle("-fx-background-color: #FFFFFF;"+"-fx-base: #AEAEB1;"+"-fx-control-inner-background: #484848;"+"-fx-table-cell-border-color: transparent;"+"-fx-table-header-border-color: transparent;"+"-fx-padding: 5;");
        Image imageSearch = null;
        try {
            imageSearch = new Image(new FileInputStream("picturesProject/search.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        filterFieldProduit.setPrefWidth(330);
        ImageView imageView=new ImageView(imageSearch);
        imageView.setTranslateY(4);
        textFlow.getChildren().addAll(imageView,filterFieldProduit);
        Vboxright.getChildren().addAll(textFlow);
        Vboxright.setPrefWidth(350);
        Vboxright.setPrefHeight(400);
        Vboxright.getChildren().add(tableViewProduct);
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
        ProduitScene=new Scene(root);
//        ProduitScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return ProduitScene;
    }
}
