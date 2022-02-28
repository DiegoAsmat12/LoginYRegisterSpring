<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> <!--Unicamente para update-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Login & Registration</title>
		<!--BOOTSTRAP-->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
		<div class="container">
			<h1 class="text-success">Welcome!</h1>
			<p>Join our growing community</p>
			<div class="row">
				<div class="col-8">
					<h2 class="text-primary">Register</h2>
					<form:form action="/register" method="post" modelAttribute="newUser">
						<form:errors path="userName" class="alert alert-danger d-block"></form:errors>
						<form:errors path="email" class="alert alert-danger d-block"></form:errors>
						<form:errors path="nacimiento" class="alert alert-danger d-block"></form:errors>
						<form:errors path="password" class="alert alert-danger d-block"></form:errors>
						<form:errors path="confirm" class="alert alert-danger d-block"></form:errors>
						<form:errors path="terminos" class="alert alert-danger d-block"></form:errors>
						<div class="form-group mb-3">
							<form:label path="userName">User Name:</form:label>
							<form:input path="userName" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="email">Email:</form:label>
							<form:input path="email" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="nacimiento">BirthDate:</form:label>
							<form:input path="nacimiento" type="date" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="password">Password:</form:label>
							<form:input path="password" class="form-control" type="password"/>
						</div>
						<div class="form-group mb-3">
							<form:label path="confirm">Confirm Password:</form:label>
							<form:input path="confirm" class="form-control" type="password"/>
						</div>
						<div class="form-check">
							<form:checkbox path="terminos" value="terminos" class="form-check-input"/><form:label path="terminos" class="form-check-label">Terminos y condiciones</form:label>
						</div>
						<button type="submit" class="btn btn-primary">Register</button>
					</form:form>

				</div>
				<div class="col-4">
					<h2 class="text-primary">Log in</h2>
					<form:form action="/login" method="post" modelAttribute="newLogin">
						<form:errors path="email" class="alert alert-danger d-block"></form:errors>
						<form:errors path="password" class="alert alert-danger d-block"></form:errors>
						<div class="form-group mb-3">
							<form:label path="email">Email:</form:label>
							<form:input path="email" class="form-control" />
						</div>
						<div class="form-group mb-3">
							<form:label path="password">Password:</form:label>
							<form:input path="password" class="form-control" type="password"/>
						</div>
						
						<button type="submit" class="btn btn-primary">Log in</button>
					</form:form>
				</div>
			</div>
		</div>
		
		<!--BOOTSTRAP-->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>