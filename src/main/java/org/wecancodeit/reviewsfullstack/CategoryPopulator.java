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
		Tag tag2 = new Tag("Other Stuff");
		tag2 = tagRepo.save(tag2);

		reviewRepo.save(new Review("The Ultimate Warrior", "/images/ultimatewarrior.png", tvPeople,
				"/images/ultimatewarrior2.jpg", "Mean Machine", tag));
		reviewRepo.save(new Review("Xena: Warrior Princess", "/images/xena.png", tvPeople, "/images/xena2.jpg",
				"Warrior Princess", tag2));
		reviewRepo.save(new Review("Ron Swanson", "/images/ronswanson.png", tvPeople, "/images/ronandxena.jpg",
				"Ron Swanson wrote his will at the age of eight.  It reads: \"Upon my death, all of my belongings shall transfer to the man or animal who has killed me.\"  It is thus only natural that Ron Swanson and Xena, the Warrior Princess, developed a deep romance and chose to cement their love in the bond of marriage.  Perhaps, with their powers combined, the Utlimate Warrior's long reign of terror may come to a close.",
				tag));
		reviewRepo.save(new Review("Korg Volca Keys", "/images/volca.png", synthesizers, "/images/volca2.jpg",
				"The Korg Volca Keys is an analog loop synth.  It is the first synthesizer I ever bought.  Sometimes it sounds like a dying chicken and other times it sounds like rocks clanging around in space.  Yet other times it sounds like a happy family of robots camping near some moon lake or other.  All around recommened.",
				tag));
		reviewRepo.save(new Review("Teenage Engineering PO-18 Factory", "/images/po.jpg", synthesizers,
				"/images/factory2.jpg",
				"I absolutely adore the Teenage Engineering Pocket Operator series of synthesizers.  The Factory was among the first three released (alongside the Rythm and Sub).  The Factory is meant as a melody synth, although I've rarely used it as a lead.  I tend to use it for chord work and effects.  I think it's the synth I've used the most in recordings, especially since it easily chains patterns (unlike the Volca series).  The question is: do I love the Factory more than Keys?  Tough question.  It's like asking who I love more: the son that lives in my basement spending all my cash but has a great sense of humor, or the son who is the CEO of sucessful company and donates a third of his billions to charity?  They're both my sons, I ought not make that deceision. Nor can I.",
				tag));
		reviewRepo.save(new Review("Roland TR-909", "/images/909.png", synthesizers, "/images/9092.jpg",
				"The 909 is the successor to the 808.  While the 808 has come to be the iconic sound of hip-hop and rap from the eighties onward, I for some reason prefer the 909 sounds.  This is not a preference that I've developed rationally or with due  deliberation, though.  It's just some weird thing.  Anyway, I've never seen a 909 in person (or an 808 for that matter).  But I've heard many samplings and reconstructions of its sounds.  They're my standard goto when firing up a beat in Ableton.  I guess that's about it. The 909.  It's the sounds of the eighties in the now.",
				tag));
		reviewRepo.save(new Review("The Wizard of Earthsea by Ursula K. Le Guin", "/images/leguin.jpg", books,
				"/images/woes.jpg",
				"The above image is the release version of the Wizard of Earthsea that I own.  I had no idea who Ursula Le Guin was when I bought the book (in a completed trilogy set, including then the Tombs of Atuan and the Farthest Shore).  Really, I got it simply becuase I found the medieval-ish, blocky, pastel cover art captivating.  I've been a Le Guin fan ever since.  Her books don't get lost in the minutiae of world building or extravagant technical descriptions.  She concentrates on the internal world of her charcters, and does so in a way that expresses the rich complexities of one's mental life with an expertly chosen, minimal set of words.  Another thing worth mentioning: Le Guin's rendition of the Tao Te Ching is PHENOMENAL.  It's not the most technically precise (in terms of word-to-word translation), but nonetheless genuinely captures the essence (or non-essence!) of Daoist thought in English idiom.  Finally, another fact: Le Guin attended the same high school--at the same time--as Philip K. Dick.  She said she didn't know him and that, although his name is in the yearbook, his picture is not.  That's such a Philip K. Dick thing to do.",
				tag));
		reviewRepo.save(new Review("Philip K. Dick and Philosophy, edited by D.E. Wittkower", "/images/pkd2.jpg", books,
				"/images/pkdap.jpg",
				"This review is not about a Philip K. Dick book.  It's about an essay appearing in Philip K. Dick and Philosophy.  The essay's name is: \"The Blob Necessitates.\" One reviewer on Amazon had quite a list of complaints lodged at the book, and had this to say in particular about the Blob Necessitates:\"Matthew Mccall's \"The Blob Necessitates\" drowns the reader in Spinoza's incomprehensible view of the totality of reality (hey, you're either in the blob or outside of it, and nothing's outside the blob!)\"  Now, the kind-hearted editor of the collection responded to the reviewer, and had these encouraging words to say about the author of the Blob Necessitates: \"McCall: Sounds like a good treatment of some of PKD's later views to me.\"  This being an internet battle, the words were of course to no avail.  The reviewer responded to our indefatigable editor, reemphasizing his point:\"Not to rehash my criticism of McCall's contribution, but I absolutely drowned in his presentation of Spinoza's view of reality.\"  So ends the great Blob Necessitates debate.  But to top it all off, I submitted the final draft thinking I'd named it the Blob Necessities, not the Blob Necessitates.  My title was a typo.  Perhaps the reviewer wasn't so wrong after all.",
				tag));
		reviewRepo.save(new Review("The Search After Truth by Nicolas Malebranche", "/images/malebranche.jpg", books,
				"/images/sat.jpg",
				"The Search After Truth is Nicolas Malebranche's primary philosophical treatise.  The Search is the first work in which Malebranche clearly expounds on the two views one immediately calls to mind when they hear his name: occasionalism and the vision in God doctrine.  In a broad sense, occasionalism is composed of two related claims: (a) no natural substances are causally efficacious and (b) God is the only true cause.  The term \"occasionalism\" derives from consequent claim that created substances are merely \"occasional causes,\" or causes that \"occasion\" or prompt God to act.  My Github name and current Github photo stand in honor of this obscure and incredulous claim.  As mentioned previously, Malebranche's second claim to fame is the Vision in God doctrine. The short (and debatable) version of the doctrine is this: visually perceived shape is ontologically identical to God's idea of extension (an idea which itself is ontologically identical to God).  Thus, when one sees shape, they see God directly.  There are various reasons Malebranche has (or may of had) for proposing this unpopular doctrine.  For some of those (possible) reasons, see the fourth chapter of my dissertation.  The chapter is titled \"the Divine Pineal Gland.\"",
				tag));

	}

}
