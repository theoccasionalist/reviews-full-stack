package org.wecancodeit.reviewsfullstack;

import static org.hamcrest.Matchers.contains;
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

	@Resource
	private TagRepository tagRepo;

	@Resource
	private CommentRepository commentRepo;

	@Test
	public void ShouldAddTwoReviewsToOneCategory() {
		Category testCategory = new Category("Cat");
		testCategory = categoryRepo.save(testCategory);
		Tag testTag = new Tag("my cat");
		testTag = tagRepo.save(testTag);
		Review testReview = new Review("Phil", "image", testCategory, "image", "content", testTag);
		testReview = reviewRepo.save(testReview);
		Review testReview2 = new Review("Fusa", "image", testCategory, "image", "content", testTag);
		testReview2 = reviewRepo.save(testReview2);

		entityManager.flush();
		entityManager.clear();

		Collection<Category> testCategories = categoryRepo.findByReviewsContains(testReview);
		Collection<Category> testCategories2 = categoryRepo.findByReviewsContains(testReview2);

		assertThat(testCategories, contains(testCategory));
		assertThat(testCategories2, contains(testCategory));
	}

	@Test
	public void shouldAddTwoTagsToOneReview() {
		Category testCategory = new Category("Cat");
		testCategory = categoryRepo.save(testCategory);
		Tag testTag = new Tag("my cat");
		testTag = tagRepo.save(testTag);
		Tag testTag2 = new Tag("best cat");
		testTag2 = tagRepo.save(testTag2);
		Review testReview = new Review("Phil", "image", testCategory, "image", "content", testTag, testTag2);
		testReview = reviewRepo.save(testReview);

		entityManager.flush();
		entityManager.clear();

		Collection<Review> testReviews = reviewRepo.findAllByTags(testTag);
		Collection<Review> testReviews2 = reviewRepo.findAllByTags(testTag2);

		assertThat(testReviews, contains(testReview));
		assertThat(testReviews2, contains(testReview));
	}

	@Test
	public void shouldAddTwoCommentsToOneReview() {
		Category testCategory = new Category("Cat");
		testCategory = categoryRepo.save(testCategory);
		Tag testTag = new Tag("my cat");
		testTag = tagRepo.save(testTag);
		Review testReview = new Review("Phil", "image", testCategory, "image", "content", testTag);
		testReview = reviewRepo.save(testReview);
		Comment testComment = new Comment("Test", testReview);
		testComment = commentRepo.save(testComment);
		Comment testComment2 = new Comment("Test2", testReview);
		testComment2 = commentRepo.save(testComment2);

		entityManager.flush();
		entityManager.clear();

		Collection<Review> testReviews = reviewRepo.findAllByComments(testComment);
		Collection<Review> testReviews2 = reviewRepo.findAllByComments(testComment2);

		assertThat(testReviews, contains(testReview));
		assertThat(testReviews2, contains(testReview));
	}

	// @Test
	// public void shouldFindReviewByTag () {
	// Category testCategory = new Category("Cat");
	// testCategory = categoryRepo.save(testCategory);
	// Tag testTag = new Tag("my cat");
	// testTag = tagRepo.save(testTag);
	// Tag testTag2 = new Tag("best cat");
	// testTag2 = tagRepo.save(testTag2);
	// Review testReview = new Review("Phil", "image", testCategory, "image",
	// "content", testTag, testTag2);
	// testReview = reviewRepo.save(testReview);
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// assertThat(testReview.getTags(), contains(testTag));
	// }
	// Tag testTag = new Tag("my cat");
	// Tag testTag2 = new Tag("great cat");
	// Review review = new Review("Phil", "image", testCategory, "image", "content",
	// testTag, testTag2);

	// @Test
	// public void shouldSaveAndLoadReview() {
	// Tag testTag = new Tag("my cat");
	// Review review = new Review("Sea Shells", "image", null, "image", "content",
	// testTag);
	// testTag = tagRepo.save(testTag);
	// review = reviewRepo.save(review);
	// long reviewId = review.getId();
	// entityManager.flush();
	// entityManager.clear();
	// review = reviewRepo.findOne(reviewId);
	// assertThat(review.getHeader(), is("Sea Shells"));
	// }

	// @Test
	// public void shouldSaveReviewInCategory() {
	// Category animals = new Category("Animals");
	// Review cat = new Review("Phil", "image", animals, "image", "Loves Me");
	// animals = categoryRepo.save(animals);
	// cat = reviewRepo.save(cat);
	// long checkId = cat.getId();
	// entityManager.flush();
	// entityManager.clear();
	// animals = categoryRepo.findOne(checkId);
	// assertThat(animals.getReviews(), containsInAnyOrder(cat));
	// }
	//
	// @Test
	// public void shouldFindCategoryByReview() {
	// Category cats = new Category("Phil");
	// Review phil = new Review("Phil", "image", cats, "image", "content");
	// phil = reviewRepo.save(phil);
	// cats = categoryRepo.save(cats);
	// long philId = phil.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// phil = reviewRepo.findOne(philId);
	// Collection<Category> categories = categoryRepo.findByReviewsContains(phil);
	// assertThat(categories, containsInAnyOrder(cats));
	// }
	//
	// @Test
	// public void shouldFindCategorybyReviewId() {
	// Category cats = new Category("Phil");
	// Review phil = new Review("Phil", "image", cats, "image", "content");
	// phil = reviewRepo.save(phil);
	// cats = categoryRepo.save(cats);
	// long philId = phil.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// Collection<Category> categories = categoryRepo.findByReviewsId(philId);
	// assertThat(categories, containsInAnyOrder(cats));
	// }
	//
	// @Test
	// public void shouldEstablishReviewToCategoryRelationship() {
	// Category cats = new Category("Phil");
	// Review phil = new Review("Phil", "image", cats, "image", "content");
	// phil = reviewRepo.save(phil);
	// cats = categoryRepo.save(cats);
	// long catsId = cats.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// phil = reviewRepo.findOne(catsId);
	// Category check = phil.getCategory();
	// assertEquals(check, cats);
	// }
	//
	// @Test
	// public void shouldEstablishCategoryToReviewRelationship() {
	// Category cats = new Category("Phil");
	// Review phil = new Review("Phil", "image", cats, "image", "content");
	// phil = reviewRepo.save(phil);
	// cats = categoryRepo.save(cats);
	// long philId = phil.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// cats = categoryRepo.findOne(philId);
	// assertThat(cats.getReviews(), containsInAnyOrder(phil));
	// }
	//
	// @Test
	// public void shouldSaveTwoTagsToOneReview() {
	// Category testCategory = new Category("Phil");
	// Tag testTag = new Tag("my cat");
	// Tag testTag2 = new Tag("great cat");
	// Review testReview = new Review("Phil", "image", testCategory, "image",
	// "content", testTag, testTag2);
	// testCategory = categoryRepo.save(testCategory);
	// testReview = reviewRepo.save(testReview);
	// testTag = tagRepo.save(testTag);
	// testTag2 = tagRepo.save(testTag2);
	// long tagId = testTag.getId();
	// long tag2Id = testTag.getId();
	//
	// entityManager.flush();
	// entityManager.clear();
	//
	// Collection<Review> testReviewsTags = reviewRepo.findAllTags(testTag,
	// testTag2);
	//
	// assertThat(testReviewsTags, containsInAnyOrder(testReview));
	// }

}
