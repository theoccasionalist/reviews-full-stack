package org.wecancodeit.reviewsfullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Override
	public void run(String... args) throws Exception {

		Category cats = new Category("Cats");
		cats = categoryRepo.save(cats);
		Review phil = new Review("Phil", cats, "Image", "Phil Cat");
		phil = reviewRepo.save(phil);
	}

}
