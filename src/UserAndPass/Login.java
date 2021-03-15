package UserAndPass;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
 
public class Login extends Application{
 
	/*
	 * Authors: Chihaia Catalin & Cioaca Andreea
	 *  
	 */
 
	  
	@Override
	public void start(Stage primaryStage) 
	{
	 try{
			 
		  AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
	 
		  Scene scene=new Scene(root,1038,515);
		  
		  primaryStage.setScene(scene);
		  primaryStage.setTitle("Bun venit! Doriti sa va autentificati?");
		  primaryStage.setResizable(false);
		  primaryStage.show();
			  
		 }catch(Exception e){e.printStackTrace();}
	 
 	 }
 
	
	public static void main(String[] args) 
	{
	 launch(args); 
	}
}
