package usjobs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("EMPLOYER")
public class Employer extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "employer_website")
	private String employerWebsite;

	//list of jobs by employer
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<JobPosting> jobsPosted;

	@OneToMany(mappedBy = "employerReviewed", cascade = CascadeType.ALL)
	private List<EmployerReview> employerReviews;

	public String getEmployerWebsite() {
		return employerWebsite;
	}

	public void setEmployerWebsite(String employerWebsite) {
		this.employerWebsite = employerWebsite;
	}

	public List<JobPosting> getJobsPosted() {
		return jobsPosted;
	}

	public void setJobsPosted(List<JobPosting> jobsPosted) {
		this.jobsPosted = jobsPosted;
	}

	public List<EmployerReview> getEmployerReviews() {
		return employerReviews;
	}

	public void setEmployerReviews(List<EmployerReview> employerReviews) {
		this.employerReviews = employerReviews;
	}
	
}
