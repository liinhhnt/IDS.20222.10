package view.screen;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLScreenHandler {
	
	protected FXMLLoader loader;
	
	
	protected FXMLScreenHandler() {}
	
	public FXMLScreenHandler(String screenPath) throws IOException {
	    URL location = getClass().getResource(screenPath);
	    if (location == null) {
	        throw new IOException("FXML file not found: " + screenPath);
	    }

	    this.loader = new FXMLLoader(location);
	    // Set this class as the controller
	    this.loader.setController(this);
//	    this.content = (SplitPane) this.loader.load();
	}

	public FXMLLoader getLoader() {
		return this.loader;
	}

	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}
}