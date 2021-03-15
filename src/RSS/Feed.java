package RSS;

import java.util.ArrayList;
import java.util.List;
import MenuItems.SearchURL;

public class Feed {

	  private String title;
	  private String description;
	  private String pubDate;

	  final List<FeedMessage> entries=new ArrayList<FeedMessage>();

	  public Feed(String title,String link,String description,String pubDate) 
	  {
	   this.title=title;
	   this.description=description;
	   this.pubDate=pubDate;
	  }

	  public List <FeedMessage> getMessages() 
	  {
	   return entries;
	  }

	  public String getTitle() 
	  {
	   return title;
	  }
  
	  public void setTitle(String title)
	  {
	   this.title=title;
	  }
	  
	  public String getLink() 
	  {
	   return SearchURL.getAdress();
	  }
	  
	  public void setLink(String link)
	  {
	  }
	  
	  public String getDescription() 
	  {
	   return description;
	  }

	  public void setDescription(String description)
	  {
	   this.description=description;
	  }
	  
	  public String getPubDate() 
	  {
	   return pubDate;
	  }
	  
	  public void setPubDate(String pubDate)
	  {
	   this.pubDate=pubDate;
	  }
	  
	  @Override
	  public String toString() 
	  {
	   return "description="+description+"  ,pubDate="+pubDate+"  ,title="+title;
	  }
}
