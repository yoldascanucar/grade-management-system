package grade_management.servlets.student_related;

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
import java.util.*;

@WebServlet("/grade-card")
public class GradeCardServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        @SuppressWarnings("unchecked")
        Map<String, String> studentIdInfo = (Map<String, String>) session.getAttribute("studentIdInfo");


        int id = Integer.parseInt(studentIdInfo.get("id"));
        Student student = managementDAO.getStudentById(id);


        if (student != null) {
            List<CourseStudent> studentRecords = managementDAO.getCourseStudentInformation(id);

            Map<String, List<CourseStudent>> courseRecordsMap =  new LinkedHashMap<>();

            for (CourseStudent record : studentRecords) {
                String courseName = managementDAO.getCourseNameById(record.getCourse().getId());
                courseRecordsMap.computeIfAbsent(courseName, k -> new ArrayList<>())
                        .add(record);
            }

            request.setAttribute("courseRecordsMap", courseRecordsMap);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("grade-card.jsp");
            requestDispatcher.forward(request,response);
        } else {
            response.sendRedirect(request.getContextPath() + "/student-login");
        }
    }
}
