<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Welcome!</h1>
		<h3>Join our growing community</h3>
		<form:form action="/register" method="post" modelAttribute="newUser">
			<h3 class="mt-3">Register</h3>
			<div class="my-3">
		        <form:label path="username" class="form-label">Username</form:label><br/>
		        <form:errors path="username" style="color:red"/>
		        <form:input type="text" class="form-control" path="username"/>
		    </div>
			<div class="my-3">
		        <form:label path="email" class="form-label">Email address</form:label><br/>
		        <form:errors path="email" style="color:red"/>
		        <form:input type="text" class="form-control" path="email"/>
		    </div>
			<div class="my-3">
		        <form:label path="password" class="form-label">Password</form:label><br/>
		        <form:errors path="password" style="color:red"/>
		        <form:input type="password" class="form-control" path="password"/>
		    </div>
			<div class="my-3">
		        <form:label path="confirm" class="form-label">Confirm password</form:label><br/>
		        <form:errors path="confirm" style="color:red"/>
		        <form:input type="password" class="form-control" path="confirm"/>
		    </div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
		<form:form action="/login" method="post" modelAttribute="newLogin">
			<h3 class="mt-3">Login</h3>
			<div class="my-3">
		        <form:label path="email" class="form-label">Email address</form:label><br/>
		        <form:errors path="email" style="color:red"/>
		        <form:input type="text" class="form-control" path="email"/>
		    </div>
			<div class="my-3">
		        <form:label path="password" class="form-label">Password</form:label><br/>
		        <form:errors path="password" style="color:red"/>
		        <form:input type="password" class="form-control" path="password"/>
		    </div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>