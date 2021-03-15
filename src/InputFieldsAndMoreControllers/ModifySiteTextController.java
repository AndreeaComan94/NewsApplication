package InputFieldsAndMoreControllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.net.URL;
import java.util.ResourceBundle;
import model.ResourceQuery;
import model.User;
import MenuItems.ViewMyUrlRepo;
import MenuItemsControllers.ViewMyUrlRepoController;
import UserAndPassControllers.LoginController;

public class ModifySiteTextController implements Initializable{
	
 
  @FXML
  private TextField TxtURL;

  
  @FXML
  private TextArea TxtDescription;
  
  
  @PersistenceContext 
  private EntityManager em;
   
  
  ResourceQuery rq=new ResourceQuery();
  User u=new User();
 
  
  
  @FXML
  private void CloseCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }

  
  
  @FXML
  private void Refresh()
  {
   Stage stagee=new Stage();
   ViewMyUrlRepo vmur=new ViewMyUrlRepo ();
   vmur.start(stagee);
  }
  
  
  
  @FXML
  private void BtnConfirmationPressed(ActionEvent event) 
  {
   u.setId(LoginController.getCurrentId());
   u.setNume(LoginController.getCurrentUsername());

   model.Resource r=new model.Resource(ViewMyUrlRepoController.getSelectedItemId(),TxtURL.getText(),TxtDescription.getText(),u);
 
   ResourceQuery.em.merge(r);
   ResourceQuery.em.getTransaction().commit();
 
   Refresh();
   CloseCurrentStage(event);
  }
 
  
  
  @Override
  public void initialize(URL url,ResourceBundle rb)
  {
   TxtURL.setText(ViewMyUrlRepoController.getUrlCopy());
   TxtDescription.setText(ViewMyUrlRepoController.getDescriptionCopy());
  }
}