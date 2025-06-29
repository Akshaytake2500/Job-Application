package com.project.JobApp.Company;

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
@RequestMapping("/company")
public class CompanyController {

	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<List<Company>> getAllCompany(){
		return new ResponseEntity<List<Company>>(companyService.getAllCompanies(), HttpStatus.OK);
//		return ResponseEntity.ok(companyService.getAllCompanies());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
		return new ResponseEntity<Company>(companyService.getCompanyById(id),HttpStatus.FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company) {
		return new ResponseEntity<Company>(companyService.createCompany(company),HttpStatus.CREATED);
//		return ResponseEntity.ok(companyService.createCompany(company));	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable Long id) {
	    boolean updated = companyService.updateCompany(company, id);
	    if (updated) {
	        return ResponseEntity.accepted().body("Company updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
		boolean deletedCompany = companyService.deleteCompanyById(id);
		if(deletedCompany) {
			return ResponseEntity.ok("Deleted Successfully");			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
	}
	
}
