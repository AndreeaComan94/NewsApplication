package MenuItems;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import InputFieldsAndMoreControllers.AddSiteTextController;

public class URL extends AddSiteTextController{
 
   private String title;
   private String description;
   private String author;
   private DateTime data;
   
   
   public URL(String title,String description,String author,DateTime data) 
   {
	super();
	this.title=title;
	this.description=description;
	this.author=author;
	this.data=data;
	}
   
   
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
  
   
   public String getAuthor() 
   {
	return author;
   }
   
   
   public void setAuthor(String author) 
   {
	this.author=author;
   }

   
   public DateTime getData() 
   {
	return data;
   }
   
   
   public void setData(DateTime data) 
   {
	this.data=data;
   }
   
}
