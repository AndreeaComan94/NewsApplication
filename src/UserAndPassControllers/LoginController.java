package UserAndPassControllers;
 
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import model.UserQuery;
import model.User;
import model.UserevidenceQuery;
import InputFieldsAndMore.MessageBox;
import MenuItems.Menu;
import UserAndPass.CreateNewAccount;

public class LoginController implements Initializable{
	
  
  @FXML
  private Label lblMessage;
  
  
  @FXML
  private TextField txtUsername;
  
  
  @FXML
  private PasswordField txtPassword;
  
  
  private List<model.User>listUser=new ArrayList<>();
  private UserQuery query=new UserQuery();
  private UserevidenceQuery uq=new UserevidenceQuery();
  
  private static int id;
  private static String username;
  private String timenow;
  
  private int nr=0;
  
  
  public static int getCurrentId()
  {
   return id;
  }
  
 
  public static String getCurrentUsername()
  {
   return username;
  }
  
  
  public String getTimeNow()
  {
   DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd , HH:mm:ss");
   Date date=new Date();
   return dateFormat.format(date);
  }
  
  
  @FXML
  private void closeCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }
	
 
  @SuppressWarnings("static-access")
  @FXML
  private void btnLoginAction(ActionEvent event)
  {    
   for(User u: listUser)
   {
	if(txtUsername.getText().equals(u.getNume()) && txtPassword.getText().equals(u.getParola()))
    {
	 id=u.getId();
	 username=u.getNume();
     timenow=getTimeNow();
     
	 model.Userevidence ue=new model.Userevidence(username,timenow);
	  
	 uq.em.persist(ue);
	 uq.em.getTransaction().commit(); 
	   
	 Stage stage=new Stage();
	 Menu m=new Menu();
	 m.start(stage);
		
	 nr=1;
	 closeCurrentStage(event);
    }
   else 
	if(nr==0)
    { 
     Stage stagee=new Stage();
     MessageBox mb=new MessageBox();
	 mb.start(stagee);
 
	 nr=1;
	 closeCurrentStage(event);
   }
  }
  }
  
  
  
  @FXML
  private void btnCancelAction(ActionEvent event)
  { 
   System.exit(0);
  }
  
  
  
  @FXML
  private void btnNewAccount(ActionEvent event)
  {
   Stage stagee=new Stage();
   CreateNewAccount cna=new CreateNewAccount();
   cna.start(stagee);
		
   closeCurrentStage(event);
  }
  
  
  
  @Override
  public void initialize(URL url,ResourceBundle rb)
  {
   listUser=query.listUser();
  }
}
