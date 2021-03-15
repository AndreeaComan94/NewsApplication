package InputFieldsAndMoreControllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import UserAndPass.Login;

public class MessageBoxController implements Initializable{
	
	
  @FXML
  private void CloseCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }
  
  
  
  @FXML
  private void BtnYesPressed(ActionEvent event)
  {  
   CloseCurrentStage(event);
   
   Stage stage=new Stage();
   Login m=new Login();
   m.start(stage);
  }
  
  
  
  @FXML
  private void BtnNoPressed(ActionEvent event)
  { 
   System.exit(0);
  }
  
  
  
  @Override
  public void initialize(URL url,ResourceBundle rb)
  {

  }
}
