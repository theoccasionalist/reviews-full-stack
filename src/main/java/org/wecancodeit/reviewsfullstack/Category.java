package org.wecancodeit.reviewsfullstack;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;
	private String nameImg;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	@SuppressWarnings("unused")
	private Category() {
	}

	public Category(String nameImg) {
		this.nameImg = nameImg;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public long getId() {
		return id;
	}

	public String getNameImg() {
		return nameImg;
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
		return id == ((Category) obj).id;
	}
}
