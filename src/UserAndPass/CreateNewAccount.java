package UserAndPass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateNewAccount extends Application{
	
	
	@Override
	public void start(Stage stage)   
	{
     try{
			 
		 AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("CreateNewAccount.fxml"));
		 
		 Scene scene=new Scene(root,1038,515);
		 
		 stage.setScene(scene);
		 stage.setTitle("Adaugati un URL");
		 stage.setResizable(false);
		 stage.show();
				  
		 }catch(Exception e){e.printStackTrace();}
		}
	
	
	public static void main(String[] args) 
	{
	 launch(args);
	}
}
