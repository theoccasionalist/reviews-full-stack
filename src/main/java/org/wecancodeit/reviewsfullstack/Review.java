package org.wecancodeit.reviewsfullstack;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Collection<Tag> tags;

	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	@SuppressWarnings("unused")
	private Review() {
	}

	private String header;
	private String name;
	private String image;
	@Lob
	private String content;

	public Review(String header, String name, Category category, String image, String content, Tag... tags) {
		this.header = header;
		this.name = name;
		this.category = category;
		this.image = image;
		this.content = content;
		this.tags = new HashSet<>(asList(tags));
	}

	public Long getId() {
		return id;
	}

	public String getHeader() {
		return header;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	public String getContent() {
		return content;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		return id == ((Review) obj).id;
	}
}