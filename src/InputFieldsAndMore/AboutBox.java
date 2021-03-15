package InputFieldsAndMore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 
public class AboutBox extends Application{
	
	
	@Override
	public void start(Stage stage)   
	{
     try{
			 
		 AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("AboutBox.fxml"));
		 
		 Scene scene=new Scene(root,408,269);
			 
		 stage.setScene(scene);
		 stage.setTitle("Despre");
		 stage.setResizable(false);
		 stage.show();
				  
		 }catch(Exception e){e.printStackTrace();}
		}
	
	
	public static void main(String[] args) 
	{
	 launch(args);
	}
}
