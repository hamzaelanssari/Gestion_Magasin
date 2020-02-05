package scenes;

import javafx.scene.Scene;

import java.io.FileNotFoundException;

public interface SceneInterface {
    Scene init(double width, double height) throws FileNotFoundException;
}
