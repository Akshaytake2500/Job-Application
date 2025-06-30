package com.project.JobApp.Reviews;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.JobApp.Company.Company;
import com.project.JobApp.Company.CompanyService;

@Service
public class ReviewsServiceImpl implements ReviewsService{

	private ReviewsRepository reviewsRepository;
	private CompanyService companyService;

	public ReviewsServiceImpl(ReviewsRepository reviewsRepository, CompanyService companyService) {
		super();
		this.reviewsRepository = reviewsRepository;
		this.companyService = companyService;
	}

	@Override
	public List<Reviews> getAllReviews(Long companyId) {
		List<Reviews> reviews = reviewsRepository.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(Long companyId, Reviews reviews) {
		Company company = companyService.getCompanyById(companyId);
		if(company != null) {
			reviews.setCompany(company);
			reviewsRepository.save(reviews);	
			return true;
		}	
		return false;
	}

	@Override
	public Reviews getReview(Long companyId, Long reviewId) {
		List<Reviews> reviews = reviewsRepository.findByCompanyId(companyId);
		return reviews.stream()
				.filter(review -> review.getId().equals(reviewId))
				.findFirst()
				.orElse(null);
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Reviews review) {
		if(companyService.getCompanyById(companyId) != null) {
			review.setCompany(companyService.getCompanyById(companyId));
			review.setId(reviewId);
			reviewsRepository.save(review);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteReviewById(Long companyId, Long reviewId) {
			if(companyService.getCompanyById(companyId) != null
					&& reviewsRepository.existsById(reviewId)) {
				Reviews review = reviewsRepository.findById(reviewId).orElse(null);
				Company company = review.getCompany();
				company.getReviews().remove(review);
				review.setCompany(null);
				companyService.updateCompany(company, companyId);
				reviewsRepository.deleteById(reviewId);
				return true;
			}
			return false;
	}
	
	
	
	
}
