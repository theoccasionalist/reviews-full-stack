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

	private String header;
	private String name;
	private String image;
	@Lob
	private String content;

	public Review(String header, String name, Category category, String image, String content) {
		this.header = header;
		this.name = name;
		this.category = category;
		this.image = image;
		this.content = content;
	}

	public long getId() {
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