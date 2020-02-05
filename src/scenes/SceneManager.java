package scenes;


import com.Forms.*;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneManager {
    double window_width=900;
    double window_height=900;

    private Stage stage;
    private Timeline animation;

    public SceneManager(Stage stage) {
        this.stage = stage;
        animation=new Timeline();
        stage.show();
    }
    //go to Menu_Scene
   /* public void goToMenuScene(SceneManager sceneManager) {
        animation.stop();
        Menu menu = new Menu(sceneManager);
        Scene menuScene = menu.init(window_width,window_height);
        stage.setScene(menuScene);
    }*/
    public void goToCategorieScene(SceneManager sceneManager){
        animation.stop();
        FormCategorie formCategorie=new FormCategorie(sceneManager);
        Scene CategorieScene=formCategorie.init(window_width,window_height);
        stage.setScene(CategorieScene);
    }

    public void goToProduitScene(SceneManager sceneManager){
        animation.stop();
        FormProduit formProduit=new FormProduit(sceneManager);
        Scene ProduitScene=formProduit.init(window_width,window_height);
        stage.setScene(ProduitScene);
    }
    public void goToClientScene(SceneManager sceneManager){
        animation.stop();
        FormClient formClient=new FormClient(sceneManager);
        Scene ClientScene=formClient.init(window_width,window_height);
        stage.setScene(ClientScene);
    }
    public void goToCommandeScene(SceneManager sceneManager){
        animation.stop();
        FormCommande formCommand=new FormCommande(sceneManager);
        Scene CommandScene=formCommand.init(window_width,window_height);
        stage.setScene(CommandScene);
    }
    public void goToPaymentScene(SceneManager sceneManager){
        animation.stop();
        FormPayment formPayment=new FormPayment(sceneManager);
        Scene PaymentScene=formPayment.init(window_width,window_height);
        stage.setScene(PaymentScene);
    }
    public void goToHomeScene(SceneManager sceneManager){
        animation.stop();
        FormHome formHome=new FormHome(sceneManager);
        Scene HomeScene=formHome.init(window_width,window_height);
        stage.setScene(HomeScene);
    }
    public void goToVenteScene(SceneManager sceneManager){
        animation.stop();
        FormVente formVente=new FormVente(sceneManager);
        Scene VenteScene=formVente.init(window_width,window_height);
        stage.setScene(VenteScene);
    }
}
