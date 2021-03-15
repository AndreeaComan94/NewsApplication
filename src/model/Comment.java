package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the comment database table.
 * 
 */

@Entity
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String text;
	private String userCurrent;
	private String urlCurent;
	//bi-directional many-to-one association to New
	@ManyToOne
	@JoinColumn(name="news_id")
	private News news;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;
 
	//bi-directional many-to-one association to Friend
	@ManyToOne
	@JoinColumn(name="friends_id")
	private Friend friend;
 
	public Comment(News news,Category category,Friend friend,String text,Date data, String userCurrent, String urlCurent) {
		this.news=news;
		this.category=category;
		this.friend=friend;
		this.text=text;
		this.data=data;
		this.userCurrent = userCurrent;
		this.urlCurent = urlCurent;
	}

	public Comment() {
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public News getNew() {
		return this.news;
	}

	public void setNew(News news) {
		this.news = news;
	}

	public void setUrlCurent(String urlCurent){
		this.urlCurent = urlCurent;
	}
	
	public String  getUrlCurent(){
		return this.urlCurent;
	}
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
 
	public Friend getFriend() {
		return this.friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}
 
	public void setUserCurrent(String userCurrent){
		this.userCurrent = userCurrent;
	}
	
	public String getUserCurrent(){
		return this.userCurrent;
	}
}