<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/student-styles.css?v=<%= new java.util.Date().getTime() %>">
</head>

<style>
         .nav {
             display: flex;
             justify-content: center;
             align-items: center;
             gap: 15px;
             margin-top: 90px;
             margin-left: 70px;
          }

         .nav-item {
             display: flex;
             flex-direction: column;
             align-items: center;
             margin: 0;
        }

       .grade-card, .enrollment, .id-card {
             background-size: cover;
             background-position: center;
             padding: 60px;
             border-radius: 10px;
             width: 150px;
             height: 45px;
             transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .grade-card:hover, .enrollment:hover, .id-card:hover {
            transform: scale(1.1);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
       }


       .grade-card {
             background-image: url('<%=request.getContextPath()%>/images/grade-image.jpg');
       }

       .enrollment {
             background-image: url('<%=request.getContextPath()%>/images/enrollment-image.jpg');
             margin-right: 20px;
             margin-left: 20px;
       }

       .id-card {
             background-image: url('<%=request.getContextPath()%>/images/id-card-image.jpg');
       }

</style>


<body>
<div class="sidebar">
    <div class="home-btn">
        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="24" height="24" viewBox="0 0 24 24">
            <path d="M 15 2 A 1 1 0 0 0 14.300781 2.2851562 L 3.3925781 11.207031 A 1 1 0 0 0 3.3554688 11.236328 L 3.3183594 11.267578 L 3.3183594 11.269531 A 1 1 0 0 0 3 12 A 1 1 0 0 0 4 13 L 5 13 L 5 24 C 5 25.105 5.895 26 7 26 L 23 26 C 24.105 26 25 25.105 25 24 L 25 13 L 26 13 A 1 1 0 0 0 27 12 A 1 1 0 0 0 26.681641 11.267578 L 26.666016 11.255859 A 1 1 0 0 0 26.597656 11.199219 L 25 9.8925781 L 25 6 C 25 5.448 24.552 5 24 5 L 23 5 C 22.448 5 22 5.448 22 6 L 22 7.4394531 L 15.677734 2.2675781 A 1 1 0 0 0 15 2 z M 18 15 L 22 15 L 22 23 L 18 23 L 18 15 z"></path>
        </svg>
        <a  href="<%=request.getContextPath()%>/student-dashboard">Home Page</a>
    </div>
    <div class="logout-btn">
        <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg"  width="24" height="24" viewBox="0 0 24 24"><title>logout</title>
            <path d="M21.71,11.3,19.46,9.05A1,1,0,0,0,18,10.46l.54.54H10.16a1,1,0,0,0,0,2h8.44l-.54.54a1,1,0,1,0,1.41,1.41l2.23-2.23A1,1,0,0,0,21.71,11.3Z"></path>
            <path d="M8.16,12a2,2,0,0,1,2-2h6.26V6a4,4,0,0,0-4-4H6A4,4,0,0,0,2,6V18a4,4,0,0,0,4,4h6.42a4,4,0,0,0,4-4V14H10.16A2,2,0,0,1,8.16,12Z"></path></svg>
        <a href="<%=request.getContextPath()%>/student-logout">Log Out</a>
    </div>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-auto">
            <ul class="nav justify-content-center mt-5" style="margin-left: 180px;" >
                <li class="nav-item">
                    <span class="button-label">Grade Card</span>
                    <a  href="<%=request.getContextPath()%>/grade-card" class="btn grade-card"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">Course Enrollment</span>
                    <a  href="<%=request.getContextPath()%>/course-enrollment" class="btn enrollment"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">Digital Id Card</span>
                    <a  href="<%=request.getContextPath()%>/my-digital-id-card" class="btn id-card"></a>
                </li>
            </ul>
        </div>

    </div>
</div>

</body>
</html>
