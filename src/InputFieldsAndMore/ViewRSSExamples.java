package InputFieldsAndMore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 

public class ViewRSSExamples extends Application{

	public void start(Stage stage)   
	{
     try{
			 
		 AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("ViewRSSExamples.fxml"));
		 
		 Scene scene=new Scene(root,244,372);
 
		 stage.setScene(scene);
		 stage.setTitle("Vizualizare RSS-uri!");
		 stage.setResizable(false);
		 stage.show();
				  
		 }catch(Exception e){e.printStackTrace();}
		}
	
	
	public static void main(String[] args) 
	{
	 launch(args);
	}
}
