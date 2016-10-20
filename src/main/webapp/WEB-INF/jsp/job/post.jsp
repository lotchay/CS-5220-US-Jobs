<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Posting ${jobPosting.jobTitle}</title>

<link href="../css/vendor/bower.css" rel="stylesheet" />
<link href="../css/us-jobs.css" rel="stylesheet" />
<link href="../bower_components/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="row">
		<div class="col col-md-8 col-md-offset-2">
			<h3>${jobPosting.jobTitle }</h3>
			<p>${jobPosting.jobDescription }</p>
			<security:authorize access="hasRole('SEEKER') or hasRole('ADMIN')">
				<div class="container">
					<c:choose>
						<c:when test="${jobPosting.usersFavorited.contains(currentUser)}">
							<form method="post" action="favorite.html?jobid=${jobPosting.id}">
								<button type="submit" id="favorite" class="btn btn-info"><i class="fa fa-undo" aria-hidden="true"></i>
								&nbsp;&nbsp;Unfavorite</button>
							</form>
						</c:when>
						<c:otherwise>
							<form method="post" action="favorite.html?jobid=${jobPosting.id}">
								<button type="submit" id="favorite" class="btn btn-info">
								<i class="fa fa-star" aria-hidden="true"></i>&nbsp;&nbsp;Favorite</button>
							</form>
						</c:otherwise>
					</c:choose>
					<br />
					<c:choose>
						<c:when test="${jobPosting.usersApplied.contains(currentUser)}">
						<button class="btn btn-info" disabled><i class="fa fa-check-square-o" aria-hidden="true"></i>&nbsp;&nbsp;Applied</button>
						</c:when>
						<c:otherwise>
							<form method="post" action="apply.html?jobid=${jobPosting.id}">
								<button type="submit" id="apply" class="btn btn-info">Apply</button>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</security:authorize>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-8 col-md-offset-2">
			<p class="text-success" id="status-msg"></p>
		</div>
	</div>
</body>

<script src="../js/vendor/bower.js"></script>
<script src="../js/job-posting.js"></script>

</html>