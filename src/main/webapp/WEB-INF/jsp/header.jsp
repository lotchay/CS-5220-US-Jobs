<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/home.html' />">U.S.
				Jobs</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-2">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value='/home.html' />">Main</a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">Dropdown
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<form method="post" action="<c:url value='/home.html' />" class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input id="searchKey" type="text" class="form-control" name="searchBar"
						placeholder="Search">
				    <select class="form-control" id="searchType" name="searchType">
				    	<option>Job Postings</option>
				        <option>Salary</option>
				    </select>
				</div>
				<button type="submit" class="btn btn-default" id="search-btn">
					<i class="fa fa-search"></i>&nbsp;&nbsp;Search
				</button>
			</form>
			<security:authorize access="isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/user/profile.html' />">Welcome,
							<security:authentication property="principal.username" />
					</a></li>
					<li><a href="<c:url value='/logout' />">Logout</a></li>
				</ul>
			</security:authorize>
			<security:authorize access="!isAuthenticated()">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/login.html' />" >Login</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value='/register.html' />">Register</a></li>
				</ul>
			</security:authorize>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/user/list.html' />" >User Management</a></li>
			</ul>
		</div>
	</div>
</nav>