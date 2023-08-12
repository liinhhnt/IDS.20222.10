package view.screen.dock;

import java.io.IOException;

import entity.dock.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import utils.Configs;
import view.screen.FXMLScreenHandler;
import view.screen.home.HomeScreenHandler;
//import view.screen.menu.MenuHandler;

public class DockHandler extends FXMLScreenHandler {
  @FXML
  private Label dockNameLabel;
  
  @FXML
  private Label addressLabel;
  
  @FXML
  private Label emptyPointsLabel;
  
  @FXML
  private Label availableBikesLabel;
  
  @FXML
  private Label areaLabel;
  
  @FXML
  private Button viewDockBtn;
  
  @FXML
  private Button returnBtn;
  
  private HomeScreenHandler homeScreen;
  private Dock dock;
  
  private Pane content;
  
  public DockHandler ()
  {
	  
  }
  
  public DockHandler(String screenPath, HomeScreenHandler homeScreen) throws IOException {
      super(screenPath);
      this.content = (Pane) this.getLoader().load();
      this.homeScreen = homeScreen;
  }

  public Pane getContent() {
    return this.content;
  }
  
  public void setDock(Dock dock) {
    this.dock = dock;
    displayDock();
  }
  
  public void displayDock() {
	// setup labels
    this.dockNameLabel.setText(dock.getName());
    this.addressLabel.setText("Address: " + dock.getAddress());
    this.areaLabel.setText("Area: " + dock.getArea() + " m2");
    this.emptyPointsLabel.setText("Empty Points: " + (dock.getNoOfEmptyPoints()));
    this.availableBikesLabel.setText("Available bikes: " + dock.getNoOfBikes());

    this.viewDockBtn.setOnMouseClicked(e -> {
      try {
        DockViewHandler dockMenu = new DockViewHandler(this.homeScreen.getStage(), Configs.DOCK_VIEW_PATH, this.dock);
        dockMenu.setPreviousScreen(this.homeScreen);
        dockMenu.show();
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    });
  }
}