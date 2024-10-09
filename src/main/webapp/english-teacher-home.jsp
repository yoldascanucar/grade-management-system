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

      .english-midterm-button, .english-final-button, .english-letter-button {
         background-size: cover;
         background-position: center;
         color: white;
         border: none;
         display: block;
         width: 250px;
         height: 190px;
         transition: transform 0.3s ease, box-shadow 0.3s ease;
      }

      .english-midterm-button:hover, .english-final-button:hover, .english-letter-button:hover {
            transform: scale(1.1);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
       }

     .english-midterm-button {
         background-image: url('<%=request.getContextPath()%>/images/english-midterm-icon.jpg');
     }
     .english-final-button {
         background-image: url('<%=request.getContextPath()%>/images/english-final-icon.jpg');
     }
     .english-letter-button {
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
            <h3 class="text-center">ENGLISH GRADING INTERFACE</h3>
            <ul class="nav justify-content-center mt-5">
                <li class="nav-item">
                    <span class="button-label">English Midterm Grades</span>
                    <a href="<%=request.getContextPath()%>/english-teacher-home/english-midterm-grades" class="btn english-midterm-button"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">English Final Grades</span>
                    <a href="<%=request.getContextPath()%>/english-teacher-home/english-final-grades" class="btn english-final-button"></a>
                </li>
                <li class="nav-item">
                    <span class="button-label">English Letter Grades</span>
                    <a href="<%=request.getContextPath()%>/english-teacher-home/english-letter-grades" class="btn english-letter-button"></a>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
