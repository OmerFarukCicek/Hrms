package kodlamaio.hrms.entities.concretes.Dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerForRegisterDto {

	private String firstName;
	private String lastName;
	private String nationalId;
	private Date dateOfBirth;
	private String email;
	private String password;
	private String verifyPassword;
}
