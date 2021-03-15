package MenuItemsControllers;
 
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Friend;
import model.FriendQuery;
import model.User;
import InputFieldsAndMore.ViewAllFriends;
import MenuItems.AddCommentsToFriendsWebsites;
import MenuItems.ViewCronology;
import MenuItems.ViewMyUrlRepo;
import MenuItems.SearchURL;
import UserAndPass.Login;
import UserAndPassControllers.LoginController;

public class MenuController{

	@FXML
	private ComboBox<String> UsersList;

	private List<String> value_url=new ArrayList<>();
	private String prieten_preluat;
	
	User u=new User();
	
	 
	@FXML
	private void CloseCurrentStage(ActionEvent event) 
	{
	 Node source=(Node) event.getSource();
     Stage current_stage=(Stage) source.getScene().getWindow();
	 current_stage.close();
	}

	
	@FXML
	private void BtnSearchURLPressed(ActionEvent event) 
	{
	 Stage search_stage=new Stage();
	 SearchURL su=new SearchURL();
	 su.start(search_stage);

	 CloseCurrentStage(event);
	}

	
	@FXML
	private void BtnAddCommentsToFriendsWebsitesPressed(ActionEvent event) 
	{
	 Stage add_comments_stage=new Stage();
	 AddCommentsToFriendsWebsites adcom=new AddCommentsToFriendsWebsites();
	 adcom.start(add_comments_stage);

	 CloseCurrentStage(event);
	}

	
	@FXML
	private void BtnViewMyURLRepoPressed(ActionEvent event) 
	{
	 Stage view_my_url_repo_stage=new Stage();
	 ViewMyUrlRepo wmur=new ViewMyUrlRepo();
	 wmur.start(view_my_url_repo_stage);

	 CloseCurrentStage(event);
	}

	
	@FXML
	private void BtnViewCronologyPressed(ActionEvent event) 
	{
	 Stage view_my_cronology_stage=new Stage();
	 ViewCronology vc=new ViewCronology();
	 vc.start(view_my_cronology_stage);

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
	private void BtnUsersList(ActionEvent event) 
	{
 
	}

 
	@SuppressWarnings("static-access")
	@FXML
	private void BtnAddNewFriendPressed(ActionEvent event) 
	{
	 u.setId(LoginController.getCurrentId());
	 u.setNume(LoginController.getCurrentUsername());
    
	 Friend f=new Friend(u,prieten_preluat);
 
     FriendQuery fq=new FriendQuery();
	 fq.em.persist(f);
	 fq.em.getTransaction().commit();  
	}

	
	@FXML
	private void BtnViewMyFriendsPressed(ActionEvent event) 
	{
     CloseCurrentStage(event);
	 Stage stage=new Stage();
	 ViewAllFriends vaf=new ViewAllFriends();
	 vaf.start(stage);
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
		   User u=(User) iterator.next();
		   
		   if(u.getNume()!=LoginController.getCurrentUsername())
		     value_url.add(u.getNume());
		 /*
		     Alert alert=new Alert(AlertType.INFORMATION);
			 alert.setTitle("Information Dialog");
			 alert.setHeaderText("Friend not was found");
			 alert.setContentText("Friend not was found!");
			 		alert.showAndWait(); 
		   */
           UsersList.setItems(FXCollections.observableList(value_url));
          }
		  
		  UsersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		  {
		   prieten_preluat=newValue.toString();
		  });
			
		  entr.commit();

	     }finally{em.close();} 
		 
	}

}
