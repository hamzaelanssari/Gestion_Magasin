package com.Forms;


import com.IMPL.CategorieDAOIMPL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import scenes.SceneManager;
import com.DAO_Database.DAO;
import com.classes.Categorie;
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
import javafx.scene.input.KeyEvent;
import scenes.SceneInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
public class FormCategorie  implements SceneInterface{

    SceneManager sceneManager;
    Scene CategorieScene;
    BorderPane root = new BorderPane();
    GridPane gridpane=new GridPane();
    GridPane SmallGridPane=new GridPane();
    HBox HboxTop =new HBox();
    VBox Vboxleft =new VBox();
    VBox Vboxright =new VBox();

    // List<Categorie> categories=new ArrayList<>();
    TableView<Categorie> tableViewCategories=new TableView<>();
    //liste
    ObservableList<Categorie> observableTable= FXCollections.observableArrayList();
    Button buttonAdd=new Button("Ajouter");Button buttonModifier=new Button("Modifier");
    Button buttonDelete=new Button("Supprimer");Button buttonRefresh = new Button("Refresh");

    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");
    Button buttonHome=new Button("Home");

    TextField textFieldTitle = new TextField();
    TextField filterField=new TextField();
    TextField textFieldid_Categorie=new TextField();
    DAO categorie=new CategorieDAOIMPL();
    TextFlow textFlow=new TextFlow();
    public FormCategorie(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }


    //Creating a Grid Pane
    //Setting size for the pane
    private void filterData(String str) {
        tableViewCategories.getItems().clear();
        List<Categorie> filteredList =  categorie.findAll(str);
        if(filteredList !=null) {//tableViewProduct.getItems().addAll(filteredList);
            observableTable.setAll(filteredList);
            tableViewCategories.setItems(observableTable);}
    }

