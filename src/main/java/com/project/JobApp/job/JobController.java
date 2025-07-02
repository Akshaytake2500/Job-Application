package com.project.JobApp.job;

import java.util.List;

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
@RequestMapping("/jobs")
public class JobController {
	
	private JobService jobService;

	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	
	//two ways to write ResponseEntity

	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
//		return new ResponseEntity<List<Job>>(jobService.findAll(),HttpStatus.OK);
		return ResponseEntity.ok(jobService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		if(job!= null) {
			return ResponseEntity.ok(job);
		}
		return ResponseEntity.notFound().build();
//		return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.createJob(job);
//		return new ResponseEntity<>("Job created successfully", HttpStatus.CREATED);
		return ResponseEntity.ok("Job created successfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable Long id) {
		boolean updatedJob = jobService.updateJobById(job, id);
		if(updatedJob) {
			return ResponseEntity.ok("Job updated successfully");			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		boolean deleted = jobService.deleteById(id);
		if(deleted) {
			return ResponseEntity.ok("Job deleted successfully");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
