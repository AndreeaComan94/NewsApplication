package UserAndPass;

@SuppressWarnings("unused")
public class Urlinfo{
    
		private String url;
	    private int id_user;
	    private String connected_user;
	 
	    public Urlinfo(String url,int id_user,String connected_user)
	    {
	     this.url=url;
	     this.id_user=id_user;
	     this.connected_user=connected_user;
	    }
	    
	    public Urlinfo()
	    {
	    }
	    
	    public int getID()
	    {
	     return id_user;
	    }
	    
	    public void setID(int id_user)
	    {
	     this.id_user=id_user;
	    }
	    
	    public void setUrl(String url)
	    {
	     this.url=url;
	     System.out.println(url);
	    }
}
