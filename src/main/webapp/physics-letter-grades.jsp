<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="auto-logout.jsp" %>


<html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gradepage-styles.css?v=<%= new java.util.Date().getTime() %>">

</head>
<body>


<div>
    <h2 class="text-center mb-4">PHYSICS AVERAGE GRADES</h2>
</div>

<div class="container">
    <div class="top-right">
        <button class="btn btn-success" onclick="location.href='<%=request.getContextPath()%>/physics-teacher-home'">
            <i class="bi bi-house-door-fill"></i> Home
        </button>
    </div>
</div>

<div class="container">
    <table class="table table-striped" style="margin-top: 6rem;">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Student Number</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Lecture</th>
            <th scope="col">Physics Average Grade</th>
            <th scope="col">Physics Letter Grade</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${physicsAverageGradesList}" var="student" >
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.studentNumber}"/></td>
                <td><c:out value="${student.firstName}"/></td>
                <td><c:out value="${student.lastName}"/></td>
                <td><c:out value="${student.lecture}"/></td>
                <td><c:out value="${student.averagePhysicsGrade}"/></td>
                <td><c:out value="${student.physicsLetterGrade}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
