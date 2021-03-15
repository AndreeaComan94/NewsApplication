package InputFieldsAndMoreControllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import InputFieldsAndMore.AddSiteText;
import MenuItems.Menu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Category;
import model.ResourceQuery;



public class AddACategoryController{
 
	
  @FXML
  private ListView <String> ListCategories;
  
 
  @FXML
  private TextField TxtAddCategory;
  
  
  @FXML
  private TextField TxtAddCategoryDescr;
  
  
  private List<String> value_category=new ArrayList<>();
  
  
  @FXML
  private void CloseCurrentStage(ActionEvent event)
  {
   Node source=(Node)event.getSource();
   Stage current_stage=(Stage)source.getScene().getWindow();
   current_stage.close();
  }

 
  @FXML
  private void BtnConfirmPressed(ActionEvent event)
  { 
   Category c=new Category(TxtAddCategory.getText(),TxtAddCategoryDescr.getText());
	      
   ResourceQuery.em.persist(c);
   ResourceQuery.em.getTransaction().commit();
	   
   CloseCurrentStage(event);
   Stage stage=new Stage();
   AddSiteText ast=new AddSiteText();
   ast.start(stage);
  }
  
  
  
  @FXML
  private void BtnReturnPressed(ActionEvent event)
  { 
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
							 
		Query query=em.createQuery("SELECT nume FROM Category nume");
		List list=query.getResultList();
		Iterator iterator=list.iterator();
		   	 
		while(iterator.hasNext())
		{
	     Category c=( Category)iterator.next();
	     value_category.add(c.getNume()+"  =   "+c.getDescriere());
	 
	     ListCategories.setItems(FXCollections.observableList(value_category));
		} 

	    entr.commit();
					    
	   }finally{em.close();}
  }
}
