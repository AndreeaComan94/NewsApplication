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
import MenuItems.AddCommentsToFriendsWebsites;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.News;
import model.NewsQuery;

 
@SuppressWarnings("rawtypes")
public class AddCommentController{
  
	 
	  @FXML
	  private ListView ListFriendsSites;
	  
	  private List<String> value_url=new ArrayList<>();
	  private List<String> value_friends=new ArrayList<>(); 
	  private ArrayList<String> value_friends2=new ArrayList<>();
	  private ArrayList<Integer> value_friends3=new ArrayList<>();
      
	  private static int taken_id_from_selected_item_list;
	  private static String taken_url_from_selected_item_list;
	  private static String get_posted_message;
	  
	  NewsQuery nq=new NewsQuery();
	  News n=new News();
	  
	  
	  public static int getIdFromSelectedItemList()
	  {
	   return taken_id_from_selected_item_list;
	  }
	  
	  
	  public static String getUrlFromSelectedItemList()
	  {
	   return taken_url_from_selected_item_list;
	  }
	   
	  
	  public static String getPostedMessage()
	  {
	   return get_posted_message;
	  }
	  
	  
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
	   AddCommentsToFriendsWebsites ac=new AddCommentsToFriendsWebsites();
	   ac.start(stage);  
      }
     
	 
 	
	  @FXML
	  private void BtnPostCommentPressed(ActionEvent event)
	  {
 
	  }
	  
	  
	  
	  @SuppressWarnings("unchecked")
	  @FXML
	  public void initialize()
	  {
	   EntityManagerFactory emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	   EntityManager em=emf.createEntityManager();
							
	   try{
							     
		   EntityTransaction entr=em.getTransaction();
		   entr.begin();
							 
		   Query query=em.createQuery("SELECT sursa FROM News sursa");
		   List list=query.getResultList();
		   Iterator iterator=list.iterator();
		   	 
		   while(iterator.hasNext())
		   {
			News n=(News)iterator.next();
 
		    value_url.add(n.getSursa());
 
		    ListFriendsSites.setItems(FXCollections.observableList(value_url));
		    ListFriendsSites.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
		    {
		     taken_url_from_selected_item_list=newValue.toString(); 
		     taken_id_from_selected_item_list=(Integer)emf.getPersistenceUnitUtil().getIdentifier(n);
		    }); 			  
		   } 
	 
		   entr.commit();
					    
		  }finally{em.close();}
		 
	     }  
	}
	 