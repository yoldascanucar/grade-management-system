<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/student-styles.css?v=<%= new java.util.Date().getTime() %>">

    <title>Course Enrollment</title>
    <style>
        .container {
            margin-top: 60px;
            margin-left: 80px;
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }

        .course-basket {
            background-color: #c9ffbf;
            margin-right: 70px;
        }

        .course-list {
            background-color: #d1fcff;
            margin-left: 200px;
        }

        .course-list, .course-basket {
            width: 30%;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
        }

        .course-list ul, .course-basket ul {
            list-style-type: none;
            padding: 0;
        }

        .course-list li, .course-basket li {
            padding: 8px;
            margin-bottom: 5px;
            border: 1px solid #ddd;
            cursor: pointer;
            position: relative;
            transition: all 0.5s ease;
        }

        .course-list li:hover, .course-basket li:hover {
            background-color: #f0f0f0;
        }

        .remove-btn {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px;
            cursor: pointer;
            border-radius: 5px;
        }

        .remove-btn:hover {
            background-color: #c82333;
        }

        .enroll-btn {
            position: relative;
            top: 50%;
            left: 60%;
            transform: translate(-60%, 150%);
            padding: 10px 20px;
            background-color: #28a745;
            color: #fff;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        h2, h3 {
         font-weight: 200;
        }

        h3 {
           text-align: center;
        }

        h2 {
           display: inline-block;
           position: relative;
           top: 5%;
           left: 60%;
           transform: translate(-60%, 10%);
        }

    </style>
    <script>
        function addToBasket(courseId, courseName, courseCredit) {
            const basket = document.getElementById("basket");

            if (!document.querySelector(`#basket [data-id="${courseId}"]`)) {
                const listItem = document.createElement("li");
                listItem.setAttribute("data-id", courseId);
                listItem.textContent = courseName + " (" + courseCredit + " credits)";

                const removeButton = document.createElement("button");
                removeButton.textContent = "Remove";
                removeButton.className = "remove-btn";
                removeButton.onclick = function() {
                    removeFromBasket(courseId, courseName, courseCredit);
                };

                listItem.appendChild(removeButton);

                basket.appendChild(listItem);

                const availableItem = document.getElementById(courseId);
                if (availableItem) {
                    document.getElementById("availableCourses").removeChild(availableItem);
                }

                updateLocalStorage();
            }
        }

        function removeFromBasket(courseId, courseName, courseCredit) {
            const basket = document.getElementById("basket");

            const listItem = Array.from(basket.getElementsByTagName("li"))
                .find(item => item.getAttribute("data-id") === courseId);

            if (listItem) {
                basket.removeChild(listItem);
            }

            const newAvailableItem = document.createElement("li");
            newAvailableItem.setAttribute("id", courseId);
            newAvailableItem.textContent = courseName + " (" + courseCredit + " credits)";
            newAvailableItem.onclick = function() {
                addToBasket(courseId, courseName, courseCredit);
            };
            document.getElementById("availableCourses").appendChild(newAvailableItem);

            updateLocalStorage();
        }

        function updateLocalStorage() {
            const selectedCourses = [];
            document.querySelectorAll("#basket li").forEach(function(item) {
                selectedCourses.push({
                    id: item.getAttribute("data-id"),
                    name: item.textContent.split(" (")[0],
                    credit: item.textContent.match(/\(([^)]+) credits\)/)[1]
                });
            });
            localStorage.setItem("selectedCourses", JSON.stringify(selectedCourses));
        }

        function loadBasketFromLocalStorage() {
            const savedCourses = JSON.parse(localStorage.getItem("selectedCourses"));
            if (savedCourses) {
                savedCourses.forEach(function(course) {
                    addToBasket(course.id, course.name, course.credit);
                });
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            loadBasketFromLocalStorage();

            document.querySelector("form").onsubmit = function() {
                const selectedCourses = [];
                document.querySelectorAll("#basket li").forEach(function(item) {
                    selectedCourses.push(item.getAttribute("data-id"));
                });
                document.getElementById("selectedCourses").value = selectedCourses.join(",");
            };
        });
    </script>
