package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDAO;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDAO jobPositionsDAO;
	
	@Autowired
	public JobPositionManager(JobPositionDAO jobPositionsDAO) {
		super();
		this.jobPositionsDAO = jobPositionsDAO;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionsDAO.findAll());
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if(this.jobPositionsDAO.getByName(jobPosition.getPosition())==null) {
			this.jobPositionsDAO.save(jobPosition);
			return new SuccessResult("İş alanı eklendi.");
		}
		return new ErrorResult("Böyle bir iş ilanı zaten var.");
	}

}
