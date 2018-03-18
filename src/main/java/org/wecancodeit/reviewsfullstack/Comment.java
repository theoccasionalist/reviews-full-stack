package org.wecancodeit.reviewsfullstack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Review review;

	@Lob
	private String content;

	@SuppressWarnings("unused")
	private Comment() {
	}

	public Comment(String content, Review review) {
		this.content = content;
		this.review = review;
	}

	public long getId() {
		return id;
	}

	public Review getReview() {
		return review;
	}

	public String getContent() {
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
		return id == ((Comment) obj).id;
	}

}
