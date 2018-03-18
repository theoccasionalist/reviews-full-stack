package org.wecancodeit.reviewsfullstack;

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
		// model.addAttribute("reviewTagModel",
		// tagRepo.findAllByReviews(reviewRepo.findOne(id)));
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

	@RequestMapping("/add-tag")
	public String addTag(Long reviewId, String description, Model model) {
		Review nReview = reviewRepo.findOne(reviewId);
		if (nReview != null && description != null) {
			Tag eTag = tagRepo.findByDescription(description);
			if (eTag == null) {
				Tag nTag = new Tag(description, nReview);
				tagRepo.save(nTag);
				nReview.addTag(nTag);
				reviewRepo.save(nReview); // two types of save
			} else {
				if (nReview.tagExists(eTag.getId()) == false) {
					nReview.addTag(eTag);
					reviewRepo.save(nReview); // two types of save
				}
			}
			model.addAttribute("review", nReview); // might need to be reviewModel
		}

		return "addTagView";
	}

	// @RequestMapping("/add-tag")
	// public String addATag(String stringId, String description) {
	// Long id = Long.parseLong(stringId);
	// Review review = reviewRepo.findOne(id);
	// Tag tag = new Tag(description, review);
	// tag = tagRepo.save(tag);
	// return "redirect:/review?id=" + stringId;
	// }
	//
	// @RequestMapping(value = "/tags")
	// public String getAllTags(Model model) {
	// model.addAttribute("tagsModel", tagRepo.findAll());
	// return "tagsView";
	// }

	// @RequestMapping("tag")
	// public String getSingleTag(@RequestParam Long id, Model model) {
	// return "tagView";
	// }

}
