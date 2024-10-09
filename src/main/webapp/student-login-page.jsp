<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="grade_management.management_dao.ManagementDAO" %>
<%@ page import="grade_management.servlets.login_servlets.StudentLoginPageServlet" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Login</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginandrenewpage-styles.css?v=<%= new java.util.Date().getTime() %>">

    <style>

        body {
            background-image: url('<%=request.getContextPath()%>/images/uni-photo.jpg') !important;
            background-size: cover !important;
            background-position: center !important;
            background-repeat: no-repeat !important;
        }

    .newContainer {
        justify-content: space-between;
        align-items: center;
        margin-top: 30px;
    }

    .createButton, .forgotButton {
        flex-wrap: nowrap;
        box-sizing: border-box;
        padding: 10px 5px;
        font-size: 14px;
        font-family: Arial, sans-serif;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
        text-align: center;

    }
    .createButton {
       width: 40%;
       background-color: #658cdb;

    }
    .createButton:hover {
       background-color: #4265ad;
       transition: background-color 0.5s ease;
    }

    .forgotButton {
       background-color: #c9161c;
       width: 55%;
       float:right;
       margin-top: -26.5px;
    }


    </style>
    <script>
        function togglePasswordVisibility() {
            var passwordField = document.getElementById('studentNumber');
            var eyeIcon = document.getElementById('eyeIcon');
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                eyeIcon.src = 'https://img.icons8.com/material-outlined/24/000000/visible.png';
            } else {
                passwordField.type = 'password';
                eyeIcon.src = 'https://img.icons8.com/material-outlined/24/000000/invisible.png';
            }
        }
    </script>
</head>
<body>

<%

String emptyStudentNumberError = (String) request.getAttribute("emptyStudentNumberError");
String emptyMailAddressError = (String) request.getAttribute("emptyMailAddressError");
String sizeError = (String) request.getAttribute("sizeError");
String typeMismatchError = (String) request.getAttribute("typeMismatchError");
String invalidMailError = (String) request.getAttribute("invalidMailError");
String mailAddress = (String) request.getAttribute("mailAddress");
String studentNumber = (String) request.getAttribute("studentNumber");


if (mailAddress == null) mailAddress = "";
if (studentNumber == null) studentNumber = "";

%>

