<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Category Page</title>
</head>
<body>
	<h1><c:out value="${category.name}"/></h1>
	
	<form:form method="POST" action="/categories/${category.id}" modelAttribute="product">
	    <form:label path="name">Add Product: 
	    <form:errors path="name"/>
	    <form:select path="name" items="${products}" itemValue="id" itemLabel="name"/></form:label>
	    
	    <input type="submit" value="Add"/>
	</form:form>
	
	<c:if test="${!empty categoryProducts}">
		<h3>Products:</h3>
		<ul>
			<c:forEach items="${categoryProducts}" var="product">
				<li><c:out value="${product.name}"/></li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>