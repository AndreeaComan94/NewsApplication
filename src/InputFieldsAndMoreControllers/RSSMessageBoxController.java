package InputFieldsAndMoreControllers;
 
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class RSSMessageBoxController implements Initializable{
	
	
	
  @FXML
  private void CloseCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }
  
  
  
  @FXML
  private void BtnConfirmationPressed(ActionEvent event)
  {  
   CloseCurrentStage(event);
  }
  
  
  
  @Override
  public void initialize(URL url,ResourceBundle rb)
  {

  }
}
