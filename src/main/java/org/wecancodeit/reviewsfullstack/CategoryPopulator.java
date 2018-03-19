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

	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {

		Category tvPeople = new Category("/images/tv.png");
		Category synthesizers = new Category("/images/synthesizer.png");
		Category books = new Category("/images/books.png");
		tvPeople = categoryRepo.save(tvPeople);
		synthesizers = categoryRepo.save(synthesizers);
		books = categoryRepo.save(books);

		Tag tag = new Tag("Stuff");
		tag = tagRepo.save(tag);

		reviewRepo.save(new Review("The Ultimate Warrior", "/images/ultimatewarrior.png", tvPeople,
				"/images/ultimatewarrior2.jpg", "Mean Machine", tag));
		reviewRepo.save(new Review("Xena: Warrior Princess", "/images/xena.png", tvPeople, "/images/xena2.jpg",
				"Warrior Princess", tag));
		reviewRepo.save(new Review("Ron Swanson", "/images/ronswanson.png", tvPeople, "/images/ronandxena.jpg",
				"He is Ron Swanson", tag));
		reviewRepo.save(new Review("Korg Volca Keys", "/images/volca.png", synthesizers, "/images/volca2.jpg",
				"It's an analog syth looper", tag));
		reviewRepo.save(new Review("Teenage Engineering PO-18 Factory", "/images/po.jpg", synthesizers,
				"/images/factory2.jpg", "It's a synthesizer in your pocket.", tag));
		reviewRepo.save(new Review("Roland TR-909", "/images/909.png", synthesizers, "/images/9092.jpg",
				"The sounds of the eighties in the now", tag));
		reviewRepo.save(new Review("The Wizard of Earthsea by Ursula K. Le Guin", "/images/leguin.jpg", books,
				"/images/woes.jpg", "Went to the same high school as Philip K. Dick at the same time.", tag));
		reviewRepo.save(new Review("Philip K. Dick and Philosophy, edited by D.E. Wittkower", "/images/pkd2.jpg", books,
				"/images/pkdap.jpg",
				"Essays on a guy who went to the same high school at the same time as Ursula Le Guin.", tag));
		reviewRepo.save(new Review("The Search After Truth by Nicolas Malebranche", "/images/malebranche.jpg", books,
				"/images/sat.jpg", "A book by a crazy man.", tag));

	}

}
