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
		Category fish = new Category("Fish");
		cats = categoryRepo.save(cats);
		fish = categoryRepo.save(fish);

		reviewRepo.save(new Review("Phil", cats, "Images", "Phil Cat"));
		reviewRepo.save(new Review("Fusa", cats, "Images", "Fusa Cat"));
		reviewRepo.save(new Review("AquaMan", fish, "Images", "Fish Man"));
		reviewRepo.save(new Review("AquaLad", fish, "Images", "Fish Lad"));
	}

}
