    package grade_management.servlets.assignation;

    import grade_management.entity.finals.EnglishFinal;
    import grade_management.entity.finals.MathFinal;
    import grade_management.entity.finals.PhysicsFinal;
    import grade_management.entity.letter_grades.EnglishLetterGrade;
    import grade_management.entity.letter_grades.MathLetterGrade;
    import grade_management.entity.letter_grades.PhysicsLetterGrade;
    import grade_management.entity.midterms.EnglishMidterm;
    import grade_management.entity.midterms.MathMidterm;
    import grade_management.entity.midterms.PhysicsMidterm;
    import grade_management.entity.student.Student;
    import grade_management.management_dao.ManagementDAO;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;


    @WebServlet("/add")
    public class LetterGradesAssignationServlet extends HttpServlet {

        private ManagementDAO managementDAO;

        @Override
        public void init() throws ServletException {
            managementDAO = new ManagementDAO();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                calculateStudentAverage(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error processing SQL query", e);
            }
        }

        private void calculateStudentAverage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

            int selectedStudentId = Integer.parseInt(request.getParameter("studentId"));
            Student student = managementDAO.getStudentById(selectedStudentId);

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            int studentNumber = student.getStudentNumber();
            int studentId = student.getId();
            int grade = Integer.parseInt(request.getParameter("grade"));
            String courseType = request.getParameter("lecture");
            int courseId = managementDAO.getCourseIdByName(courseType);


            String examType = request.getParameter("examType");


            if ("Midterm".equals(examType)) {
                handleMidtermExam(firstName, lastName, grade, courseType, studentNumber, examType, studentId, courseId, response, request);
            } else if ("Final".equals(examType)) {
                handleFinalExam(firstName, lastName, grade, courseType, studentNumber, examType, studentId, courseId, response, request);
            }
        }

        private void handleMidtermExam(String firstName, String lastName, int grade, String courseType, int studentNumber, String examType, int studentId, int courseId, HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
            switch (courseType) {
                case "Math":
                    MathMidterm mathMidterm = new MathMidterm(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentMidtermInfo(mathMidterm);
                    managementDAO.updateCourseStudentMidtermGrade(studentId, courseId, grade);
                    if (managementDAO.doesFinalExist(MathFinal.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/math-teacher-home/math-midterm-grades");
                    break;
                case "English":
                    EnglishMidterm englishMidterm = new EnglishMidterm(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentMidtermInfo(englishMidterm);
                    managementDAO.updateCourseStudentMidtermGrade(studentId, courseId, grade);
                    if (managementDAO.doesFinalExist(EnglishFinal.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/english-teacher-home/english-midterm-grades");
                    break;
                case "Physics":
                    PhysicsMidterm physicsMidterm = new PhysicsMidterm(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentMidtermInfo(physicsMidterm);
                    managementDAO.updateCourseStudentMidtermGrade(studentId, courseId, grade);
                    if (managementDAO.doesFinalExist(PhysicsFinal.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/physics-teacher-home/physics-midterm-grades");
                    break;
            }
        }

        private void handleFinalExam(String firstName, String lastName, int grade, String courseType, int studentNumber, String examType, int studentId, int courseId, HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException, ServletException {
            switch (courseType) {
                case "Math":
                    MathFinal mathFinal = new MathFinal(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentFinalInfo(mathFinal);
                    managementDAO.updateCourseStudentFinalGrade(studentId, courseId, grade);
                    if (managementDAO.doesMidtermExist(MathMidterm.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/math-teacher-home/math-final-grades");
                    break;
                case "English":
                    EnglishFinal englishFinal = new EnglishFinal(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentFinalInfo(englishFinal);
                    managementDAO.updateCourseStudentFinalGrade(studentId, courseId, grade);
                    if (managementDAO.doesMidtermExist(EnglishMidterm.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/english-teacher-home/english-final-grades");
                    break;
                case "Physics":
                    PhysicsFinal physicsFinal = new PhysicsFinal(firstName, lastName, grade, courseType, studentNumber, examType, studentId);
                    managementDAO.addStudentFinalInfo(physicsFinal);
                    managementDAO.updateCourseStudentFinalGrade(studentId, courseId, grade);
                    if (managementDAO.doesMidtermExist(PhysicsMidterm.class, studentNumber)) {
                        storeLetterGrade(studentNumber, firstName, lastName, courseType, studentId, courseId);
                    }
                    response.sendRedirect(request.getContextPath() + "/physics-teacher-home/physics-final-grades");
                    break;
            }
        }

        // sadece öğrenci eklendiğinde kullanılan method.
        public void storeLetterGrade(int studentNumber, String firstName, String lastName, String courseType, int studentId, int courseId) throws SQLException {
            String courseName = managementDAO.getCourseNameById(courseId);
            double average = managementDAO.calculateWeightedAverage(studentNumber, courseName);

            Object firstStudentRecord;
            Object newStudentRecord;
            Class<?> letterGradeClass;

            switch (courseType) {
                case "Math":
                    firstStudentRecord = new MathLetterGrade(studentNumber, firstName, lastName, average, "AA", courseType, studentId);
                    newStudentRecord = new MathLetterGrade(studentNumber, firstName, lastName, average, null, courseType, studentId);
                    letterGradeClass = MathLetterGrade.class;
                    break;
                case "English":
                    firstStudentRecord = new EnglishLetterGrade(studentNumber, firstName, lastName, average, "AA", courseType, studentId);
                    newStudentRecord = new EnglishLetterGrade(studentNumber, firstName, lastName, average, null, courseType, studentId);
                    letterGradeClass = EnglishLetterGrade.class;
                    break;
                case "Physics":
                    firstStudentRecord = new PhysicsLetterGrade(studentNumber, firstName, lastName, average, "AA", courseType, studentId);
                    newStudentRecord = new PhysicsLetterGrade(studentNumber, firstName, lastName, average, null, courseType, studentId);
                    letterGradeClass = PhysicsLetterGrade.class;
                    break;
                default:
                    throw new IllegalArgumentException("Lecture not found!! " + courseType);
            }

            List<?> existingRecords = managementDAO.listLetterTableRecords(courseName, letterGradeClass);

            if (existingRecords.isEmpty()) {
                managementDAO.addLetterGradeTableInfo(firstStudentRecord, courseName);
                managementDAO.updateCourseStudentAverageAndLetterGrade(firstStudentRecord, courseId, studentId, courseName);
            } else {
                managementDAO.addLetterGradeTableInfo(newStudentRecord, courseName);
                managementDAO.addCourseStudentAverage(average, courseId, studentId);

                List<?> allRecords = managementDAO.listLetterTableRecords(courseName, letterGradeClass);
                double mean = managementDAO.calculateMean(allRecords, courseType);
                double sd = managementDAO.calculateStandardDeviation(allRecords, mean, courseType);

                for (Object record : allRecords) {
                    String letterGrade = managementDAO.assignLetterGrade(record, mean, sd, courseName);
                    int recordId = managementDAO.getRecordId(record);
                    managementDAO.updateLetterGrade(recordId, letterGrade, courseName);
                    managementDAO.updateCourseStudentLetterGrade(recordId, letterGrade, courseId, courseName);
                }
            }
        }
    }

