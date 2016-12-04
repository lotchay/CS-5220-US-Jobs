<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div class="animated fadeIn row">
	<div class="col-md-10 col-md-offset-1">
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<p>
				<a id="add" class="btn btn-primary" role="button"><i
					class="fa fa-user-plus" aria-hidden="true"></i>&nbsp;&nbsp; Add New
					User</a>
			</p>
		</security:authorize>
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">
					<i class="fa fa-users" aria-hidden="true"></i>&nbsp;&nbsp;User
					Management
				</h3>
			</div>
			<div class="panel-body">
				<table id="users" class="table table-striped table-hover">
					<tr class="info">
						<th>ID</th>
						<th>Username</th>
						<th>password</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>User Roles</th>
						<th>Operations</th>
					</tr>
					<c:forEach items="${users}" var="user">
						<tr data-user-id="${user.id}">
							<td data-field="id">${user.id}</td>
							<td data-field="username">${user.username}</td>
							<td data-field="password">${user.password}</td>
							<td data-field="firstName">${user.firstName}</td>
							<td data-field="lastName">${user.lastName}</td>
							<td data-field="email">${user.email}</td>
							<td data-field="userRole">${user.userRoles}</td>
							<td><security:authorize access="hasRole('ROLE_ADMIN')">
									<a data-userId="${user.id}" class="btn btn-info view"
										role="button"> <i class="fa fa-street-view"
										aria-hidden="true"></i>&nbsp;&nbsp; View
									</a>
									<a data-userId="${user.id}" class="btn btn-success edit"
										role="button"> <i class="fa fa-pencil-square-o"
										aria-hidden="true"></i>&nbsp;&nbsp;Edit
									</a>
									<c:if test="${user.enabled}">
										<a data-userId="${user.id}" class="btn btn-danger disable"
											role="button"> <i class="fa fa-level-down"
											aria-hidden="true"></i>&nbsp;&nbsp;Disable
										</a>
									</c:if>
									<c:if test="${not user.enabled}">
										<a data-userId="${user.id}" class="btn btn-warning enable"
											role="button"> <i class="fa fa-level-up"
											aria-hidden="true"></i>&nbsp;&nbsp;Enable
										</a>
									</c:if>
								</security:authorize></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="user-form">
	<form id="addForm" class="form-horizontal">
		<input type="hidden" name="userId" />
		<fieldset>
			<div class="form-group">
				<label for="username" class="col-lg-2 control-label">Username</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="username"
						name="username" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="password"
						name="password" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-lg-2 control-label">FirstName</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="firstName"
						name="firstName" />
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-lg-2 control-label">LastName</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="lastName"
						name="lastName" />
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-lg-2 control-label">Email</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="email" name="email" />
				</div>
			</div>
		</fieldset>
	</form>
</div>

<div id="user-display">
	<table class="table table-striped table-hover">
		<tr>
			<th>ID</th>
			<td data-field="userId"></td>
		</tr>
		<tr>
			<th>Username</th>
			<td data-field="username"></td>
		</tr>
		<tr>
			<th>Password</th>
			<td data-field="password"></td>
		</tr>
		<tr>
			<th>First Name</th>
			<td data-field="firstName"></td>
		</tr>
		<tr>
			<th>Last Name</th>
			<td data-field="lastName"></td>
		</tr>
		<tr>
			<th>Email</th>
			<td data-field="email"></td>
		</tr>
	</table>
</div>
