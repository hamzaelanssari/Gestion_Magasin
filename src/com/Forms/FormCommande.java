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
import javafx.scene.Node;
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
import java.util.Properties;


public class FormCommande implements SceneInterface {

    SceneManager sceneManager;

    public FormCommande(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    Scene CommandeScene;
    BorderPane root = new BorderPane();
    GridPane gridpane=new GridPane();
    GridPane SmallGridPane=new GridPane();
    HBox HboxTop =new HBox();
    VBox Vboxright =new VBox();
    VBox Vboxleft=new VBox();
    Label Total=new Label("Total : ");
    TableView<Commande>tableViewCommande=new TableView<>();
    ObservableList<Commande> observableTableCommandes=FXCollections.observableArrayList();
    private ComboBox<Produit>comboBox=new ComboBox<>();

    Button buttonAdd=new Button("Ajouter");
    Button buttonModifier=new Button("Modifier");
    Button buttonDelete=new Button("Supprimer");
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    TextField textField5 = new TextField();
    TextField filterFieldCommande=new TextField();
    DAO<Commande> p=new CommandeDAOIMPL();
    DAO<Produit>produitDAO=new ProduitDAOIMPL();
    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");
    Button buttonHome=new Button("Home");
    TextFlow textFlow=new TextFlow();

    //Creating a Grid Pane
    //Setting size for the pane
    private void filterData(String str) {
        tableViewCommande.getItems().clear();
        List<Commande> filteredList =  p.findAll(str);
        if(filteredList !=null) {//tableViewProduct.getItems().addAll(filteredList);
            observableTableCommandes.setAll(filteredList);
            tableViewCommande.setItems(observableTableCommandes);}


    }

    private void filteredList(){
        filterFieldCommande.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(filterFieldCommande.getText().length()!=0) {
                    filterData(filterFieldCommande.getText());
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
        buttonCommandes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;");
        buttonVentes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonPaiements.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        Vboxleft.getChildren().addAll(buttonHome,buttonCategories,buttonProduits,buttonClients,buttonCommandes,buttonVentes,buttonPaiements);

    }
    /*private void ComboBoxTitle(){
         Callback<ListView<Produit>, ListCell<Produit>> factory = lv -> new ListCell<Produit>() {

             @Override
             protected void updateItem(Produit item, boolean empty) {
                 super.updateItem(item, empty);
                 setText(empty ? "" :item.getDesignation());
             }

         };

         comboBox.setCellFactory(factory);
         comboBox.setButtonCell(factory.call(null));
     }*/
    private void CreateGridPane() throws FileNotFoundException{
        //creating label id/designation/prix
        textField4.setPromptText("Quantite");
        textField3.setPromptText("Id_Vente");
        textField5.setDisable(true);
        Label Quantite=new Label("Quantite");
        Label Vente=new Label("Vente");
        Label Produit=new Label("Produit");


        Quantite.setStyle("-fx-font: 15px Serif;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
        Vente.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+" -fx-font-weight:bold;");
        Produit.setStyle("-fx-font: 15px Serif ;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");

        //Creating Text Filed for id/designation/prix


        buttonAdd.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width:   100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonModifier.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonDelete.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 110;" + "    -fx-text-fill:white;" + "   -fx-background-color: #004080;");
        buttonAdd.setTranslateX(10);buttonDelete.setTranslateX(250);buttonModifier.setTranslateX(130);
        buttonAdd.setTranslateY(210);buttonModifier.setTranslateY(210);buttonDelete.setTranslateY(210);
        Total.setTranslateX(10);Total.setTranslateY(130);
        Total.setStyle("-fx-font: 15px Serif;" +"    -fx-pref-height: 30;" + "    -fx-pref-width:  200;" + "    -fx-text-fill:white;" + "    -fx-background-color:#2BC92A;");
        Image imageAdd = new Image(new FileInputStream("picturesProject/plus.png"));
        Image imageDelete = new Image(new FileInputStream("picturesProject/delete.png"));
        Image imageUpdate= new Image(new FileInputStream("picturesProject/update.png"));
        buttonAdd.setGraphic(new ImageView(imageAdd));
        buttonDelete.setGraphic(new ImageView(imageDelete));
        buttonModifier.setGraphic(new ImageView(imageUpdate));

        gridpane.setStyle("-fx-background-color: white;");
        /*gridpane.setVgap(10);
        gridpane.setHgap(10);#13D3C2*/
        comboBox.getItems().addAll(produitDAO.findAll());
        SmallGridPane.setStyle("-fx-background-color:#EBECEC;"+"-fx-border-style: solid;"+"-fx-border-width: 2;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;"+ "-fx-border-color: #5D5D5E;");
        SmallGridPane.addRow(4,Quantite,textField4);
        SmallGridPane.addRow(2,Vente,textField3);

        comboBox.getSelectionModel().selectFirst();

        SmallGridPane.addRow(3  ,Produit,  comboBox);
        comboBox.getSelectionModel().selectFirst();
        SmallGridPane.setTranslateX(10);
        SmallGridPane.setTranslateY(30);
        SmallGridPane.getChildren().addAll(buttonAdd,buttonModifier,buttonDelete,Total);
        SmallGridPane.setPrefWidth(380);
        SmallGridPane.setPrefHeight(280);
        gridpane.setPrefHeight(400);
        gridpane.setPrefWidth(400);
        gridpane.getChildren().add(SmallGridPane);
    }

    private void setForm(Commande p) {
        textField1.setText(Long.toString(p.getId()));
        textField4.setText(Double.toString(p.getQuantite()));
        comboBox.getSelectionModel().select(p.getProduit());
        textField3.setText(Long.toString(p.getVente_id()));
        Total.setText("Total :"+p.getSousTotal());
    }
    /* public void refreshContentProduit() {

         observableTableProduits.setAll(p.findAll());
         tableViewProduct.setItems(observableTableProduits);

     }*/
    public void loadDataToTable() {
        tableViewCommande.getItems().clear();
        List<Commande> t = p.findAll();
        if (t != null){
            tableViewCommande.getItems().addAll(t);
        }
        comboBox.getItems().clear();
        List<Produit> p = produitDAO.findAll();
        if(p!=null){
            comboBox.getItems().addAll(p);
            comboBox.getSelectionModel().selectFirst();
        }
    }
    private boolean isProduitFieldsFilled(){
        return  textField4.getText().length() > 0 && textField3.getText().length() > 0;
    }
    private void eventHandling() {
        buttonAdd.setOnAction
                (
                        new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent action){
                                Produit tp = comboBox.getSelectionModel().getSelectedItem();

                                Commande c=new Commande(1,tp,Double.parseDouble(textField4.getText()),Long.parseLong(textField3.getText()));
                                System.out.println(c.getId());
                                p.create(c);
                                loadDataToTable();
                                comboBox.getSelectionModel().clearSelection();
                                //refreshContentProduit();
                                textField1.clear();textField2.clear();textField3.clear();textField4.clear();textField5.clear();
                            }});
        buttonModifier.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        if (isProduitFieldsFilled() && tableViewCommande.getSelectionModel().getSelectedItem() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
                            Commande c=new Commande(Long.parseLong(textField1.getText()),comboBox.getSelectionModel().getSelectedItem(),Double.parseDouble(textField4.getText()),Long.parseLong(textField3.getText()));
                            //p.update(new Commande(Long.parseLong(textField1.getText()),comboBox.getSelectionModel().getSelectedItem(),Long.parseLong(textField4.getText())));
                            p.update(c);
                            loadDataToTable();
                            //refreshContentProduit();
                            textField1.clear();textField2.clear();textField4.clear();textField3.clear();textField5.clear();
                            comboBox.getSelectionModel().clearSelection();
                        }}});
        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        if (isProduitFieldsFilled() && tableViewCommande.getSelectionModel().getSelectedItem() != null && comboBox.getSelectionModel().getSelectedItem() != null) {
                            Commande c=new Commande(Long.parseLong(textField1.getText()),comboBox.getSelectionModel().getSelectedItem(),Double.parseDouble(textField4.getText()),Long.parseLong(textField3.getText()));
                            p.delete(c);
                            loadDataToTable();
                            // refreshContentProduit();
                            comboBox.getSelectionModel().clearSelection();
                            textField1.clear();textField2.clear();textField4.clear();textField3.clear();textField5.clear();
                        }}});
        buttonHome.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToHomeScene(sceneManager);
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
        buttonVentes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToVenteScene(sceneManager);
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
        buttonClients.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToClientScene(sceneManager);
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

        tableViewCommande.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tableViewCommande.getSelectionModel().getSelectedItem() != null)
                {
                    Commande prd = tableViewCommande.getSelectionModel().getSelectedItem();
                    setForm(prd);
                }
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
        TableColumn<Commande, Long> Id_ProduitColon=new TableColumn<>("Id");
        Id_ProduitColon.setPrefWidth(50);
        Id_ProduitColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableViewCommande.getColumns().add(Id_ProduitColon);

        TableColumn<Commande, Produit> DesignationColon=new TableColumn<>("Produit");
        DesignationColon.setPrefWidth(100);
        DesignationColon.setCellValueFactory(new PropertyValueFactory<>("produit"));
        tableViewCommande.getColumns().add(DesignationColon);

        TableColumn<Commande, Long> CategorieColon=new TableColumn<>("Quantite");
        CategorieColon.setPrefWidth(100);
        CategorieColon.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tableViewCommande.getColumns().add(CategorieColon);

        TableColumn<Commande, Vente> VenteColon=new TableColumn<>("Vente");
        VenteColon.setPrefWidth(100);
        VenteColon.setCellValueFactory(new PropertyValueFactory<>("vente_id"));
        tableViewCommande.getColumns().add(VenteColon);

        tableViewCommande.setStyle("-fx-background-color: #FFFFFF;"+"-fx-base: #AEAEB1;"+"-fx-control-inner-background: #484848;"+"-fx-table-cell-border-color: transparent;"+"-fx-table-header-border-color: transparent;"+"-fx-padding: 5;");
        Image imageSearch = null;
        try {
            imageSearch = new Image(new FileInputStream("picturesProject/search.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        filterFieldCommande.setPrefWidth(330);
        ImageView imageView=new ImageView(imageSearch);
        imageView.setTranslateY(4);
        textFlow.getChildren().addAll(imageView,filterFieldCommande);
        Vboxright.getChildren().addAll(textFlow);
        Vboxright.setPrefWidth(350);
        Vboxright.setPrefHeight(400);
        Vboxright.getChildren().add(tableViewCommande);
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
        CommandeScene=new Scene(root);
//        ProduitScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return CommandeScene;
    }
}
