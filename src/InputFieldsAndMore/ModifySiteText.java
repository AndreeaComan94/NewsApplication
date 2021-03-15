package InputFieldsAndMore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ModifySiteText extends Application{
	
	
	@Override
	public void start(Stage stage)   
	{
     try{
			 
		 AnchorPane root=(AnchorPane)FXMLLoader.load(getClass().getResource("ModifySiteText.fxml"));
		 
		 Scene scene=new Scene(root,300,295);
	 
		 stage.setScene(scene);
		 stage.setTitle("Modificati un URL");
		 stage.setResizable(false);
		 stage.show();
				  
		 }catch(Exception e){e.printStackTrace();}
		}
	
	
	public static void main(String[] args) 
	{
	 launch(args);
	}
}