</head>
<body>
<h2>Course Enrollment</h2>
<div class="sidebar">
    <div class="home-btn">
        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="24" height="24" viewBox="0 0 24 24">
            <path d="M 15 2 A 1 1 0 0 0 14.300781 2.2851562 L 3.3925781 11.207031 A 1 1 0 0 0 3.3554688 11.236328 L 3.3183594 11.267578 L 3.3183594 11.269531 A 1 1 0 0 0 3 12 A 1 1 0 0 0 4 13 L 5 13 L 5 24 C 5 25.105 5.895 26 7 26 L 23 26 C 24.105 26 25 25.105 25 24 L 25 13 L 26 13 A 1 1 0 0 0 27 12 A 1 1 0 0 0 26.681641 11.267578 L 26.666016 11.255859 A 1 1 0 0 0 26.597656 11.199219 L 25 9.8925781 L 25 6 C 25 5.448 24.552 5 24 5 L 23 5 C 22.448 5 22 5.448 22 6 L 22 7.4394531 L 15.677734 2.2675781 A 1 1 0 0 0 15 2 z M 18 15 L 22 15 L 22 23 L 18 23 L 18 15 z"></path>
        </svg>
        <a  href="<%=request.getContextPath()%>/student-dashboard">Home Page</a>
    </div>
    <div class="grade-card-btn">
        <svg xmlns="http://www.w3.org/2000/svg" xml:space="preserve" width="24" height="24" version="1.1" style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd" viewBox="0 0 1707 1707" xmlns:xlink="http://www.w3.org/1999/xlink">
                   <defs><style type="text/css">.fil0 {fill:black}</style></defs><g id="Layer_x0020_1"><metadata id="CorelCorpID_0Corel-Layer"></metadata><path class="fil0" d="M1348 1707l-986 0c-103,0 -187,-90 -187,-200l0 -1307c0,-110 84,-200 187,-200l780 0 0 23 0 0 0 16 0 311c0,39 31,70 70,70l282 0 14 0 1 0 23 0 0 1087c0,52 -19,102 -53,140 -34,38 -81,60 -131,60zm-73 -210l-857 0c-13,0 -23,-11 -23,-24 0,-13 10,-23 23,-23l857 0c13,0 23,10 23,23 0,13 -10,24 -23,24zm0 -200l-857 0c-13,0 -23,-11 -23,-24 0,-13 10,-23 23,-23l857 0c13,0 23,10 23,23 0,13 -10,24 -23,24zm0 -200l-857 0c-13,0 -23,-11 -23,-24 0,-13 10,-23 23,-23l857 0c13,0 23,10 23,23 0,13 -10,24 -23,24zm-627 -327c-174,0 -316,-142 -316,-317 0,-174 142,-316 316,-316 86,0 166,32 226,92 61,59 94,139 94,224 0,85 -33,165 -94,225 -60,59 -140,92 -226,92zm0 -587c-149,0 -270,121 -270,270 0,149 121,270 270,270 151,0 274,-121 274,-270 0,-149 -123,-270 -274,-270zm200 237l-40 0 0 37c0,13 -10,23 -23,23 -13,0 -23,-10 -23,-23l0 -37 -40 0c-13,0 -24,-10 -24,-23 0,-13 11,-24 24,-24l40 0 0 -40c0,-13 10,-23 23,-23 13,0 23,10 23,23l0 40 40 0c13,0 24,11 24,24 0,13 -11,23 -24,23zm-370 183c-3,0 -5,0 -8,-1 -12,-5 -18,-19 -13,-31l38 -97 0 -1c0,-6 3,-12 7,-16l55 -139c4,-12 18,-18 30,-13 7,3 13,9 14,16l53 139c2,3 4,7 4,11l39 101c4,12 -2,25 -14,30 -2,1 -5,1 -8,1 -9,0 -18,-5 -22,-15l-35 -91 -82 0 -36 92c-4,9 -12,14 -22,14zm123 -153l-23 -59 -23 59 46 0zm674 447l-857 0c-13,0 -23,-11 -23,-24 0,-13 10,-23 23,-23l857 0c13,0 23,10 23,23 0,13 -10,24 -23,24z">,</path>
            <path class="fil0" d="M1508 373l-296 0c-13,0 -24,-10 -24,-23l0 -327c0,-9 6,-18 15,-21 9,-4 20,-1 26,6l297 326c6,7 7,17 4,25 -4,9 -12,14 -22,14z"></path></g></svg>
        <a href="<%=request.getContextPath()%>/grade-card">Grade Card</a>
    </div>
    <div class="id-card-btn">
        <svg width="24" height="24" viewBox="0 0 64 64" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M19.96 30.75C22.3072 30.75 24.21 28.8472 24.21 26.5C24.21 24.1528 22.3072 22.25 19.96 22.25C17.6127 22.25 15.71 24.1528 15.71 26.5C15.71 28.8472 17.6127 30.75 19.96 30.75Z" fill="black"></path>
            <path d="M57 10.75H7C5.87364 10.7526 4.79417 11.2013 3.99771 11.9977C3.20126 12.7942 2.75264 13.8736 2.75 15V49C2.75264 50.1264 3.20126 51.2058 3.99771 52.0023C4.79417 52.7987 5.87364 53.2474 7 53.25H57C58.1264 53.2474 59.2058 52.7987 60.0023 52.0023C60.7987 51.2058 61.2474 50.1264 61.25 49V15C61.2474 13.8736 60.7987 12.7942 60.0023 11.9977C59.2058 11.2013 58.1264 10.7526 57 10.75ZM35.71 18.75H40.54C40.8715 18.75 41.1895 18.8817 41.4239 19.1161C41.6583 19.3505 41.79 19.6685 41.79 20C41.79 20.3315 41.6583 20.6495 41.4239 20.8839C41.1895 21.1183 40.8715 21.25 40.54 21.25H35.71C35.3785 21.25 35.0605 21.1183 34.8261 20.8839C34.5917 20.6495 34.46 20.3315 34.46 20C34.46 19.6685 34.5917 19.3505 34.8261 19.1161C35.0605 18.8817 35.3785 18.75 35.71 18.75V18.75ZM30.12 43.45C29.7885 43.45 29.4705 43.3183 29.2361 43.0839C29.0017 42.8495 28.87 42.5315 28.87 42.2C28.87 39.8369 27.9313 37.5706 26.2603 35.8997C24.5894 34.2287 22.3231 33.29 19.96 33.29C17.5969 33.29 15.3306 34.2287 13.6597 35.8997C11.9887 37.5706 11.05 39.8369 11.05 42.2C11.05 42.5315 10.9183 42.8495 10.6839 43.0839C10.4495 43.3183 10.1315 43.45 9.8 43.45C9.46848 43.45 9.15054 43.3183 8.91612 43.0839C8.6817 42.8495 8.55 42.5315 8.55 42.2C8.5513 39.9508 9.21616 37.752 10.4613 35.8789C11.7065 34.0058 13.4766 32.5417 15.55 31.67C14.4995 30.7741 13.749 29.5777 13.3996 28.242C13.0503 26.9063 13.119 25.4955 13.5963 24.2001C14.0737 22.9046 14.9369 21.7867 16.0694 20.997C17.2019 20.2074 18.5494 19.784 19.93 19.784C21.3106 19.784 22.6581 20.2074 23.7906 20.997C24.9231 21.7867 25.7863 22.9046 26.2636 24.2001C26.741 25.4955 26.8097 26.9063 26.4604 28.242C26.111 29.5777 25.3605 30.7741 24.31 31.67C26.3834 32.5417 28.1535 34.0058 29.3987 35.8789C30.6438 37.752 31.3087 39.9508 31.31 42.2C31.3003 42.5144 31.1724 42.8136 30.9518 43.0379C30.7312 43.2622 30.4342 43.3951 30.12 43.41V43.45ZM53.18 41.93H35.71C35.3785 41.93 35.0605 41.7983 34.8261 41.5639C34.5917 41.3295 34.46 41.0115 34.46 40.68C34.46 40.3485 34.5917 40.0305 34.8261 39.7961C35.0605 39.5617 35.3785 39.43 35.71 39.43H53.18C53.5115 39.43 53.8295 39.5617 54.0639 39.7961C54.2983 40.0305 54.43 40.3485 54.43 40.68C54.43 41.0115 54.2983 41.3295 54.0639 41.5639C53.8295 41.7983 53.5115 41.93 53.18 41.93V41.93ZM53.18 35.04H35.71C35.3785 35.04 35.0605 34.9083 34.8261 34.6739C34.5917 34.4395 34.46 34.1215 34.46 33.79C34.46 33.4585 34.5917 33.1405 34.8261 32.9061C35.0605 32.6717 35.3785 32.54 35.71 32.54H53.18C53.5115 32.54 53.8295 32.6717 54.0639 32.9061C54.2983 33.1405 54.43 33.4585 54.43 33.79C54.43 34.1215 54.2983 34.4395 54.0639 34.6739C53.8295 34.9083 53.5115 35.04 53.18 35.04V35.04ZM53.18 28.15H35.71C35.3785 28.15 35.0605 28.0183 34.8261 27.7839C34.5917 27.5495 34.46 27.2315 34.46 26.9C34.46 26.5685 34.5917 26.2505 34.8261 26.0161C35.0605 25.7817 35.3785 25.65 35.71 25.65H53.18C53.5115 25.65 53.8295 25.7817 54.0639 26.0161C54.2983 26.2505 54.43 26.5685 54.43 26.9C54.43 27.2315 54.2983 27.5495 54.0639 27.7839C53.8295 28.0183 53.5115 28.15 53.18 28.15V28.15Z" fill="black"></path>
            <path d="M53.1799 46.29H48.5699C48.2384 46.29 47.9205 46.4217 47.6861 46.6562C47.4516 46.8906 47.3199 47.2085 47.3199 47.54C47.3199 47.8716 47.4516 48.1895 47.6861 48.4239C47.9205 48.6583 48.2384 48.79 48.5699 48.79H53.1799C53.5115 48.79 53.8294 48.6583 54.0638 48.4239C54.2983 48.1895 54.4299 47.8716 54.4299 47.54C54.4299 47.2085 54.2983 46.8906 54.0638 46.6562C53.8294 46.4217 53.5115 46.29 53.1799 46.29V46.29Z" fill="black"></path>
        </svg>
        <a href="<%=request.getContextPath()%>/my-digital-id-card">Digital Id</a>
    </div>
    <div class="logout-btn">
        <svg id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg"  width="24" height="24" viewBox="0 0 24 24"><title>logout</title>
            <path d="M21.71,11.3,19.46,9.05A1,1,0,0,0,18,10.46l.54.54H10.16a1,1,0,0,0,0,2h8.44l-.54.54a1,1,0,1,0,1.41,1.41l2.23-2.23A1,1,0,0,0,21.71,11.3Z"></path>
            <path d="M8.16,12a2,2,0,0,1,2-2h6.26V6a4,4,0,0,0-4-4H6A4,4,0,0,0,2,6V18a4,4,0,0,0,4,4h6.42a4,4,0,0,0,4-4V14H10.16A2,2,0,0,1,8.16,12Z"></path></svg>
        <a href="<%=request.getContextPath()%>/student-logout">Log Out</a>
    </div>
</div>

<div class="container">
    <div class="course-list">
        <h3>Available Courses</h3>
        <ul id="availableCourses" style="background-color: white;">
            <c:forEach var="course" items="${courseInfo}">
                <li id="${course.id}" onclick="addToBasket('${course.id}', '${course.courseName}', '${course.credit}')">
                    ${course.courseName} (${course.credit} credits)
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="course-basket">
        <h3>Your Course Basket</h3>
        <ul id="basket" style="background-color: white;">
            <c:forEach var="course" items="${selectedCourses}">
                <li data-id="${course.id}">
                    ${course.courseName} (${course.credit} credits)
                    <button class="remove-btn" onclick="removeFromBasket('${course.id}', '${course.courseName}', '${course.credit}')">Remove</button>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- Enrollment Button -->
<form action="course-enrollment" method="post">
    <input type="hidden" name="selectedCourses" id="selectedCourses" value="">
    <button type="submit" class="enroll-btn">Confirm Enrollment</button>
</form>
</body>
</html>

