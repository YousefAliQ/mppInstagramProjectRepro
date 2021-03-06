package com.mpp.instagram.comments.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToOne;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.mpp.instagram.likes.entity.likesEntity;
import com.mpp.instagram.storage.PostEntity;
import com.mpp.instagram.user.entity.UserEntity;

@Table("INSTAGRAM_COMMENTS")
public class CommentsEntity {
   
	@PrimaryKey
	private long Id;
    
	@Column("user") @ManyToOne
    private UserEntity user;  
    
    @Column("post") @ManyToOne
    private PostEntity post;     
    
    @Column("description")
    private String description;
    
    @Column("likes") @ManyToOne
    private List<likesEntity> likes;

    @Column("date_comment")
    private LocalDateTime dateComment;

	public CommentsEntity(UserEntity user, PostEntity post, String description) {
		super();
		this.user = user;
		this.post = post;
		this.description = description;
		this.likes = new ArrayList<>();
	}
	
	public CommentsEntity(UserEntity user, PostEntity post, String description, LocalDateTime dateComment) {
		super();
		this.user = user;
		this.post = post;
		this.description = description;
		this.dateComment = dateComment;
	}

	public CommentsEntity(UserEntity user, PostEntity post, String description, List<likesEntity> likes) {
		super();
		this.user = user;
		this.post = post;
		this.description = description;
		this.likes = likes;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<likesEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<likesEntity> likes) {
		this.likes = likes;
	}

	public void addLike(likesEntity like) {
		this.likes.add(like);
	}
	
	public long getId() {
		return Id;
	}
	
	public LocalDateTime getDateComment() {
		return dateComment;
	}
    
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof CommentsEntity)) return false;
		
		return ((CommentsEntity)obj).description.equals(this.description)
				&& ((CommentsEntity)obj).dateComment.equals(this.dateComment)
				&& ((CommentsEntity)obj).user.getUsername().equals(this.getUser().getUsername())
				&& ((CommentsEntity)obj).post.getDescription().equals(this.post.getDescription());
	}
	
}
