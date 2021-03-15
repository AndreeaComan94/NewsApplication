package MenuItems;
 
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCommentsToFriendsWebsites extends Application{
	
	
	@Override
	public void start(Stage stage)   
	{
     try{
			 
		 AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("AddCommentsToFriendsWebsites.fxml"));
		 
		 Scene scene=new Scene(root,1038,508);
	 
		 stage.setScene(scene);
		 stage.setTitle("Adaugati comentarii prietenilor!");
		 stage.setResizable(false);
		 stage.show();
				  
		 }catch(Exception e){e.printStackTrace();}
		}
	
	
	public static void main(String[] args) 
	{
	 launch(args);
	}
   } 
