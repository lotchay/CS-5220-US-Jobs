package usjobs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
@DiscriminatorValue("SEEKER")
public class JobSeeker extends User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name="supress_contact")
	private boolean supressContact;
	
	//jobs applied to
	@ManyToMany(mappedBy = "usersApplied", cascade = CascadeType.ALL)
	private List<JobPosting> appliedJobs;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Resume> resumes;
	
	@Type(type="text")
	private String experience;
		
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Degree> degrees;
		
	@Column(name="current_job_title")
	private String currentJobTitle;
		
	@OneToMany(mappedBy="userPosted", cascade = CascadeType.ALL)
	private List<JobReview> jobsReviewed;
		
	@OneToMany(mappedBy="userPosted", cascade = CascadeType.ALL)
	private List<EmployerReview> employersReviewed;

	public List<JobPosting> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<JobPosting> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}
	
	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public boolean isSupressContact() {
		return supressContact;
	}

	public void setSupressContact(boolean supressContact) {
		this.supressContact = supressContact;
	}

	public String getCurrentJobTitle() {
		return currentJobTitle;
	}

	public void setCurrentJobTitle(String currentJobTitle) {
		this.currentJobTitle = currentJobTitle;
	}

	public List<JobReview> getJobsReviewed() {
		return jobsReviewed;
	}

	public void setJobsReviewed(List<JobReview> jobsReviewed) {
		this.jobsReviewed = jobsReviewed;
	}

	public List<EmployerReview> getEmployersReviewed() {
		return employersReviewed;
	}

	public void setEmployersReviewed(List<EmployerReview> employersReviewed) {
		this.employersReviewed = employersReviewed;
	}
}
