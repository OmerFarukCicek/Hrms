package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDAO;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.concretes.Dtos.JobSeekerForRegisterDto;

@Service
public class JobSeekerManager implements JobSeekerService{

	private JobSeekerDAO jobseekerDao;
	private AuthService authService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDAO jobseekerDao, AuthService authService) {
		super();
		this.jobseekerDao = jobseekerDao;
		this.authService = authService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>(this.jobseekerDao.findAll());
	}

	@Override
	public Result register(JobSeekerForRegisterDto jobSeekerForRegisterDto) {
		JobSeeker jobSeekerToRegister = new JobSeeker(
				jobSeekerForRegisterDto.getFirstName(), 
				jobSeekerForRegisterDto.getLastName(), 
				jobSeekerForRegisterDto.getNationalId(), 
				jobSeekerForRegisterDto.getDateOfBirth());	
		jobSeekerForRegisterDto.setEmail(jobSeekerForRegisterDto.getEmail());
		jobSeekerForRegisterDto.setPassword(jobSeekerForRegisterDto.getPassword());
		if(this.authService.checkEmail(jobSeekerForRegisterDto.getEmail()) && 
				this.authService.checkPassword(jobSeekerForRegisterDto.getPassword(), 
						jobSeekerForRegisterDto.getVerifyPassword()) &&
				this.authService.mernisCheck(jobSeekerToRegister)
				) {
			this.jobseekerDao.save(jobSeekerToRegister);
			return new SuccessResult("İş arayan kişi eklendi.");
		}	
		return new SuccessResult("Kayıt başarısız.");
	}

}