    private void filteredList(){
        filterField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(filterField.getText().length()!=0) {
                    filterData(filterField.getText());
                }
                else refreshContent();
            }
        });
    }

    private void CreateVboxleft(){
        Vboxleft.setStyle("-fx-background-color: #777777;");
        Vboxleft.setSpacing(10);
        Vboxleft.setPrefWidth(250);
        buttonHome.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonCategories.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;");
        buttonProduits.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonClients.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonCommandes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonVentes.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        buttonPaiements.setStyle("    -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777777;");
        Vboxleft.getChildren().addAll(buttonHome,buttonCategories,buttonProduits,buttonClients,buttonCommandes,buttonVentes,buttonPaiements);
        /*buttonCategories.setOnMouseClicked(e->buttonCategories.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
        buttonProduits.setOnMouseClicked(e->buttonProduits.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
        buttonClients.setOnMouseClicked(e->buttonClients.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
        buttonCommandes.setOnMouseClicked(e->buttonCommandes.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
        buttonVentes.setOnMouseClicked(e->buttonVentes.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
        buttonPaiements.setOnMouseClicked(e->buttonPaiements.setStyle( "   -fx-font: 15px Serif;" +"    -fx-pref-height: 20;" + "    -fx-pref-width:   250;" + "    -fx-text-fill:white;" + "    -fx-background-color: #777FFF;"));
*/
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
    private void CreateGridPane() throws FileNotFoundException{
        //creating label id/designation/prix
        buttonAdd.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width:   100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonModifier.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 100;" + "    -fx-text-fill:white;" + "    -fx-background-color: #004080;");
        buttonDelete.setStyle("    -fx-font: 13px Serif;" +"    -fx-pref-height: 10;" + "    -fx-pref-width: 110;" + "    -fx-text-fill:white;" + "   -fx-background-color: #004080;");
        buttonAdd.setTranslateX(10);buttonDelete.setTranslateX(250);buttonModifier.setTranslateX(130);
        buttonAdd.setTranslateY(110);buttonModifier.setTranslateY(110);buttonDelete.setTranslateY(110);

        Image imageAdd = new Image(new FileInputStream("picturesProject/plus.png"));
        Image imageDelete = new Image(new FileInputStream("picturesProject/delete.png"));
        Image imageUpdate= new Image(new FileInputStream("picturesProject/update.png"));
        buttonAdd.setGraphic(new ImageView(imageAdd));
        buttonDelete.setGraphic(new ImageView(imageDelete));
        buttonModifier.setGraphic(new ImageView(imageUpdate));

        textFieldTitle.setPromptText("Title");
        Label title=new Label("title");
        title.setStyle("-fx-font: 15px Serif;"+"-fx-padding: 5;"+"-fx-text-fill:black;"+"-fx-font-weight:bold;");
        //title.setTranslateX(100);title.setTranslateY(80);textFieldTitle.setTranslateX(150);textFieldTitle.setTranslateY(80);
        gridpane.setStyle("-fx-background-color: white;");
        /*gridpane.setVgap(10);
        gridpane.setHgap(10);#13D3C2*/
        SmallGridPane.setStyle("-fx-background-color:#EBECEC;"+"-fx-border-style: solid;"+"-fx-border-width: 2;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;"+ "-fx-border-color: #5D5D5E;");
        SmallGridPane.addRow(1,title,textFieldTitle);
        SmallGridPane.setTranslateX(10);
        SmallGridPane.setTranslateY(30);
        SmallGridPane.getChildren().addAll(buttonAdd,buttonModifier,buttonDelete);
        SmallGridPane.setPrefWidth(380);
        SmallGridPane.setPrefHeight(200);
        gridpane.setPrefHeight(400);
        gridpane.setPrefWidth(400);
        gridpane.getChildren().add(SmallGridPane);
        //Image image=new Image(getClass().getResourceAsStream());
        //buttonAdd.setGraphic(new ImageView());

    }
    private void setForm(Categorie p) {
        textFieldTitle.setText(p.getTitle());
        textFieldid_Categorie.setText(String.valueOf(p.getId()));

    }
    public void refreshContent() {
        observableTable.setAll(categorie.findAll());
        tableViewCategories.setItems(observableTable);
    }
    private void eventHandling() {
        buttonAdd.setOnAction
                (
                        new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent action){
                                categorie.create(new Categorie(textFieldTitle.getText()));
                                refreshContent();
                                textFieldTitle.clear();
                            }});
        buttonModifier.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        categorie.update(new Categorie(Long.parseLong(textFieldid_Categorie.getText()),textFieldTitle.getText()));
                        refreshContent();
                        textFieldTitle.clear();
                    }});
        buttonDelete.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent action){
                        categorie.delete(new Categorie(Long.parseLong(textFieldid_Categorie.getText()),textFieldTitle.getText()));
                        refreshContent();
                        textFieldTitle.clear();
                    }});
        buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent action) {
                refreshContent();
            }
        });
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
        buttonCategories.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToCategorieScene(sceneManager);
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
        buttonHome.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToHomeScene(sceneManager);
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
        tableViewCategories.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(tableViewCategories.getSelectionModel().getSelectedItem() != null)
                {
                    Categorie cat = tableViewCategories.getSelectionModel().getSelectedItem();
                    setForm(cat);
                }
            }
        });
    }
    private void CreateHbox() {
        Label label=new Label("Gestion des Categories");
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
        TableColumn<Categorie, Long> IdColon=new TableColumn<>("Id_Categorie");
        IdColon.setPrefWidth(150);
        IdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableViewCategories.getColumns().add(IdColon);

        TableColumn<Categorie, Long> TitleColon=new TableColumn<>("Title");
        TitleColon.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableViewCategories.getColumns().add(TitleColon);
        // IdColon.setStyle("-fx-background-color: transparent;");
        //TitleColon.setStyle("-fx-background-color: transparent;");
        TitleColon.setPrefWidth(200);
        Image imageSearch = null;
        try {
            imageSearch = new Image(new FileInputStream("picturesProject/search.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tableViewCategories.setStyle("-fx-background-color: #FFFFFF;"+"-fx-base: #AEAEB1;"+"-fx-control-inner-background: #484848;"+"-fx-table-cell-border-color: transparent;"+"-fx-table-header-border-color: transparent;"+"-fx-padding: 5;");
        filterField.setPrefWidth(330);
        ImageView imageView=new ImageView(imageSearch);
        imageView.setTranslateY(4);
        textFlow.getChildren().addAll(imageView,filterField);
        Vboxright.getChildren().addAll(textFlow);
        Vboxright.getChildren().add(tableViewCategories);
        Vboxright.setPrefWidth(350);
        Vboxright.setPrefHeight(300);
        refreshContent() ;
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
        root.setRight(Vboxright);
        root.setLeft(Vboxleft);
    }

    @Override
    public Scene init(double width, double height){
        initPane();
        CreateHbox();
        CreateVboxleft();
        createVboxright();
        eventHandling();
        filteredList();
        CategorieScene=new Scene(root);
        return CategorieScene;
    }
}
