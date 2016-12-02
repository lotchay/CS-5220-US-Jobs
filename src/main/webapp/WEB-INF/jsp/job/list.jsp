<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<div class="animated fadeIn row">
	<div class="col-md-8 col-md-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-tasks" aria-hidden="true"></i>&nbsp;&nbsp;Job Posting Management
				</h3>
			</div>
			<div class="panel-body">
				<table id="postings" class="table table-striped table-hover">
					<tr class="info">
						<th>ID</th>
						<th>Job Title</th>
						<th>Employer</th>
						<th>Location</th>
						<th>Salary</th>
						<th>Website</th>
						<th>Operations</th>
					</tr>
					<c:forEach items="${jobPostings}" var="jobPosting">
						<tr data-job-posting-id="${jobPosting.id}">
							<td data-field="id">${jobPosting.id}</td>
							<td data-field="jobTitle">${jobPosting.jobTitle}</td>
							<td data-field="employerName">${jobPosting.company.employerName}</td>
							<td data-field="location">${jobPosting.location}</td>
							<td data-field="salary">${jobPosting.salary}</td>
							<td data-field="website">${jobPosting.website}</td>
							<td>
								<a class="btn btn-info viewPosting" role="button" href="javascript:void(0)">
									<i class="fa fa-opencart" aria-hidden="true"></i>&nbsp;&nbsp;View
								</a>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									<c:if test="${jobPosting.enabled}">
										<span class="disableEnable">
											<a class="btn btn-danger disablePosting" role="button" href="javascript:void(0)">
												<i class="fa fa-level-down" aria-hidden="true"></i>&nbsp;&nbsp;Disable
											</a>
										</span>
									</c:if>
									<c:if test="${not jobPosting.enabled}">
										<span class="disableEnable">
											<a class="btn btn-success enablePosting" role="button" href="javascript:void(0)">
												<i class="fa fa-level-up" aria-hidden="true"></i>&nbsp;&nbsp;Enable
											</a>
										</span>
									</c:if>
								</security:authorize>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="job-posting-display">
	<table class="table table-striped table-hover">
		<tr><th>ID</th><td data-field="id"></td></tr>
		<tr><th>Title</th><td data-field="jobTitle"></td></tr>
		<tr><th>Employer</th><td data-field="employerName"></td></tr>
		<tr><th>Description</th><td data-field="jobDescription"></td></tr>
		<tr><th>Location</th><td data-field="location"></td></tr>
		<tr><th>Salary</th><td data-field="salary"></td></tr>
		<tr><th>Website</th><td data-field="website"></td></tr>
	</table>
</div>
