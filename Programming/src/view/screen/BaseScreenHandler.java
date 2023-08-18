package view.screen;

import java.io.IOException;
import java.util.Hashtable;

import controller.BaseScreenController;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import view.screen.BaseScreenHandler;
import view.screen.home.HomeScreenHandler;

public class BaseScreenHandler extends FXMLScreenHandler {
	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected static HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseScreenController bController;
	protected SplitPane content;

	protected BaseScreenHandler(String screenPath) throws IOException {
		super(screenPath);
		this.stage = new Stage();
		this.content = (SplitPane) (getLoader().load());
	}
	public SplitPane getContent() {
		return this.content;
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;		
		this.content = (SplitPane) (this.getLoader().load());
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBaseController(BaseScreenController bController){
		this.bController = bController;
	}

	public BaseScreenController getBaseController(){
		return this.bController;
	}

	public void forward(Hashtable<String, String> messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
//		if(homeScreenHandler != null) return;
		homeScreenHandler = HomeScreenHandler;
	}
	
	public Stage getStage() {
		return this.stage;
	}

}