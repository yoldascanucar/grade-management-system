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
            overflow: hidden;

        }
        .container {
            margin-top: 100px;
        }
        .form-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 380px;
            height: 420px;
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

        .btn-custom {
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%);
            background-color: #007bff;
            color: #ffffff;
            border: none;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        h2 {
         text-align: center;
         font-weight: 200;
         margin-bottom: 50px;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="mb-5">Update Form</h2>
        <form action="update" method="post">
            <input type="hidden" name="id" value="${student.id}"/>
            <input type="hidden" name="updateButton" value="${param.updateButton}"/>

            <div class="mb-4">
                <input type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName" value="${student.firstName}" readonly  />
            </div>

            <div class="mb-4">
                <input type="text" class="form-control" id="lastName" placeholder="Last Name" name="lastName" value="${student.lastName}" readonly />
            </div>

            <div class="mb-4">
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

            <c:choose>
                <c:when test="${param.updateButton == 'mathMidterm'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="mathMidtermGrade" placeholder="Math Midterm Grade" name="mathMidtermGrade" value="${student.mathMidtermGrade}"/>
                    </div>
                </c:when>
                <c:when test="${param.updateButton == 'mathFinal'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="mathFinalGrade" placeholder="Math Final Grade" name="mathFinalGrade" value="${student.mathFinalGrade}"/>
                    </div>
                </c:when>
                <c:when test="${param.updateButton == 'physicsMidterm'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="physicsMidtermGrade" placeholder="Physics Midterm Grade" name="physicsMidtermGrade" value="${student.physicsMidtermGrade}"/>
                    </div>
                </c:when>
                <c:when test="${param.updateButton == 'physicsFinal'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="physicsFinalGrade" placeholder="Physics Final Grade" name="physicsFinalGrade" value="${student.physicsFinalGrade}"/>
                    </div>
                </c:when>
                <c:when test="${param.updateButton == 'englishMidterm'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="englishMidtermGrade" placeholder="English Midterm Grade" name="englishMidtermGrade" value="${student.englishMidtermGrade}"/>
                    </div>
                </c:when>
                <c:when test="${param.updateButton == 'englishFinal'}">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="englishFinalGrade" placeholder="English Final Grade" name="englishFinalGrade" value="${student.englishFinalGrade}"/>
                    </div>
                </c:when>
            </c:choose>

            <button type="submit" class="btn btn-custom w-30 mt-3">Update Student</button>
        </form>
    </div>
</div>

<script>
    window.onload = function() {
        var formContainer = document.querySelector('.form-container');
        formContainer.classList.add('visible');
    };
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YE7W7qfDk9jlKN5AqDqAAV7lsuRU3xqZ8sOR0A4D3SrzcrXWUnz7KjUE+W25CHBzM" crossorigin="anonymous"></script>
</body>
</html>

