package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */


@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
 
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
    private int id;

	private String nume;

	private String parola;
 
	//bi-directional many-to-one association to Friend
	@OneToMany(mappedBy="user")
	private List<Friend> friends;
 
	
	//bi-directional many-to-one association to New
	@OneToMany(mappedBy="user")
	private List<News> news;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="user")
	private List<Resource> resources;

	public User() {
	}

	public User(String nume,String parola)
	{
	 this.nume=nume;
	 this.parola=parola;
	}
	
	public User(int id,String nume,String parola)
	{
	 this.id=id;
	 this.nume=nume;
	 this.parola=parola;
    }
	
	public int getId() {
		return this.id;
	}

 
	public void setId(int id) {
		this.id = id;
	//	System.out.println(id);
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getParola() {
		return this.parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public List<Friend> getFriends() {
		return this.friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public Friend addFriend(Friend friend) {
		getFriends().add(friend);
		friend.setUser(this);

		return friend;
	}

	public Friend removeFriend(Friend friend) {
		getFriends().remove(friend);
		friend.setUser(null);

		return friend;
	}
 
	 
	
	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public News addNew(News news) {
		getNews().add(news);
		news.setUser(this);

		return news;
	}

	public News removeNew(News news) {
		getNews().remove(news);
		news.setUser(null);

		return news;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setUser(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setUser(null);

		return resource;
	}

}