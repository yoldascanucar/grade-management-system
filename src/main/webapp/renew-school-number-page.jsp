<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="grade_management.management_dao.ManagementDAO" %>

<html lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Login</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginandrenewpage-styles.css?v=<%= new java.util.Date().getTime() %>">

    <style>

        body {
            background-image: url('<%=request.getContextPath()%>/images/uni-photo.jpg') !important;
            background-size: cover !important;
            background-position: center !important;
            background-repeat: no-repeat !important;
        }

         .input-container .first-name-icon, .id-icon, .mail-icon {
            position: absolute;
            transform: translateY(-50%);
            padding-left: 5px;
            width: 20px;
            height: 20px;
            color: #757575;
          }

         .mail-icon{
           margin-top: 17px;
         }

        .renewButton {
            width: 100%;
            background-color: #658cdb;
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
            margin-top: 25px;
        }

        .renewButton:hover {
           background-color: #4265ad;
           transition: background-color 0.5s ease;
        }

    </style>
    <script>
        function togglePasswordVisibility() {
            var passwordField = document.getElementById('identityNumber');
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

String emptyFirstNameFieldError = (String) request.getAttribute("emptyFirstNameFieldError");
String emptyIdentityNumberFieldError = (String) request.getAttribute("emptyIdentityNumberFieldError");
String emptyMailAddressFieldError = (String) request.getAttribute("emptyMailAddressFieldError");
String sizeError = (String) request.getAttribute("sizeError");
String typeMismatchError = (String) request.getAttribute("typeMismatchError");
String invalidMailError = (String) request.getAttribute("invalidMailError");
String firstName = (String) request.getAttribute("firstName");
String identityNumber =(String) request.getAttribute("identityNumber");
String mailAddress = (String) request.getAttribute("mailAddress");

String url = (String) request.getAttribute("url");

if (url == null) {
    url = "forgot-student-number";
}

if(firstName == null) firstName = "";
if(identityNumber == null) identityNumber = "";
if(mailAddress == null) mailAddress = "";
%>




<c:choose>
    <c:when test="${url == 'forgot-teacher-number'}">
        <c:set var="actionUrl" value="forgot-teacher-number"/>
    </c:when>
    <c:when test="${url == 'forgot-student-number'}">
        <c:set var="actionUrl" value="forgot-student-number"/>
    </c:when>
    <c:otherwise>
        <c:set var="actionUrl" value="default"/>
    </c:otherwise>
</c:choose>

<div class="login-container">
    <h2>Renew Number</h2>
    <form action="${actionUrl}" method="post">
        <div class="input-container">
            <label for="firstName"></label>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" id="person" class="icon first-name-icon" width="20" height="20" style="fill: currentColor;">>
                <g>
                    <path d="M12 11a4 4 0 1 0-4-4 4 4 0 0 0 4 4zm0-6a2 2 0 1 1-2 2 2 2 0 0 1 2-2zm0 8a7 7 0 0 0-7 7 1 1 0 0 0 2 0 5 5 0 0 1 10 0 1 1 0 0 0 2 0 7 7 0 0 0-7-7z"></path>
                </g>
            </svg>
            <input type="text" id="firstName"
            placeholder="<%= emptyFirstNameFieldError != null ? emptyFirstNameFieldError :(typeMismatchError != null ? typeMismatchError : " First Name ") %>"
            name="firstName"
            value="<%= firstName %>"
            class="<%= emptyFirstNameFieldError != null || typeMismatchError != null ? "error-placeholder" : "" %>">
        </div>
        <div class="input-container">
            <label for="identityNumber"></label>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" class="icon id-icon"  viewBox="0 0 24 24" id="id" style="fill: currentColor;" >
                <path fill="none" d="M0 0h24v24H0z"></path>
                <path fill="#757575" d="M20 5h-5.185C14.401 3.838 13.302 3 12 3s-2.401.838-2.815 2H4c-1.103 0-2 .896-2 2v12c0 1.103.897 2 2 2h16c1.103 0 2-.897 2-2V7c0-1.104-.897-2-2-2zM8 9a2 2 0 1 1-.001 4.001A2 2 0 0 1 8 9zm-3 8c0-1.841 1.159-3 3-3s3 1.159 3 3H5zm7-10c-.551 0-1-.449-1-1 0-.551.449-1 1-1s1 .449 1 1c0 .551-.449 1-1 1zm7 10h-6v-2h6v2zm0-4h-6v-2h6v2z"></path>
            </svg>
            <input type="password" id="identityNumber"
            placeholder="<%= emptyIdentityNumberFieldError != null ? emptyIdentityNumberFieldError : (sizeError != null ? sizeError :(typeMismatchError != null ? typeMismatchError : " Identity Number")) %>"
            name="identityNumber"
            value="<%= identityNumber %>"
            class="<%= emptyIdentityNumberFieldError != null || sizeError != null || typeMismatchError != null ? "error-placeholder" : "" %>">
            <img  src="https://img.icons8.com/material-outlined/24/000000/invisible.png" class="eye-icon"  onclick="togglePasswordVisibility()">

        </div>
        <div>
            <label for="mailAddress"></label>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" class="mail-icon" viewBox="0 0 32 32"   style="fill: currentColor;">
                <path fill="#231f20" d="M27,26H5a3,3,0,0,1-3-3V9A3,3,0,0,1,5,6H27a3,3,0,0,1,3,3V23A3,3,0,0,1,27,26ZM5,8A1,1,0,0,0,4,9V23a1,1,0,0,0,1,1H27a1,1,0,0,0,1-1V9a1,1,0,0,0-1-1Z"></path>
                <path fill="#231f20" d="M16,17a1,1,0,0,1-.55-.17l-12-8A1,1,0,0,1,4.55,7.17l12,8A1,1,0,0,1,16,17Z"></path><path fill="#231f20" d="M16,17a1,1,0,0,1-.56-1.83l12-8a1,1,0,0,1,1.11,1.66l-12,8A1,1,0,0,1,16,17Z"></path>
            </svg>
            <input type="text" id="mailAddress"
            placeholder="<%= emptyMailAddressFieldError != null ? emptyMailAddressFieldError : (invalidMailError != null) ? invalidMailError : " Email Address" %>"
            name="mailAddress"
            value="<%= mailAddress %>"
            class="<%= (emptyMailAddressFieldError != null || invalidMailError != null) ? "error-placeholder" : "" %>">
        </div>

        <input type="hidden" name="submitted" value="true">
        <button type="submit" class="renewButton">Renew Number</button>
    </form>

    <div>
        <a href="<%=request.getContextPath()%>/student-login" class="redirectButton">
            Switch to Student Login Page
        </a>
    </div>
</div>
</body>
</html>
