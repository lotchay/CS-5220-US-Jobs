package usjobs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employers")
public class Employer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "employer_id")
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userLoginId;
	
	@Column(name = "employer_name")
	private String employerName;
	
	@Column(name = "employer_website")
	private String employerWebsite;
	
	//list of jobs by employer
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Job> jobsPosted;
	
	@OneToMany(mappedBy = "employerReviewed", cascade = CascadeType.ALL)
	private List<EmployerReview> employerReviews;
	
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
	private List<Contact> contacts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerWebsite() {
		return employerWebsite;
	}

	public void setEmployerWebsite(String employerWebsite) {
		this.employerWebsite = employerWebsite;
	}

	public List<Job> getJobsPosted() {
		return jobsPosted;
	}

	public void setJobsPosted(List<Job> jobsPosted) {
		this.jobsPosted = jobsPosted;
	}

	public List<EmployerReview> getEmployerReviews() {
		return employerReviews;
	}

	public void setEmployerReviews(List<EmployerReview> employerReviews) {
		this.employerReviews = employerReviews;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public User getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(User userLoginId) {
		this.userLoginId = userLoginId;
	}
}
