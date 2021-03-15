package MenuItemsControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Resource;
import model.ResourceQuery;
import model.UserQuery;
import InputFieldsAndMore.AboutBox;
import InputFieldsAndMore.AddSiteText;
import InputFieldsAndMore.ModifySiteText;
import MenuItems.Menu;
import MenuItems.ViewMyUrlRepo;
import UserAndPass.Login;

 
@SuppressWarnings("rawtypes")
public class ViewMyUrlRepoController{
	
	  
	  @FXML
	  private TextArea TextAreaDescr;
	 
	  
	  @FXML
	  private ListView <String> urlList;
	  
  
	  @PersistenceContext 
	  private EntityManager em;
	  	  
	   
	  private List<String> value_url=new ArrayList<>();
	  private static String text_preluat;
	  private static int taken_id_from_selected_item_list;
	  private static String url_copy;
	  private static String description_copy;
	  
	  
	  UserQuery uq=new UserQuery();
	  ResourceQuery rq=new ResourceQuery(); 
	  Resource r=new Resource();
	  
	  
	  
	  public static int getSelectedItemId()
	  {
	   return taken_id_from_selected_item_list;
	  }
	  
	  
	  
	  public static String getUrlCopy()
	  {
	   return url_copy;
	  }
	  
	  
	  
	  public static String getDescriptionCopy()
	  {
	   return description_copy;
	  }
	  
	  
	  
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
	  private void BtnAddManuallyASitePressed(ActionEvent event)
	  { 
	   Stage stage=new Stage();
	   AddSiteText ast=new AddSiteText();
	   ast.start(stage);   
	   
	   CloseCurrentStage(event);
	  }
	 
	  
 
	  @FXML
	  private void BtnUpdateExistingSitePressed(ActionEvent event)
	  {   
	   Stage stage=new Stage();
	   ModifySiteText mst=new ModifySiteText();
	   mst.start(stage); 
	   
	   CloseCurrentStage(event);
	  } 
	  
	  
	  
	  @FXML
	  private void BtnRemoveSelectedSitePressed(ActionEvent event)
	  {   
	   Resource u=ResourceQuery.em.find(Resource.class,taken_id_from_selected_item_list);
	   ResourceQuery.em.remove(u);
	   ResourceQuery.em.getTransaction().commit(); 
	   
	   CloseCurrentStage(event);
	   Refresh();
	  }
	  
	  
 
	  @SuppressWarnings("unused")
	  private void initListView(ListView<String> listView) 
	  {
	   listView.getItems();
      }
	  
	  
	  
	  @FXML
	  private void BtnReturnMenuPressed(ActionEvent event)
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
	   Login m=new Login();
	   m.start(stage);
	   
	   CloseCurrentStage(event);
	  }
	  
	  
	  
	  @FXML
	  private void BtnAboutProjectPressed(ActionEvent event)
	  { 
	   Stage stage=new Stage();
	   AboutBox ab=new AboutBox();
	   ab.start(stage);  
	  }
	   
 
	   
	  @FXML
	  public void initialize()
	  {  
	   EntityManagerFactory emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	   EntityManager em=emf.createEntityManager();
							
	   try{
							     
		   EntityTransaction entr=em.getTransaction();
		   entr.begin();
							 
		   Query query=em.createQuery("SELECT url FROM Resource url");
		   List list=query.getResultList();
		   Iterator iterator=list.iterator();
		   	 
		   while(iterator.hasNext())
		   {
			Resource r=(Resource)iterator.next();
 
		    value_url.add(r.getUrl());
 
			urlList.setItems(FXCollections.observableList(value_url));
		    urlList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
		    {
			 text_preluat=newValue.toString(); 
		  
			
			 if(text_preluat==r.getUrl()) 
			 {  
			  taken_id_from_selected_item_list=(Integer)emf.getPersistenceUnitUtil().getIdentifier(r);
			  TextAreaDescr.setText(r.getDescription());
			  
			  url_copy=r.getUrl();
			  description_copy=r.getDescription();
			 }
			}); 			  
		   } 
	 
		   entr.commit();
					    
		  }finally{em.close();}
	   
     }  
}
 