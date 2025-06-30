package com.project.JobApp.Reviews;

import java.util.List;

public interface ReviewsService {

	List<Reviews> getAllReviews(Long companyId);
	Reviews getReview(Long companyId, Long reviewId);
	boolean addReview(Long companyId, Reviews reviews);
	boolean updateReview(Long companyId, Long reviewId, Reviews review);
	boolean deleteReviewById(Long companyId, Long reviewId);
	

}
