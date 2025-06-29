package com.project.JobApp.Company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	private CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public boolean updateCompany(Company updateCompany, Long id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if(optionalCompany.isPresent()) {
			Company company = optionalCompany.get();
			company.setName(updateCompany.getName());
			company.setDescription(updateCompany.getDescription());
			companyRepository.save(company);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCompanyById(Long id) {
		Optional<Company> optionalCompany = companyRepository.findById(id);
		if(optionalCompany.isPresent()) {
			companyRepository.deleteById(id);
			return true;			
		}
		return false;
	}

	@Override
	public Company getCompanyById(Long id) {
		return companyRepository.findById(id).orElse(null);
	}

}
