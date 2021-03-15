package RSS;

import java.sql.Date;

public class FeedMessage{

     private String title;
	 private String description;
	 Date data;

	  public String getTitle() 
	  {
	   return title;
	  }

	  public void setTitle(String title) 
	  {
	   this.title=title;
	  }

	  public String getDescription() 
	  {
	   return description;
	  }

	  public void setDescription(String description) 
	  {
	   this.description=description;
	  }
  
	  public Date getData()
	  {
	   return data;
	  }

	  public void setData(Date data) 
	  {
	   this.data=data;
	  }
}
