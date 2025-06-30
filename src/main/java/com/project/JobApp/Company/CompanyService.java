package com.project.JobApp.Company;

import java.util.List;

public interface CompanyService {

	public List<Company> getAllCompanies();
	public Company createCompany(Company company);
	public boolean updateCompany(Company company, Long id);
	public boolean deleteCompanyById(Long id);
	public Company getCompanyById(Long id);

}
