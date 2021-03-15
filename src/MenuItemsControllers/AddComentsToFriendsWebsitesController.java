package MenuItemsControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Category;
import model.Comment;
import model.CommentQuery;
import model.Friend;
import model.News;
import InputFieldsAndMore.AddComment;
import InputFieldsAndMoreControllers.AddCommentController;
import MenuItems.AddCommentsToFriendsWebsites;
import MenuItems.Menu;
import MenuItems.SearchURL;
import UserAndPass.Login;
import UserAndPassControllers.LoginController;

public class AddComentsToFriendsWebsitesController{
 
	
	  @FXML
	  private TextArea TxtComment;
	  
	  @FXML
	  private TextField TxtDisplaySite;
	  
	  @FXML
	  private TextArea TxtCronology;
	  
	  private List<String> value=new ArrayList<>();
	  
	  @FXML
	  private void CloseCurrentStage(ActionEvent event)
	  {
	   Node source=(Node)event.getSource();
	   Stage current_stage=(Stage)source.getScene().getWindow();
	   current_stage.close();
	  }
	
	  AddCommentController acc=new AddCommentController();
	  
	  @FXML
	  private void BtnSignOutPressed(ActionEvent event)
	  { 
	   Stage stage=new Stage();
	   Login l=new Login();
	   l.start(stage);  
			   
	   CloseCurrentStage(event);
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
	  private void BtnSelectSitePressed(ActionEvent event)
	  { 
	   CloseCurrentStage(event);
	   Stage stage=new Stage();
	   AddComment ac=new AddComment();
	   ac.start(stage);  
	  }
	  
	  
	  News n=new News();
	  Category ca=new Category();
	  Friend f=new Friend();
	  CommentQuery cq=new CommentQuery();
	  Date current_time;
	  
	  
	  public Date getCurrentDate()  
	  {
	   //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	   Date date=Calendar.getInstance().getTime();
	            //date=simpleDateFormat.parse(s);

	   return date;
	  }
	  
	  
	  @SuppressWarnings("static-access")
	  @FXML
	  private void BtnPostCommentPressed(ActionEvent event)  
	  { 
	   n.setId(AddCommentController.getIdFromSelectedItemList());
	   
	   
	   current_time=getCurrentDate();
	   model.Comment c=new model.Comment(n,null,null,TxtComment.getText(),current_time, LoginController.getCurrentUsername(), AddCommentController.getUrlFromSelectedItemList());
		  
	   cq.em.persist(c);
	   cq.em.getTransaction().commit(); 
		 
	   CloseCurrentStage(event);
	   Stage stage=new Stage();
	   AddCommentsToFriendsWebsites m=new AddCommentsToFriendsWebsites();
	   m.start(stage);  
	  }
	    

	  
	  @SuppressWarnings({ "rawtypes", "static-access" })
	  @FXML
	  public void initialize()
	  {
	   TxtDisplaySite.setText(acc.getUrlFromSelectedItemList());
	   
	   
	   EntityManagerFactory emf=Persistence.createEntityManagerFactory("AplicatieStiri");
	   EntityManager em=emf.createEntityManager();
							
	   try{
							     
		   EntityTransaction entr=em.getTransaction();
		   entr.begin();
							 
		   Query query=em.createQuery("SELECT text FROM Comment text");
		   
		   List list=query.getResultList();
		   Iterator iterator=list.iterator();
 
		   while(iterator.hasNext())
		   {
			Comment c=(Comment)iterator.next();
			
		    value.add(c.getText());
		   
		    TxtCronology.appendText(c.getUserCurrent()+"\n"+c.getData()+"\n"+c.getText()+"\n"+c.getUrlCurent()+"\n\n\n");
		    }
		   entr.commit();
					    
		  }finally{em.close();}
	   
	  }
	  
}
