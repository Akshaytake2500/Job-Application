package com.project.JobApp.job;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService{
	
//	private List<Job> jobs = new ArrayList<>();
	public JobRepository jobRepository;
	
	public JobServiceImpl(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll(); 
	}

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJobById(Long id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public boolean deleteById(Long id) {
	    Optional<Job> jobOptional = jobRepository.findById(id);
	    if (jobOptional.isPresent()) {
	        jobRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}

	@Override
	public boolean updateJobById(Job updatedJob, Long id) {
	    Optional<Job> optionalJob = jobRepository.findById(id);
	    if (optionalJob.isPresent()) {
	        Job job = optionalJob.get();

	        // ❌ DO NOT set job.setId(updatedJob.getId());
	        // The ID is already correct from the DB
	        job.setTitle(updatedJob.getTitle());
	        job.setDescription(updatedJob.getDescription());
	        job.setMinSalary(updatedJob.getMinSalary());
	        job.setMaxSalary(updatedJob.getMaxSalary());
	        job.setLocation(updatedJob.getLocation());

	        jobRepository.save(job); // persist the changes
	        return true;
	    }
	    return false;
	}


}
