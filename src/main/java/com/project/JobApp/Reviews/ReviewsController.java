package com.project.JobApp.Reviews;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewsController {

	private ReviewsService reviewsService;

	public ReviewsController(ReviewsService reviewsService) {
		super();
		this.reviewsService = reviewsService;
	}
	
	@GetMapping("/reviews")
	public  ResponseEntity<List<Reviews>> getAllReviews(@PathVariable Long companyId){
		return new ResponseEntity<>(reviewsService.getAllReviews(companyId),HttpStatus.OK);
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Reviews> getReview(@PathVariable Long companyId, @PathVariable Long reviewId){
		return new ResponseEntity<>(reviewsService.getReview(companyId, reviewId),HttpStatus.FOUND);
	}
	
	@PostMapping("/reviews")
	public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Reviews reviews){
		boolean  isReviewSaved=reviewsService.addReview(companyId, reviews);
		if(isReviewSaved) {
			return new ResponseEntity<>("Review added successfully",HttpStatus.CREATED);			
		}
		return new ResponseEntity<String>("Review does not saved",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Reviews review){
		boolean isUpdated = reviewsService.updateReview(companyId,reviewId,review);
		if(isUpdated) {
			return new ResponseEntity<>("Update successfully",HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>("Update unsuccessfull",HttpStatus.NOT_MODIFIED);
		}
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
		boolean isReviewDeleted = reviewsService.deleteReviewById(companyId,reviewId);
		if(isReviewDeleted) {
			return new ResponseEntity<>("Review Delete Successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Review Record Not Found",HttpStatus.NOT_FOUND);
		}
	}
}
