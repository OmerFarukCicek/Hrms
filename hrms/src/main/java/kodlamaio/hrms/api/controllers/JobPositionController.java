package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobpositions")
public class JobPositionController {
	private JobPositionService jobPositionsService;
	
	@Autowired
	public JobPositionController(JobPositionService jobPositionsService) {
		super();
		this.jobPositionsService = jobPositionsService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobPosition>> getAll(){
		return this.jobPositionsService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobPosition jobPosition) {
		return this.jobPositionsService.add(jobPosition);
	}
}
