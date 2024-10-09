<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="grade_management.management_dao.ManagementDAO" %>

<html lang="en">
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

       .login-container-create {
          height: 391px;
       }

      .input-container .first-name-icon,
      .input-container .last-name-icon,
      .input-container .id-icon,
      .input-container .mail-icon {
           position: absolute;
           transform: translateY(-60%);
           padding-left: 5px;
           width: 20px;
           height: 20px;
           color: #757575;
        }

       .input-create {
           margin-top: -4px;
       }

       .createButton {
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
           margin-top: 12px;
       }

       .createButton:hover {
          background-color: #4265ad;
          transition: background-color 0.5s ease;
       }

       .createTitle {
         margin-top: 2px;
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

        window.onload = function() {

            <%
                String duplicateIdError = (String) request.getAttribute("duplicateIdError");
                String duplicateMailAddressError = (String) request.getAttribute("duplicateMailAddressError");

                if (duplicateIdError != null || duplicateMailAddressError != null) {
            %>
                document.getElementById('identityNumber').value = '';
            <%
                }
            %>
        }
    </script>
</head>
<body>

<%

    String emptyFirstNameFieldError = (String) request.getAttribute("emptyFirstNameFieldError");
    String emptyLastNameFieldError = (String) request.getAttribute("emptyLastNameFieldError");
    String emptyIdentityNumberFieldError = (String) request.getAttribute("emptyIdentityNumberFieldError");
    String emptyMailAddressFieldError = (String) request.getAttribute("emptyMailAddressFieldError");
    String sizeError = (String) request.getAttribute("sizeError");
    String typeMismatchError = (String) request.getAttribute("typeMismatchError");
    String invalidMailError = (String) request.getAttribute("invalidMailError");

    String firstName = (String) request.getAttribute("firstName");
    String lastName = (String) request.getAttribute("lastName");
    String identityNumber =(String) request.getAttribute("identityNumber");
    String mailAddress = (String) request.getAttribute("mailAddress");

    boolean hasErrorId = duplicateIdError != null || emptyIdentityNumberFieldError != null
                   || sizeError != null || typeMismatchError != null;

    boolean hasErrorMail = emptyMailAddressFieldError != null || invalidMailError != null
                    || duplicateMailAddressError != null;




if(firstName == null) firstName = "";
    if(lastName == null) lastName = "";
    if(identityNumber == null) identityNumber = "";
    if(mailAddress == null) mailAddress = "";
%>



<div class="login-container login-container-create">
    <h2 class="createTitle">Create Account Page</h2>
    <form action="create-account" method="post">
        <div  class="input-container">
            <label for="firstName"></label>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" id="person" class="icon first-name-icon" width="20" height="20">>
                <g>
                    <path d="M12 11a4 4 0 1 0-4-4 4 4 0 0 0 4 4zm0-6a2 2 0 1 1-2 2 2 2 0 0 1 2-2zm0 8a7 7 0 0 0-7 7 1 1 0 0 0 2 0 5 5 0 0 1 10 0 1 1 0 0 0 2 0 7 7 0 0 0-7-7z"></path>
                </g>
            </svg>
            <input class="input-create" type="text" id="firstName"
            placeholder="<%= emptyFirstNameFieldError != null ? emptyFirstNameFieldError :(typeMismatchError != null ? typeMismatchError : " First Name") %>"
            name="firstName"
            value="<%= firstName != null ? firstName : "" %>"
            class="<%= emptyFirstNameFieldError != null || typeMismatchError != null ? "error-placeholder" : "" %>">

        </div>
        <div  class="input-container">
            <label for="lastName"></label>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" id="person" class="icon last-name-icon" width="20" height="20">>
                <g>
                    <path d="M12 11a4 4 0 1 0-4-4 4 4 0 0 0 4 4zm0-6a2 2 0 1 1-2 2 2 2 0 0 1 2-2zm0 8a7 7 0 0 0-7 7 1 1 0 0 0 2 0 5 5 0 0 1 10 0 1 1 0 0 0 2 0 7 7 0 0 0-7-7z"></path>
                </g>
            </svg>
            <input class="input-create" type="text" id="lastName"
            placeholder="<%= emptyLastNameFieldError != null ? emptyLastNameFieldError :(typeMismatchError != null ? typeMismatchError : " Last Name") %>"
            name="lastName"
            value="<%= lastName != null ? lastName : "" %>"
            class="<%= emptyFirstNameFieldError != null || typeMismatchError != null ? "error-placeholder" : "" %>">

        </div>
        <div class="input-container">
            <label for="identityNumber"></label>
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" class="icon id-icon"  viewBox="0 0 24 24" id="id" >
                <path fill="none" d="M0 0h24v24H0z"></path>
                <path fill="#757575" d="M20 5h-5.185C14.401 3.838 13.302 3 12 3s-2.401.838-2.815 2H4c-1.103 0-2 .896-2 2v12c0 1.103.897 2 2 2h16c1.103 0 2-.897 2-2V7c0-1.104-.897-2-2-2zM8 9a2 2 0 1 1-.001 4.001A2 2 0 0 1 8 9zm-3 8c0-1.841 1.159-3 3-3s3 1.159 3 3H5zm7-10c-.551 0-1-.449-1-1 0-.551.449-1 1-1s1 .449 1 1c0 .551-.449 1-1 1zm7 10h-6v-2h6v2zm0-4h-6v-2h6v2z"></path>
            </svg>
            <input class="input-create" type="password" id="identityNumber"
            placeholder="<%= duplicateIdError != null ? duplicateIdError : emptyIdentityNumberFieldError != null ? emptyIdentityNumberFieldError : (sizeError != null ? sizeError :(typeMismatchError != null ? typeMismatchError : " Identity Number")) %>"
            name="identityNumber"
            value="<%= duplicateIdError != null ? "" : identityNumber %>"
            class="<%= hasErrorId ? "error-placeholder" : "" %>">
            <img id="eyeIcon" src="https://img.icons8.com/material-outlined/24/000000/invisible.png" class="eye-icon" onclick="togglePasswordVisibility()">

        </div>
        <div class="input-container">
            <label for="mailAddress"></label>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" id="mail" class="icon mail-icon" width="20" height="20">
                <path fill="#231f20" d="M27,26H5a3,3,0,0,1-3-3V9A3,3,0,0,1,5,6H27a3,3,0,0,1,3,3V23A3,3,0,0,1,27,26ZM5,8A1,1,0,0,0,4,9V23a1,1,0,0,0,1,1H27a1,1,0,0,0,1-1V9a1,1,0,0,0-1-1Z"></path>
                <path fill="#231f20" d="M16,17a1,1,0,0,1-.55-.17l-12-8A1,1,0,0,1,4.55,7.17l12,8A1,1,0,0,1,16,17Z"></path><path fill="#231f20" d="M16,17a1,1,0,0,1-.56-1.83l12-8a1,1,0,0,1,1.11,1.66l-12,8A1,1,0,0,1,16,17Z"></path>
            </svg>
            <input class="input-create" type="text" id="mailAddress"
            placeholder="<%= duplicateMailAddressError != null ? duplicateMailAddressError : emptyMailAddressFieldError != null ? emptyMailAddressFieldError : (invalidMailError != null) ? invalidMailError : "Email Address" %>"
            name="mailAddress"
            value="<%= duplicateMailAddressError != null ? "" : mailAddress %>"
            class="<%= hasErrorMail ? "error-placeholder" : "" %>">

        </div>
        <button type="submit" class="createButton">Create Account</button>
    </form>

    <div>
        <a href="<%=request.getContextPath()%>/student-login" class="redirectButton">
            Switch to Student Login Page
        </a>
    </div>
</div>
</body>
</html>
