package org.wecancodeit.reviewsfullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Category category;

	@SuppressWarnings("unused")
	private Review() {
	}

	private String what;

	@Lob
	private String content;

	private String image;

	public Review(String what, Category category, String image, String content) {
		this.what = what;
		this.category = category;
		this.image = image;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getWhat() {
		return what;
	}

	public Category getCategory() {
		return category;
	}

	public String content() {
		return content;
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