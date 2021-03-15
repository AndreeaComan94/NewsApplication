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

import MenuItems.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Friend;
import model.FriendQuery;

 
public class ViewAllFriendsController{
 
	
  @FXML
  private ListView <String> listFriends;
  
  
  private List<String> value_friend=new ArrayList<>();
  private List<Integer> value_friend_id=new ArrayList<>();
  private static int taken_id_from_selected_item_list;
  FriendQuery fq=new FriendQuery();
  Friend fd=new Friend();
  
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
   Stage stage=new Stage();
   Menu m=new Menu();
   m.start(stage);
  }
  
  
  
  @SuppressWarnings("static-access")
  @FXML
  private void BtnDeletePressed(ActionEvent event)
  { 
   model.Friend f=fq.em.find(model.Friend.class,taken_id_from_selected_item_list);
	  
   fq.em.remove(f);
   fq.em.getTransaction().commit(); 
 
   CloseCurrentStage(event);
   Stage stage=new Stage();
   Menu m=new Menu();
   m.start(stage);
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
		  
	    Query query=em.createQuery( "Select nume From Friend nume");
	 
		List list=query.getResultList();
		Iterator iterator=list.iterator();
		   	 
		while(iterator.hasNext())
		{
	     Friend f=(Friend)iterator.next();
	     value_friend.add(f.getNume());
	     value_friend_id.add(f.getId());
	     
	     listFriends.setItems(FXCollections.observableList(value_friend));
	     
	     listFriends.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
	     {
	      taken_id_from_selected_item_list=(Integer)emf.getPersistenceUnitUtil().getIdentifier(f);
	     });
	    } 

	     entr.commit();
					    
	   }finally{em.close();}
  }
}
