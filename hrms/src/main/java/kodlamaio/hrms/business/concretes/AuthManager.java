package kodlamaio.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.Adapters.Abstracts.MernisService;
import kodlamaio.hrms.Adapters.Concrates.MernisServiceAdapter;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.dataAccess.abstracts.UserDAO;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class AuthManager implements AuthService{

	private UserDAO userDao;
	private MernisService mernisService;
	
	public AuthManager(UserDAO userDao, MernisService mernisService) {
		super();
		this.userDao = userDao;
		this.mernisService = mernisService;
	}

	@Override
	public boolean checkEmail(String email) {
		if(this.userDao.getByEmail(email)) {
			System.out.println("Böyle bir email zaten var.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkPassword(String password, String verifyPassword) {
		if(password==verifyPassword) {
			return true;
		}
		System.out.println("Şifreler uyumsuz.");
		return false;
	}

	@Override
	public boolean mernisCheck(JobSeeker jobSeeker) {
		if(Integer.parseInt(jobSeeker.getNationalId())!=11) {
			System.out.println("Kimlik doğrulandı.");
			return false;
		}
		return true;
	}

}
