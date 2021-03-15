package MenuItemsControllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Userevidence;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import MenuItems.Menu;
import UserAndPass.Login;


public class ViewCronologyController{
 
	
	  @FXML
	  private ListView <String> UserCronology;
	  private List<String> value=new ArrayList<>();
 
	  @FXML
	  private void CloseCurrentStage(ActionEvent event)
	  {
	   Node source=(Node)event.getSource();
	   Stage current_stage=(Stage)source.getScene().getWindow();
	   current_stage.close();
	  }
	  
	  
	  
	  @FXML
	  private void BtnMenuPressed(ActionEvent event)
	  { 
	   Stage stage=new Stage();
	   Menu m=new Menu();
	   m.start(stage);  
	   
	   CloseCurrentStage(event);
	  }
	  
	  
	  
	  @FXML
	  private void BtnSignOutPressed(ActionEvent event)
	  { 
	   Stage stage=new Stage();
	   Login l=new Login();
	   l.start(stage);  
	   CloseCurrentStage(event);
	  }
	  
	  
	  
	  @SuppressWarnings("rawtypes")
	  @FXML
	  public void initialize()
	  { 
	   EntityManagerFactory emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	   EntityManager em=emf.createEntityManager();
								
	   try{
								     
		    EntityTransaction entr=em.getTransaction();
			entr.begin();
								 
			Query query=em.createQuery("SELECT nume FROM Userevidence nume");
	        Iterator iterator=query.getResultList().iterator();
			   	 
			while(iterator.hasNext())
			{
			 Userevidence r=(Userevidence)iterator.next();
			 value.add("Utilizatorul: "+r.getNume()+" s-a conectat la data de: "+r.getUserTime());
	         UserCronology.setItems(FXCollections.observableList(value));
			}
			  
			entr.commit();
						    
		   }finally{em.close();}
		   	 
	  }
	  
}
