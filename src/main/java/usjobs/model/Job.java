package usjobs.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "jobs")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "job_id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	@Column(name = "job_description")
	@Type(type="text")
	private String jobDescription;
	
	private String employer;
	
	private String location;
	
	private String salary;
	
	@Column(name = "date_posted")
	private Date datePosted;
	
	@Column(name = "date_closed")
	private Date dateClosed;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "applied",
			joinColumns = @JoinColumn(name = "job_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> usersApplied;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "favorites",
			joinColumns = @JoinColumn(name = "job_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> usersFavorited;
	
	@OneToMany(mappedBy="jobApplied", cascade = CascadeType.ALL)
	List<Application> applications;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public List<User> getUsersApplied() {
		return usersApplied;
	}

	public void setUsersApplied(List<User> usersApplied) {
		this.usersApplied = usersApplied;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public Date getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public List<User> getUsersFavorited() {
		return usersFavorited;
	}

	public void setUsersFavorited(List<User> usersFavorited) {
		this.usersFavorited = usersFavorited;
	}	
}