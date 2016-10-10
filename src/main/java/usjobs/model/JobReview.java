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
@Table(name = "job_reviews")
public class JobReview implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "job_review_id")
	@GeneratedValue
	private Integer id;

	@Column(name = "job_review")
	private String jobReview;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private JobPosting jobReviewed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userPosted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobReview() {
		return jobReview;
	}

	public void setJobReview(String jobReview) {
		this.jobReview = jobReview;
	}

	public JobPosting getJobReviewed() {
		return jobReviewed;
	}

	public void setJobReviewed(JobPosting jobReviewed) {
		this.jobReviewed = jobReviewed;
	}

	public User getUserPosted() {
		return userPosted;
	}

	public void setUserPosted(User userPosted) {
		this.userPosted = userPosted;
	}

}
