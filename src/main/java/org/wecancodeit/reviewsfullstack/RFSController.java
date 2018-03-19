package org.wecancodeit.reviewsfullstack;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RFSController {

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	TagRepository tagRepo;

	@Resource
	CommentRepository commentRepo;

	@RequestMapping("/categories")
	public String getAllCategories(Model model) {
		model.addAttribute("categoriesModel", categoryRepo.findAll());
		return "categoriesView";
	}

	@RequestMapping("/reviewbycategory")
	public String getAReviewFromACategory(@RequestParam Long id, Model model) {
		model.addAttribute("reviewByCategoryModel", reviewRepo.findAllByCategory(categoryRepo.findOne(id)));
		return "reviewByCategoryView";
	}

	@RequestMapping("/review")
	public String getSingleReview(@RequestParam Long id, Model model) {
		model.addAttribute("reviewModel", reviewRepo.findOne(id));
		return "reviewView";
	}

	@RequestMapping("/add-comment")
	public String addAComment(String stringId, String content) {
		Long id = Long.parseLong(stringId);
		Review review = reviewRepo.findOne(id);
		Comment comment = new Comment(content, review);
		comment = commentRepo.save(comment);
		return "redirect:/review?id=" + stringId;
	}

	@RequestMapping("/add-tag") // Thanks KC
	public String addTag(Long reviewId, String description, Model model) {
		Review nReview = reviewRepo.findOne(reviewId);
		if (nReview != null && description != null) {
			Tag eTag = tagRepo.findByDescription(description);
			if (eTag == null) {
				Tag nTag = new Tag(description, nReview);
				tagRepo.save(nTag);
				nReview.addTag(nTag);
				reviewRepo.save(nReview);
			} else {
				if (nReview.tagExists(eTag.getId()) == false) {
					nReview.addTag(eTag);
					reviewRepo.save(nReview);
				}
			}
			model.addAttribute("review", nReview);
		}

		return "addTagView";
	}

	@RequestMapping("/delete-tag") // KC is a genius.
	public String deleteTag(long reviewId, String description, Model model) {
		Review nReview = reviewRepo.findOne(reviewId);
		if (nReview != null && description != null) {
			Tag toDelete = tagRepo.findByDescription(description);
			if (toDelete != null) {
				Iterator<Tag> tags = nReview.getTags().iterator();
				while (tags.hasNext()) {
					Tag cTag = tags.next();
					if (cTag.getId() == toDelete.getId()) {
						tags.remove();
					}
				}
				reviewRepo.save(nReview);
			}
			model.addAttribute("review", nReview);
		}
		return "addTagView";
	}

	@RequestMapping("tag")
	public String getATag(@RequestParam Long id, Model model) {
		Tag tag = tagRepo.findOne(id);
		model.addAttribute("tagModel", tag);
		model.addAttribute("reviewModel", reviewRepo.findOne(id));
		model.addAttribute("reviewsByTagsModel", reviewRepo.findByTagsContains(tag));
		return "tagsView";
	}
}
