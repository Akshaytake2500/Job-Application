package com.project.JobApp.Reviews;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long>{

	List<Reviews> findByCompanyId(Long companyId);

}
