<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="auto-logout.jsp" %>

<html xmlns:c="http://java.sun.com/jsp/jstl/core" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <style>
        body {
            background-color: #fff5f3;
            padding: 20px;
            overflow-x: hidden;
        }
         .form-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 380px;
            height: 450px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            opacity: 0;
            transition: opacity 0.6s ease-out, transform 0.6s ease-out;
        }

        .form-container.visible {
            opacity: 1;
        }

        .form-container h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-group select,
        .form-group input {
            width: 100%;
        }
        .btn-primary {
            position: relative;
            top: 50%;
            left: 50%;
            transform: translateX(-50%);
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .form-group label {
          font-weight: 200;
          margin-bottom: 3px;
        }

        .form-group option {
          font-weight: 200;
         }

        h1{
         font-weight: 200;
         text-align:center;
        }

        .form-group  select {
            font-weight: 200;
        }

        option {
            font-weight: 200;
        }

    </style>
</head>
<body>


<div class="form-container">
    <h1>Add Form</h1>
    <form action="add" method="post">

        <div class="form-group">
            <label for="studentId">Students:</label>
            <select name="studentId" id="studentId" class="form-select">
                <c:if test="${not empty records}">
                    <c:forEach items="${records}" var="entry">
                        <c:choose>
                            <c:when test="${entry.key == currentTeacherId}">
                                <option value="${entry.value}">${studentNames[entry.value]}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </select>
        </div>

        <div class="form-group">
            <label for="lecture">Lecture:</label>
            <select name="lecture" id="lecture" class="form-select">
                <c:choose>
                    <c:when test="${currentTeacherId == 1}">
                        <option value="Math">Math</option>
                    </c:when>
                    <c:when test="${currentTeacherId == 2}">
                        <option value="Physics">Physics</option>
                    </c:when>
                    <c:when test="${currentTeacherId == 3}">
                        <option value="English">English</option>
                    </c:when>
                </c:choose>
            </select>
        </div>

        <div class="form-group">
            <label for="examType">Grade Type:</label>
            <select name="examType" id="examType" class="form-select">
                <option value="Midterm">Midterm</option>
                <option value="Final">Final</option>
            </select>
        </div>

        <div class="form-group">
            <label for="grade">Student Grade:</label>
            <input type="text" class="form-control" id="grade" placeholder="Enter a grade" style="font-weight: 300;" name="grade" />
        </div>

        <button type="submit" class="btn btn-primary">Add Student</button>
    </form>
</div>

<script>
    window.onload = function() {
        var formContainer = document.querySelector('.form-container');
        formContainer.classList.add('visible');
    };
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-09MjbWmE1S7F7Bzj5fJabGxq+Vmg5cOwe4x7a6e2G8Yo13Kr2B+QK5fkj4er7vU"
        crossorigin="anonymous"></script>
</body>
</html>

