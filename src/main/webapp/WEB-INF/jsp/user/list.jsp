<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div class="animated fadeIn row">
	<div class="col-md-8 col-md-offset-2">
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
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>User Roles</th>
						<th>Operations</th>
					</tr>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.userRoles}</td>
							<td><security:authorize access="hasRole('ROLE_ADMIN')">

									<a data-userId="${user.id}" class="btn btn-info view" role="button">
										<i class="fa fa-street-view"
										aria-hidden="true"></i>&nbsp;&nbsp; View</a>
									<a data-userId="${user.id}" class="btn btn-success edit" role="button"
										href="edit?id=${user.id}"><i
										class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;Edit</a>
									<c:if test="${user.enabled}">
										<a data-userId="${user.id}" class="btn btn-danger disable" role="button"
											href="disable?id=${user.id}"><i
											class="fa fa-level-down" aria-hidden="true"></i>&nbsp;&nbsp;Disable</a>
									</c:if>
									<c:if test="${not user.enabled}">
										<a data-userId="${user.id}" class="btn btn-warning enable" role="button"
											href="enable?id=${user.id}"><i
											class="fa fa-level-up" aria-hidden="true"></i>&nbsp;&nbsp;Enable</a>
									</c:if>

								</security:authorize></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<p>
				<a id="add" class="btn btn-primary" role="button"><i
					class="fa fa-user-plus" aria-hidden="true"></i>&nbsp;&nbsp; Add New
					User</a>
			</p>
		</security:authorize>
	</div>
</div>

<div id="user-form">
    <form id="addUserForm" class="form-horizontal">
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
                    <input type="text" class="form-control" id="password" name="password" />
                </div>
            </div>
            <div class="form-group">
                <label for="password2" class="col-lg-2 control-label">Confirm Password</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="password2"
                        name="password2" />
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-lg-2 control-label">Email</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="email"
                        name="email" />
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">FirstName</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="firstName" name="firstName" />
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">LastName</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="lastName" name="lastName" />
                </div>
            </div>
        </fieldset>
    </form>
</div>

<div id="user-display">
    <form id="addUserForm" class="form-horizontal">
        <fieldset>
            <div class="form-group">
                <label for="userId" class="col-lg-2 control-label">ID</label>
                <div class="col-lg-10">
                    <span id="userId"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-lg-2 control-label">Username</label>
                <div class="col-lg-10">
                    <span id="username"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                    <span id="password"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-lg-2 control-label">Email</label>
                <div class="col-lg-10">
                    <span id="email"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">FirstName</label>
                <div class="col-lg-10">
                    <span id="firstName"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-lg-2 control-label">LastName</label>
                <div class="col-lg-10">
                    <span id="lastName"></span>
                </div>
            </div>
        </fieldset>
    </form>
</div>
