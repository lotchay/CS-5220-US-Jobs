<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
	<div class="col col-md-8 col-md-offset-2">
		<c:choose>
		<c:when test="${empty searchResultJob && empty searchResultUser && empty searchResultEmail}">
			<h1 class="text-warning">No results found.</h1>
		</c:when>
		<c:when test="${not empty searchResultUser }">
			<h1 class="text-primary">User Results</h1>
			<hr />
			<div class="list-group">
				<c:forEach items="${searchResultUser}" var="user">
					<a href="user/edit.html?id=${user.id}"
						class="list-group-item">
						<h4 class="list-group-item-heading">${user.email}</h4>
					</a>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${not empty searchResultEmail }">
			<h1 class="text-primary">Email User</h1>
			<hr />
			<h4>${searchResultEmail.firstName } ${searchResultEmail.lastName }</h4>
			<h4>${searchResultEmail.email }</h4>
			<c:if test="${searchResultEmail.reported }">
				<h4>${searchResultEmail.username } has been reported.</h4>
			</c:if>
		</c:when>
		<c:otherwise>
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
		</c:otherwise>
		</c:choose>
	</div>
</div>