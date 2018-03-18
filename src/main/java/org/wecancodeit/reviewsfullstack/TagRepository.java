package org.wecancodeit.reviewsfullstack;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	// Collection<Tag> findAllByReviews(Review review);

	Tag findByDescription(String description);

}
