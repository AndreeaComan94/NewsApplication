package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the keyword database table.
 * 
 */


@Entity
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nume;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="keyword_category"
		, joinColumns={
			@JoinColumn(name="keyword_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_id")
			}
		)
	private List<Category> categories;

	public Keyword() {
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

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}