package usjobs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue
	private Integer id;
	
	//login info
	private String username;
	
	private String password;
	
	private boolean enabled = true;
	
	private boolean reported;
	
	@Column(name="supress_contact")
	private boolean supressContact;
	
	private String email;
		
	@ElementCollection
	@CollectionTable(
		name="user_roles",
		joinColumns = @JoinColumn(name = "user_id")
	)
	@Column(name="role")
	private List<String> userRoles;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String address;
	
	@ElementCollection
	@CollectionTable(
		name="user_phones",
		joinColumns = @JoinColumn(name = "user_id")
	)
	@Column(name="phone")
	@OrderBy("asc")
	private List<String> phones;
	
	//jobs applied to
	@ManyToMany(mappedBy = "usersApplied", cascade = CascadeType.ALL)
	private List<Job> appliedJobs;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	List<Resume> resumes;
	
	@Type(type="text")
	private String experience;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	List<Degree> degrees;
	
	@Column(name="job_title")
	private String jobTitles;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Job> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<Job> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
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

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public boolean isSupressContact() {
		return supressContact;
	}

	public void setSupressContact(boolean supressContact) {
		this.supressContact = supressContact;
	}

	public String getJobTitles() {
		return jobTitles;
	}

	public void setJobTitles(String jobTitles) {
		this.jobTitles = jobTitles;
	}
}