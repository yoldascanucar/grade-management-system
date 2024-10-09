package grade_management.servlets.shared;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/update")
public class StudentUpdateServlet extends HttpServlet {

    private ManagementDAO managementDAO;
    Class<?> examType;


    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String updateButton = request.getParameter("updateButton");

        int currentTeacherId = managementDAO.getCurrentTeacherId(request);

        switch (updateButton) {
            case "mathMidterm":
                examType = MathMidterm.class;
                break;
            case "mathFinal":
                examType = MathFinal.class;
                break;
            case "physicsMidterm":
                examType = PhysicsMidterm.class;
                break;
            case "physicsFinal":
                examType = PhysicsFinal.class;
                break;
            case "englishMidterm":
                examType = EnglishMidterm.class;
                break;
            case "englishFinal":
                examType = EnglishFinal.class;
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid update button");
                return;
        }

        Object student = managementDAO.selectStudentExam(examType, Integer.parseInt(idStr));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update-form.jsp");
        request.setAttribute("student", student);
        request.setAttribute("currentTeacherId", currentTeacherId);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String updateButton = request.getParameter("updateButton");
        int studentId = Integer.parseInt(idStr);

        Student student = managementDAO.getStudentById(studentId);
        int studentNumber = student.getStudentNumber();
        String courseType = request.getParameter("lecture");
        int courseId = managementDAO.getCourseIdByName(courseType);
        String courseName = managementDAO.getCourseNameById(courseId);

        switch (updateButton) {
            case "mathMidterm":
                String mathMidtermGradeStr = request.getParameter("mathMidtermGrade");
                int mathMidtermGrade = Integer.parseInt(mathMidtermGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "midterm", mathMidtermGrade, true);
                response.sendRedirect(request.getContextPath() + "/math-teacher-home/math-midterm-grades");
                break;
            case "mathFinal":
                String mathFinalGradeStr = request.getParameter("mathFinalGrade");
                int mathFinalGrade = Integer.parseInt(mathFinalGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "final", mathFinalGrade, false);
                response.sendRedirect(request.getContextPath() + "/math-teacher-home/math-final-grades");
                break;
            case "physicsMidterm":
                String physicsMidtermGradeStr = request.getParameter("physicsMidtermGrade");
                int physicsMidtermGrade = Integer.parseInt(physicsMidtermGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "midterm", physicsMidtermGrade, true);
                response.sendRedirect(request.getContextPath() + "/physics-teacher-home/physics-midterm-grades");
                break;
            case "physicsFinal":
                String physicsFinalGradeStr = request.getParameter("physicsFinalGrade");
                int physicsFinalGrade = Integer.parseInt(physicsFinalGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "final", physicsFinalGrade, false);
                response.sendRedirect(request.getContextPath() + "/physics-teacher-home/physics-final-grades");
                break;
            case "englishMidterm":
                String englishMidtermGradeStr = request.getParameter("englishMidtermGrade");
                int englishMidtermGrade = Integer.parseInt(englishMidtermGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "midterm", englishMidtermGrade, true);
                response.sendRedirect(request.getContextPath() + "/english-teacher-home/english-midterm-grades");
                break;
            case "englishFinal":
                String englishFinalGradeStr = request.getParameter("englishFinalGrade");
                int englishFinalGrade = Integer.parseInt(englishFinalGradeStr);
                updateGrades(studentId, courseId, studentNumber, courseName, "final", englishFinalGrade, false);
                response.sendRedirect(request.getContextPath() + "/english-teacher-home/english-final-grades");
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid update button: " + updateButton);
                break;
        }
    }

    private void updateGrades(int studentId, int courseId, int studentNumber, String courseName, String examType, int grade, boolean isMidterm) {
        String tableName = courseName.toLowerCase() + "_" + examType;
        String gradeColumnName = courseName.toLowerCase() + "_" + examType + "_grade";
        managementDAO.updateMidtermOrFinalGrade(tableName, gradeColumnName, studentId, grade);

        if (isMidterm) {
            managementDAO.updateCourseStudentMidtermGrade(studentId, courseId, grade);
        } else {
            managementDAO.updateCourseStudentFinalGrade(studentId, courseId, grade);
        }

        double studentAverage = managementDAO.calculateWeightedAverage(studentNumber, courseName);
        managementDAO.updateLetterGradeTableAverageGrade(studentAverage, studentId, courseName);
        managementDAO.updateCourseStudentAverageGrade(studentAverage, studentId, courseId);

        List<?> records = managementDAO.listLetterTableRecords(courseName, getLetterGradeClass(courseName));
        double mean = managementDAO.calculateMean(records, courseName);
        double sd = managementDAO.calculateStandardDeviation(records, mean, courseName);

        for (Object record : records) {
            int recordId = managementDAO.getRecordId(record);
            String letterGrade = managementDAO.assignLetterGrade(record, mean, sd, courseName);
            managementDAO.updateLetterGrade(recordId, letterGrade, courseName);
            managementDAO.updateCourseStudentLetterGrade(recordId, letterGrade, courseId, courseName);
        }
    }

    private Class<?> getLetterGradeClass(String courseName) {
        switch (courseName) {
            case "Math":
                return MathLetterGrade.class;
            case "Physics":
                return PhysicsLetterGrade.class;
            case "English":
                return EnglishLetterGrade.class;
            default:
                throw new IllegalArgumentException("Unsupported course: " + courseName);
        }
    }
}

