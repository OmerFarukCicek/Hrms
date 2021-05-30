package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDAO;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.Dtos.EmployerForRegisterDto;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDAO employerDAO;
	private AuthService authService;
	
	@Autowired
	public EmployerManager(EmployerDAO employerDAO, AuthService authService) {
		super();
		this.employerDAO = employerDAO;
		this.authService = authService;
	}
	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDAO.findAll());
	}

	@Override
	public Result register(EmployerForRegisterDto employer) {		
		Employer employerToRegister = new Employer(
				employer.getCompanyName(), 
				employer.getWebsite(), 
				employer.getPhoneNumber());
		employer.setEmail(employer.getEmail());
		employer.setPassword(employer.getPassword());
		if(this.authService.checkEmail(employer.getEmail()) && 
				this.authService.checkPassword(employer.getPassword(), employer.getVerifyPassword())) {
			this.employerDAO.save(employerToRegister);
			return new SuccessResult("İşveren kayıt edildi.");
		}			
		return new ErrorResult("Kayıt başarısız.");
	}

}
