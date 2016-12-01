package usjobs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DiscriminatorValue("EMPLOYER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employer extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "employer_name")
    private String employerName;

    @Column(name = "employer_website")
    private String employerWebsite;

    // list of jobs by employer
    @OneToMany(mappedBy = "company",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<JobPosting> jobsPosted;

    @OneToMany(mappedBy = "employerReviewed",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<EmployerReview> employerReviews;

    public String getEmployerName() {

        return employerName;
    }

    public void setEmployerName( String employerName ) {

        this.employerName = employerName;
    }

    public String getEmployerWebsite() {

        return employerWebsite;
    }

    public void setEmployerWebsite( String employerWebsite ) {

        this.employerWebsite = employerWebsite;
    }

    public List<JobPosting> getJobsPosted() {

        return jobsPosted;
    }

    public void setJobsPosted( List<JobPosting> jobsPosted ) {

        this.jobsPosted = jobsPosted;
    }

    public List<EmployerReview> getEmployerReviews() {

        return employerReviews;
    }

    public void setEmployerReviews( List<EmployerReview> employerReviews ) {

        this.employerReviews = employerReviews;
    }

}
