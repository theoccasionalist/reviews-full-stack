package org.wecancodeit.reviewsfullstack;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collection;

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
		Review review = new Review("Sea Shells", "image", null, "image", "content");
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityManager.flush();
		entityManager.clear();
		review = reviewRepo.findOne(reviewId);
		assertThat(review.getHeader(), is("Sea Shells"));
	}

	@Test
	public void shouldSaveReviewInCategory() {
		Category animals = new Category("Animals");
		Review cat = new Review("Phil", "image", animals, "image", "Loves Me");
		animals = categoryRepo.save(animals);
		cat = reviewRepo.save(cat);
		long checkId = cat.getId();
		entityManager.flush();
		entityManager.clear();
		animals = categoryRepo.findOne(checkId);
		assertThat(animals.getReviews(), containsInAnyOrder(cat));
	}

	@Test
	public void shouldFindCategoryByReview() {
		Category cats = new Category("Phil");
		Review phil = new Review("Phil", "image", cats, "image", "content");
		phil = reviewRepo.save(phil);
		cats = categoryRepo.save(cats);
		long philId = phil.getId();

		entityManager.flush();
		entityManager.clear();

		phil = reviewRepo.findOne(philId);
		Collection<Category> categories = categoryRepo.findByReviewsContains(phil);
		assertThat(categories, containsInAnyOrder(cats));
	}

	@Test
	public void shouldFindCategorybyReviewId() {
		Category cats = new Category("Phil");
		Review phil = new Review("Phil", "image", cats, "image", "content");
		phil = reviewRepo.save(phil);
		cats = categoryRepo.save(cats);
		long philId = phil.getId();

		entityManager.flush();
		entityManager.clear();

		Collection<Category> categories = categoryRepo.findByReviewsId(philId);
		assertThat(categories, containsInAnyOrder(cats));
	}

	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		Category cats = new Category("Phil");
		Review phil = new Review("Phil", "image", cats, "image", "content");
		phil = reviewRepo.save(phil);
		cats = categoryRepo.save(cats);
		long catsId = cats.getId();

		entityManager.flush();
		entityManager.clear();

		phil = reviewRepo.findOne(catsId);
		Category check = phil.getCategory();
		assertEquals(check, cats);
	}

	@Test
	public void shouldEstablishCategoryToReviewRelationship() {
		Category cats = new Category("Phil");
		Review phil = new Review("Phil", "image", cats, "image", "content");
		phil = reviewRepo.save(phil);
		cats = categoryRepo.save(cats);
		long philId = phil.getId();

		entityManager.flush();
		entityManager.clear();

		cats = categoryRepo.findOne(philId);
		assertThat(cats.getReviews(), containsInAnyOrder(phil));
	}

}
