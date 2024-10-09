package grade_management.servlets.student_related;


import grade_management.entity.course.Course;
import grade_management.entity.course_student.CourseStudent;
import grade_management.entity.student.Student;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/course-enrollment")
public class CourseEnrollmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ManagementDAO managementDAO = null;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Course> courseInfo = managementDAO.getAllCourses();
        // Set the courseInfo attribute so it can be used in the JSP
        request.setAttribute("courseInfo", courseInfo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("course-enrollment.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        Map<String, String> studentIdInfo = (Map<String, String>) session.getAttribute("studentIdInfo");

        if (studentIdInfo != null) {

            int studentNumber = Integer.parseInt(studentIdInfo.get("Student Number"));
            Student student = managementDAO.getStudentByStudentNumber(studentNumber);
            int studentId = student.getId();

            String selectedCourseIdsString = request.getParameter("selectedCourses");

            if (selectedCourseIdsString != null && !selectedCourseIdsString.trim().isEmpty()) {
                String[] selectedCourseIds = selectedCourseIdsString.split(",");
                for (String courseId : selectedCourseIds) {
                    try {
                        int courseIdInt = Integer.parseInt(courseId.trim());

                        Course course = managementDAO.getCourseById(courseIdInt);

                        CourseStudent courseStudent = new CourseStudent(course, student);

                        managementDAO.addIdsToCourseStudent(courseStudent);
                        int teacherId = course.getTeacher().getId();
                        managementDAO.addTeacherStudentAssociation(teacherId, studentId);

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                response.sendRedirect(request.getContextPath() + "/grade-card");
            }
        }
    }
}

