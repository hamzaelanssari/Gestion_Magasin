package com.Forms;

import com.DAO_Database.DAO;
import com.IMPL.CategorieDAOIMPL;
import com.classes.Categorie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import scenes.SceneInterface;
import scenes.SceneManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FormHome implements SceneInterface {
    SceneManager sceneManager;
    Scene HomeScene;
    BorderPane root = new BorderPane();
    GridPane gridpane=new GridPane();
    HBox HboxTop=new HBox();
    GridPane SmallGridPane=new GridPane();
    Button buttonCategories=new Button("Categories");Button buttonProduits=new Button("Produits");
    Button buttonClients=new Button("Clients");Button buttonCommandes = new Button("Commandes");
    Button buttonVentes=new Button("Ventes");Button buttonPaiements = new Button("Paiements");

    public FormHome(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public Scene init(double width, double height){
        initPane();
        CreateHbox();
        eventHandling();
        HomeScene=new Scene(root);
        return HomeScene;
    }
    private void CreateIcons() throws FileNotFoundException {

        Image imageHome = new Image(new FileInputStream("picturesProject/home.png"));
        Image imageCategorize = new Image(new FileInputStream("picturesProject/categorize.png"));
        Image imageProduct = new Image(new FileInputStream("picturesProject/product.png"));
        Image imageCommande = new Image(new FileInputStream("picturesProject/commande.png"));
        Image imageCustomer = new Image(new FileInputStream("picturesProject/customer.png"));
        Image imageSale = new Image(new FileInputStream("picturesProject/sale.png"));
        Image imagePayment= new Image(new FileInputStream("picturesProject/payment.png"));

        buttonProduits.setGraphic(new ImageView(imageProduct));
        buttonCommandes.setGraphic(new ImageView(imageCommande));
        buttonVentes.setGraphic(new ImageView(imageSale));
        buttonClients.setGraphic(new ImageView(imageCustomer));
        buttonCategories.setGraphic(new ImageView(imageCategorize));
        buttonPaiements.setGraphic(new ImageView(imagePayment));
    }
    private void CreateGridPane(){
        buttonCategories.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #2A8AC9;");
        buttonProduits.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #2BC92A;");
        buttonClients.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #C92AC0;");
        buttonCommandes.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #C9832A;");
        buttonVentes.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #8D8D92;");
        buttonPaiements.setStyle("    -fx-font: 20px Serif;" +"    -fx-pref-height: 100;" + "    -fx-pref-width:   300;" + "    -fx-text-fill:white;" + "    -fx-background-color: #EE1A1A;");
        gridpane.getChildren().add(SmallGridPane);
        SmallGridPane.setTranslateX(50);SmallGridPane.setTranslateY(50);
        buttonVentes.setTranslateX(50);buttonVentes.setTranslateY(50);
        buttonPaiements.setTranslateX(450);buttonPaiements.setTranslateY(50);
        buttonProduits.setTranslateX(50);buttonProduits.setTranslateY(200);
        buttonClients.setTranslateX(450);buttonClients.setTranslateY(200);
        buttonCategories.setTranslateX(50);buttonCategories.setTranslateY(350);
        buttonCommandes.setTranslateX(450);buttonCommandes.setTranslateY(350);
        SmallGridPane.setPrefWidth(800);SmallGridPane.setPrefHeight(600);
        SmallGridPane.getChildren().addAll(buttonVentes,buttonPaiements,buttonProduits,buttonClients,buttonCategories,buttonCommandes);
    }
    private void eventHandling() {
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
        buttonVentes.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToVenteScene(sceneManager);
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
        buttonPaiements.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sceneManager.goToPaymentScene(sceneManager);
                    }
                }
        );

    }
    private void CreateHbox() {
        Label label=new Label("Gestion de Magasin");
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
    private void initPane() {
        try {
            CreateGridPane();
            CreateIcons();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root.setTop(HboxTop);
        root.setCenter(gridpane);
    }
}
