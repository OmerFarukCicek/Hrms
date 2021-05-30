package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobPositionDAO extends JpaRepository<JobPosition, Integer>{
	JobPosition getByName(String name);
}
