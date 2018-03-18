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
		model.addAttribute("reviewTagModel", tagRepo.findAllByReviews(reviewRepo.findOne(id)));
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

	@RequestMapping(value = "/tags")
	public String getAllTags(Model model) {
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "tagsView";
	}

	// @RequestMapping("tag")
	// public String getSingleTag(@RequestParam Long id, Model model) {
	// return "tagView";
	// }

}
