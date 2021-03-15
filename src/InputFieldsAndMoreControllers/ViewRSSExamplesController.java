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
import MenuItems.SearchURL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.RssExamples;
 
public class ViewRSSExamplesController{
 
	
  @FXML
  private ListView <String> listRSS;
  
  
  private List<String> value_user=new ArrayList<>();
  public static String rss_example;
  public static boolean rss_example_is_pressed=false;
  
  
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
   Stage search_stage=new Stage();
   SearchURL su=new SearchURL();
   su.start(search_stage);
  
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
							 
		Query query=em.createQuery("SELECT url FROM RssExamples url");
		List list=query.getResultList();
		Iterator iterator=list.iterator();
		   	 
		while(iterator.hasNext())
		{
	     RssExamples re=(RssExamples)iterator.next();
	     value_user.add(re.getUrl());
	 
	     listRSS.setItems(FXCollections.observableList(value_user));
	     
	     listRSS.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
		 {
	 	  rss_example=newValue.toString(); 
	 	  rss_example_is_pressed=true;
		 }); 			  
		} 

	     entr.commit();
					    
	   }finally{em.close();}
  }
}
