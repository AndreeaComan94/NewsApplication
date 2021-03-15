package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */


@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private int id;

	private String descriere;

	private String nume;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="category")
	private List<Comment> comments;

	//bi-directional many-to-many association to Keyword
	@ManyToMany(mappedBy="categories")
	private List<Keyword> keywords;

	//bi-directional many-to-many association to New
	@ManyToMany(mappedBy="categories")
	private List<News> news;

	public Category() {
	}
	
	public Category(String nume,String descriere){
	    this.nume=nume;
	    this.descriere=descriere;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescriere() {
		return this.descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setCategory(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setCategory(null);

		return comment;
	}

	public List<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}