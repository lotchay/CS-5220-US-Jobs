<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CS-5220-Group-Project</title>

<link href="css/vendor/bower.css" rel="stylesheet" />
<link href="css/us-jobs.css" rel="stylesheet" />
<link href="bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="row">
		<div class="col col-md-8 col-md-offset-2 jumbotron">
			<h1>U.S. Jobs</h1>
			<p>We all go to college in the hopes of landing good and
				high-paying jobs. Unfortunately, there are too many job websites out
				there such as Monster and CareerBuilder that mostly contain
				employment agencies’ listings designed to pool for candidates
				instead of bonafide employers listing real job openings. There are
				just too many unemployed recent college grads. Therefore, we want to
				create a website where recent college grads and college students can
				easily connect with bonafide employers with legit job openings
				without jumping through hoops.</p>
			<p>
				<a class="btn btn-primary btn-lg">Learn more</a>
			</p>
		</div>
	</div>

	<div class="row">
		<div class="col col-md-8 col-md-offset-2">
			<h1 class="text-primary">Trending Jobs</h1>
			<hr />
			<div class="list-group">
				<c:forEach items="${jobPostings}" var="jobPosting">
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
</body>

<script src="js/vendor/bower.js"></script>
</html>