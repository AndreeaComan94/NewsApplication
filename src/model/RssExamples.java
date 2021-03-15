package model;

import java.io.Serializable;
import javax.persistence.*;
 

/**
 * The persistent class for the RssExamples database table.
 * 
 */


@Entity
@NamedQuery(name="RssExamples.findAll", query="SELECT r FROM RssExamples r")
 
public class RssExamples implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
    private int id;

	private String url;
 

	public RssExamples() {
	}

	public RssExamples(String url)
	{
	 this.url=url;
	}
	 
	public int getId() {
		return this.id;
	}

 
	public void setId(int id) {
		this.id=id;
 
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url=url;
	}
}