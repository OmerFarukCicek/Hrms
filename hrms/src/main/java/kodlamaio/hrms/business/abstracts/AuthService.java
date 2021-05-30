package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface AuthService {
	boolean checkEmail(String email);
	boolean checkPassword(String password, String verifyPassword);
	boolean mernisCheck(JobSeeker jobSeeker);
}
