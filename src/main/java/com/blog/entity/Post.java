package com.blog.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@Column(length=5000)
	private String Content;
	private String postedBy;
	 @Lob
	 @Column(columnDefinition = "LONGBLOB")
	private byte[] img;
	private Date date;
	private int likeCount;
	private int viewCount;
	@ElementCollection
	private List<String> tags;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	

	public Post(long id, String name, String content, String postedBy, byte[] img, Date date, int likeCount,
			int viewCount, List<String> tags) {
		super();
		this.id = id;
		this.name = name;
		Content = content;
		this.postedBy = postedBy;
		this.img = img;
		this.date = date;
		this.likeCount = likeCount;
		this.viewCount = viewCount;
		this.tags = tags;
	}
	public Post() {
		super();
		
	}
	

}
