<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="grade_management.entity.course_student.CourseStudent" %>

<html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <title>Grade Card</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gradecard-styles.css?v=<%= new java.util.Date().getTime() %>">
</head>
<body>
<h1>Grade Card</h1>

<div class="card-container">
    <c:forEach var="entry" items="${courseRecordsMap}">
        <c:set var="courseName" value="${entry.key}" />
        <div class="card">
            <h2><c:out value="${courseName}" /></h2>
            <table>
                <tbody>
                <c:forEach var="record" items="${entry.value}">
                    <tr>
                        <td><strong>Midterm:</strong> <c:out value="${record.midtermGrade}" /></td>
                    </tr>
                    <tr>
                        <td><strong>Final:</strong> <c:out value="${record.finalGrade}" /></td>
                    </tr>
                    <tr>
                        <td><strong>Average:</strong> <c:out value="${record.averageGrade}" /></td>
                    </tr>
                    <tr>
                        <td><strong>Letter Grade:</strong> <c:out value="${record.letterGrade}" /></td>
                    </tr>
                </c:forEach>
                <c:if test="${empty entry.value}">
                    <tr>
                        <td>No records found</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </c:forEach>
</div>

</body>
</html>
