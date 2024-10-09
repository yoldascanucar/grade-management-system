<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/teacher-homepage-styles.css?v=<%= new java.util.Date().getTime() %>">
    <style>

        body {
           background-color: #fefaf9;
        }

        .physics-midterm-button, .physics-final-button, .physics-letter-button {
           background-size: cover;
           background-position: center;
           color: white;
           border: none;
           display: block;
           width: 250px;
           height: 190px;
           transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .physics-midterm-button:hover, .physics-final-button:hover, .physics-letter-button:hover {
              transform: scale(1.1);
              box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
         }

       .physics-midterm-button {
           background-image: url('<%=request.getContextPath()%>/images/physics-midterm-icon.png');
       }
       .physics-final-button {
           background-image: url('<%=request.getContextPath()%>/images/physics-final-icon.jpg');
       }
       .physics-letter-button {
           background-image: url('<%=request.getContextPath()%>/images/letter-grade-icon.jpg');
       }

    </style>
</head>
<body>

<div>
    <a href="<%=request.getContextPath()%>/addForm" style="text-decoration: none;"
       class="add-student-btn">Add Form</a>
</div>

<div>
    <a href="<%=request.getContextPath()%>/logout" style="text-decoration: none;"
       class="logout-btn">Log Out</a>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-auto">
            <h3 class="text-center">PHYSICS GRADING INTERFACE</h3>
            <ul class="nav justify-content-center mt-5">
                <li class="nav-item">
                    <span class="button-label">Physics Midterm Grades</span>
                    <a href="<%=request.getContextPath()%>/physics-teacher-home/physics-midterm-grades" class="btn physics-midterm-button"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">Physics Final Grades</span>
                    <a href="<%=request.getContextPath()%>/physics-teacher-home/physics-final-grades" class="btn physics-final-button"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">Physics Letter Grades</span>
                    <a href="<%=request.getContextPath()%>/physics-teacher-home/physics-letter-grades" class="btn physics-letter-button"></a>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
