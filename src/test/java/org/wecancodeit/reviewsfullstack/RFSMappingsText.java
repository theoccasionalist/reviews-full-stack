package org.wecancodeit.reviewsfullstack;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class RFSMappingsText {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("Sea Shells", null, "image", "content");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityManager.flush();
		entityManager.clear();
		review = reviewRepo.findOne(reviewId);
		assertThat(review.getWhat(), is("Sea Shells"));
	}

	@Test
	public void shouldPutReviewInCategory() {
		Category animals = new Category("Animals");
		Review cat = new Review("Phil", animals, "image", "Loves Me");
		animals = categoryRepo.save(animals);
		cat = reviewRepo.save(cat);
		long checkId = cat.getId();
		entityManager.flush();
		entityManager.clear();
		animals = categoryRepo.findOne(checkId);
		assertThat(animals.getReviews(), containsInAnyOrder(cat));
	}
}
