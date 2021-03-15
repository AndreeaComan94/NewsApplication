package InputFieldsAndMoreControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.User;
 

public class ViewAllUsersController{
 
	
  @FXML
  private ListView <String> listUsers;
  
  
  private List<String> value_user=new ArrayList<>();
 
  
  
  @FXML
  private void CloseCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }

 
  
  @FXML
  private void BtnReturnPressed(ActionEvent event)
  { 
   CloseCurrentStage(event);
  }
  
  
  
  @FXML
  private void BtnDeletePressed(ActionEvent event)
  {
	 
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
							 
		Query query=em.createQuery("SELECT nume FROM User nume");
		List list=query.getResultList();
		Iterator iterator=list.iterator();
		   	 
		while(iterator.hasNext())
		{
	     User u=(User)iterator.next();
	     value_user.add("ID  "+u.getId()+"  "+u.getNume());
	 
	     listUsers.setItems(FXCollections.observableList(value_user));
		} 

	     entr.commit();
					    
	   }finally{em.close();}
  }
}
