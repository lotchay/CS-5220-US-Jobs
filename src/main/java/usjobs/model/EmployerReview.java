package usjobs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employer_reviews")
public class EmployerReview implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "employer_review_id")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "employer_review")
	private String employerReview;
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employerReviewed;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userPosted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployerReview() {
		return employerReview;
	}

	public void setEmployerReview(String employerReview) {
		this.employerReview = employerReview;
	}

	public Employer getEmployerReviewed() {
		return employerReviewed;
	}

	public void setEmployerReviewed(Employer employerReviewed) {
		this.employerReviewed = employerReviewed;
	}

	public User getUserPosted() {
		return userPosted;
	}

	public void setUserPosted(User userPosted) {
		this.userPosted = userPosted;
	}

}
