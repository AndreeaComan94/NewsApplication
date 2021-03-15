package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Userevidence database table.
 * 
 */


@Entity
@NamedQuery(name="Userevidence.findAll",query="SELECT u FROM Userevidence u")
 
public class Userevidence implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
    private int id;

	private String nume;
    
	private String usertime;

	public Userevidence(){
	}

	public Userevidence(String nume,String usertime){
	  this.nume=nume;
	  this.usertime=usertime;
	}
	 
	
	public int getId() {
		return this.id;
	}

 
	public void setId(int id) {
		this.id = id;
	}

	
	public String getNume() {
		return this.nume;
	}

	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	
	public String getUserTime() {
		return this.usertime;
	}

	
	public void setUserTime(String usertime) {
		this.usertime=usertime;
	}
}