<div class="login-container">
    <h2>Student Login</h2>
    <form action="student-login" method="post">
        <div class="input-container">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" class="icon number-icon" id="mail" width="20" height="20" style="fill: currentColor;">
                <path fill="#231f20" d="M27,26H5a3,3,0,0,1-3-3V9A3,3,0,0,1,5,6H27a3,3,0,0,1,3,3V23A3,3,0,0,1,27,26ZM5,8A1,1,0,0,0,4,9V23a1,1,0,0,0,1,1H27a1,1,0,0,0,1-1V9a1,1,0,0,0-1-1Z"></path>
                <path fill="#231f20" d="M16,17a1,1,0,0,1-.55-.17l-12-8A1,1,0,0,1,4.55,7.17l12,8A1,1,0,0,1,16,17Z"></path><path fill="#231f20" d="M16,17a1,1,0,0,1-.56-1.83l12-8a1,1,0,0,1,1.11,1.66l-12,8A1,1,0,0,1,16,17Z"></path>
            </svg>
            <input type="text" id="mailAddress"
            placeholder="<%= emptyMailAddressError != null ? emptyMailAddressError : (invalidMailError != null) ? invalidMailError : "Email Address" %>"
            name="mailAddress"
            value="<%= mailAddress %>"
            class="<%= (emptyMailAddressError != null || invalidMailError != null) ? "error-placeholder" : "" %>">
        </div>
        <div class="input-container">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon number-icon" style="width: 27px; height: 27px;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1"><path d="M723.671 660.064c-1.986 0-3.586 1.624-3.586 3.63v62.466c0 18.883-15.164 34.248-33.808 34.248h-66.588c-1.979 0-3.58 1.623-3.58 3.631s1.601 3.632 3.58 3.632h66.588c22.597 0 40.973-18.622 40.973-41.511v-62.466c0-2.006-1.6-3.63-3.579-3.63zM585.328 760.408h-16.342c-1.979 0-3.579 1.623-3.579 3.631s1.6 3.632 3.579 3.632h16.342c1.98 0 3.586-1.624 3.586-3.632 0-2.007-1.607-3.631-3.586-3.631zM724.7 388.688v-19.753c0-119.569-96.014-216.85-214.024-216.85-118.015 0-214.023 97.282-214.023 216.85v19.754c-39.577 7.329-69.67 42.534-69.67 84.725v279.292c0 47.483 38.12 86.108 84.983 86.108h397.428c37.879 0 70.043-25.238 80.98-59.998h3.99v-305.403c0-42.192-30.090-77.398-69.664-84.726zM318.151 368.934c0-107.564 86.366-195.066 192.525-195.066 106.16 0 192.519 87.502 192.519 195.066v18.351h-385.044v-18.351zM772.866 752.707c0 35.469-28.474 64.324-63.473 64.324h-397.428c-35.006 0-63.478-28.854-63.478-64.324v-279.293c0-35.477 28.472-64.344 63.478-64.344h397.428c35 0 63.473 28.868 63.473 64.344v279.293z"/>
                <path xmlns="http://www.w3.org/2000/svg" d="M723.671 660.064c-1.986 0-3.586 1.624-3.586 3.63v62.466c0 18.883-15.164 34.248-33.808 34.248h-66.588c-1.979 0-3.58 1.623-3.58 3.631s1.601 3.632 3.58 3.632h66.588c22.597 0 40.973-18.622 40.973-41.511v-62.466c0-2.006-1.6-3.63-3.579-3.63zM585.328 760.408h-16.342c-1.979 0-3.579 1.623-3.579 3.631s1.6 3.632 3.579 3.632h16.342c1.98 0 3.586-1.624 3.586-3.632 0-2.007-1.607-3.631-3.586-3.631zM724.7 388.688v-19.753c0-119.569-96.014-216.85-214.024-216.85-118.015 0-214.023 97.282-214.023 216.85v19.754c-39.577 7.329-69.67 42.534-69.67 84.725v279.292c0 47.483 38.12 86.108 84.983 86.108h397.428c37.879 0 70.043-25.238 80.98-59.998h3.99v-305.403c0-42.192-30.090-77.398-69.664-84.726zM318.151 368.934c0-107.564 86.366-195.066 192.525-195.066 106.16 0 192.519 87.502 192.519 195.066v18.351h-385.044v-18.351zM772.866 752.707c0 35.469-28.474 64.324-63.473 64.324h-397.428c-35.006 0-63.478-28.854-63.478-64.324v-279.293c0-35.477 28.472-64.344 63.478-64.344h397.428c35 0 63.473 28.868 63.473 64.344v279.293z"/>
            </svg>
            <input type="password" id="studentNumber"
                   placeholder="<%= emptyStudentNumberError != null ? emptyStudentNumberError : (sizeError != null ? sizeError :(typeMismatchError != null ? typeMismatchError : " Student Number")) %>"
            name="studentNumber"
            value="<%= studentNumber %>"
            class="<%= emptyStudentNumberError != null || sizeError != null || typeMismatchError != null ? "error-placeholder" : "" %>">
            <img id="eyeIcon" src="https://img.icons8.com/material-outlined/24/000000/invisible.png" class="eye-icon" onclick="togglePasswordVisibility()">
        </div>
        <input type="hidden" name="submitted" value="true">
        <button type="submit" class="loginButton">Student Login</button>
    </form>
    <div>
        <a href="<%=request.getContextPath()%>/teacher-login" class="redirectButton">
            Switch to Teacher Login Page
        </a>
    </div>

    <div class="newContainer">
        <div>
            <a href="<%=request.getContextPath()%>/create-account" class="createButton">
                Create Account
            </a>
        </div>
        <div>
            <a href="<%=request.getContextPath()%>/forgot-student-number" class="forgotButton">
                Forgot Student Number
            </a>
        </div>
    </div>
</div>
</body>
</html>

