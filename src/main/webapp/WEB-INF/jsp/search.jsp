<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
	<div class="col col-md-8 col-md-offset-2">
		<h1 class="text-primary">Job Results</h1>
		<hr />
		<div class="list-group">
			<c:forEach items="${searchResultJob}" var="jobPosting">
				<a href="job/view.html?jobid=${jobPosting.id}"
					class="list-group-item">
					<h4 class="list-group-item-heading">${jobPosting.jobTitle}</h4>
					<h6>${jobPosting.company.employerName}</h6>
					<div class="job-listing-preview">
						<p class="list-group-item-text">${jobPosting.jobDescription }</p>
					</div>
				</a>
			</c:forEach>
		</div>
		<div class="btn-container row col col-md-2 col-md-offset-5">
			<button class="btn btn-singleton btn-info">Show More</button>
		</div>
	</div>
</div>