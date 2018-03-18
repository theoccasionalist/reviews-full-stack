package org.wecancodeit.reviewsfullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findAllByCategory(Category category);

	// Collection<Review> findAllByTags(Tag tag);

	Collection<Review> findAllByComments(Comment comment);

	Collection<Review> findByTagsContains(Tag tag);

	Collection<Review> findByTagsId(Long id);

}
