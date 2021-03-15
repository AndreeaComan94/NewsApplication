package InputFieldsAndMoreControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Category;
import model.Resource;
import model.ResourceQuery;
import model.User;
import InputFieldsAndMore.AddACategory;
import MenuItems.SearchURL;
import MenuItems.ViewMyUrlRepo;
import UserAndPassControllers.LoginController;
 
public class AddSiteTextController implements Initializable{
	
 
  @FXML
  public TextField TxtURL;

  
  @FXML
  private TextArea TxtDescription;
  
  
  @FXML
  private ComboBox <String>ListChooseCategory;
  
  
  @PersistenceContext 
  private EntityManager em;
  
  
  private static String url_copy;
  private static String description_copy;
  
  private List<String> value_category=new ArrayList<>();
  
  User u=new User();
  ResourceQuery rq=new ResourceQuery();
  
  public static String getURL()
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
  
  
   
  @SuppressWarnings("static-access")
  @FXML
  private void BtnConfirmationPressed(ActionEvent event) 
  {
   u.setId(LoginController.getCurrentId());
   u.setNume(LoginController.getCurrentUsername());
 
   
   Resource r=null;
   if(SearchURL.url_from_web_is_pressed==true)
   {
    r=new Resource(SearchURL.getAdress(),SearchURL.getDescription(),u);
    url_copy=SearchURL.getAdress();
    description_copy=SearchURL.getDescription();
   }
   else
   {
	r=new Resource(TxtURL.getText(),TxtDescription.getText(),u);
   }
  
   rq.em.persist(r);
   rq.em.getTransaction().commit();
   
   Refresh();
   CloseCurrentStage(event);
  }
 
 
  
  @FXML
  private void BtnChooseCategoryPressed(ActionEvent event) 
  {
  
  }
  
  
  
  @FXML
  private void BtnAddCategoryPressed(ActionEvent event) 
  { 
   CloseCurrentStage(event);
   Stage stagee=new Stage();
   AddACategory aac=new AddACategory();
   aac.start(stagee);
  }
  
  
  
  @SuppressWarnings("rawtypes")
  @Override
  public void initialize(URL url,ResourceBundle rb)
  {
   if(SearchURL.url_from_web_is_pressed==true)
   {
    TxtURL.setDisable(true);
	TxtURL.setText(SearchURL.getAdress());	
	TxtDescription.setText(SearchURL.getDescription());
   }
   
   EntityManagerFactory emf=Persistence.createEntityManagerFactory("AplicatieStiri");
   EntityManager em=emf.createEntityManager();
							
   try{
							     
	    EntityTransaction entr=em.getTransaction();
		entr.begin();
							 
		Query query=em.createQuery("SELECT nume FROM Category nume");
		List list=query.getResultList();
		Iterator iterator=list.iterator();
		   	 
		while(iterator.hasNext())
		{
	     Category c=( Category)iterator.next();
	     value_category.add(c.getNume());
	 
	     ListChooseCategory.setItems(FXCollections.observableList(value_category));
		} 

	    entr.commit();
					    
	   }finally{em.close();}  
  }
  
}