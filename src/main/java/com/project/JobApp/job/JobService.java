package com.project.JobApp.job;

import java.util.List;

public interface JobService {
	
	public List<Job> findAll();
	public void createJob(Job job);
	public Job getJobById(Long id);
	public boolean deleteById(Long id);
	public boolean updateJobById(Job job, Long id);
}
