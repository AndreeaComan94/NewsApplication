package UserAndPassControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import InputFieldsAndMore.ViewAllUsers;
import UserAndPass.CreateNewAccount;
import UserAndPass.Login;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import model.UserQuery;
 

public class CreateNewAccountController implements Initializable{
	
	
  @PersistenceContext private EntityManager em;
	
  
  @FXML
  private TextField TxtID;
  
  
  @FXML
  private TextField TxtUsername;
  
   
  @FXML
  private TextField TxtNewPassword;
  
  
  UserQuery uq=new UserQuery();
  
  
  @FXML
  private void Refresh()
  {
   Stage stagee=new Stage();
   CreateNewAccount cna=new CreateNewAccount();
   cna.start(stagee);
   }
  
  
  
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
   model.User u=new model.User(TxtUsername.getText(),TxtNewPassword.getText());
	  
   UserQuery.em.persist(u);
   UserQuery.em.getTransaction().commit(); 
   Refresh();
   CloseCurrentStage(event);
  }
  
 
  
  @FXML
  private void BtnModificationPressed(ActionEvent event)
  {
   model.User u=new model.User(Integer.parseInt(TxtID.getText()),TxtUsername.getText(),TxtNewPassword.getText());
	   
   UserQuery.em.merge(u);
   UserQuery.em.getTransaction().commit();
   Refresh();
   CloseCurrentStage(event);
  }
  
  
  
  @FXML
  private void BtnRemovePressed(ActionEvent event)
  { 
   model.User u=UserQuery.em.find(model.User.class,Integer.parseInt(TxtID.getText()));
 
   UserQuery.em.remove(u);
   UserQuery.em.getTransaction().commit(); 
   Refresh();
   CloseCurrentStage(event);
  }
  
  
  
  @FXML
  private void BtnViewUsersPressed(ActionEvent event)
  {
   Stage stagee=new Stage();
   ViewAllUsers vauc=new  ViewAllUsers();
   vauc.start(stagee);
  }
  
  
  
  @FXML
  private void BtnSignOutPressed(ActionEvent event)
  { 
   Stage stage=new Stage();
   Login m=new Login();
   m.start(stage);
			   
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }
  
  
  
  public List<User> getAllUsers() 
  {
   return this.getEntityManager().createQuery("select u from model.User u", User.class).getResultList();
  }

  
  
  public User getUserById(int id) 
  {
   User user=this.getEntityManager().find(User.class, new Integer(id));
   return user;
  }

  
  
  private EntityManager getEntityManager()
  {
   EntityManagerFactory emf=Persistence.createEntityManagerFactory("User");
   EntityManager em=emf.createEntityManager();
   return em;
  }

  
  
  @Override
  public void initialize(URL location,ResourceBundle resources) 
  {
  }
 
}