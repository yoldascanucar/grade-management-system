package grade_management.servlets.shared;

import grade_management.entity.course.Course;
import grade_management.entity.student.Student;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;



@WebServlet("/addForm")
public class ShowAddFormServlet extends HttpServlet {


    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        try {
            managementDAO = new ManagementDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize ManagementDAO", e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        MultiValuedMap<Integer, Integer> idPairs = new ArrayListValuedHashMap<>();
        Map<Integer, String> studentNames = new HashMap<>();  // Map to store student ID to name

        int currentTeacherId = managementDAO.getCurrentTeacherId(request);
        Course teacherCourse = managementDAO.getCourseByTeacherId(currentTeacherId);


        MultiValuedMap<Integer, Integer> courseStudentIdPairs = managementDAO.getCourseStudentIdPairs();

        courseStudentIdPairs.entries().forEach(entry -> {
            int courseId = entry.getKey();
            int studentId = entry.getValue();
            idPairs.put(courseId, studentId);
        });

        List<Map.Entry<Integer, Integer>> storedIdPairs = new ArrayList<>();

        if (idPairs.containsKey(currentTeacherId)) {
            Collection<Integer> studentIds = idPairs.get(currentTeacherId);

            for (int studentId : studentIds) {
                Student student = managementDAO.getStudentById(studentId);  // Assuming you have a method to fetch Student by ID

                boolean isEnrolledInTeacherCourse = student.getCourseStudents().stream()
                        .anyMatch(cs -> cs.getCourse().equals(teacherCourse));

                if (isEnrolledInTeacherCourse) {
                    studentNames.put(studentId, student.getFirstName() + " " + student.getLastName());
                    storedIdPairs.add(new AbstractMap.SimpleEntry<>(currentTeacherId, studentId));
                }
            }
        }

        List<Map.Entry<Integer, Integer>> records = new ArrayList<>(storedIdPairs);
        request.setAttribute("records", records);
        request.setAttribute("studentNames", studentNames);
        request.setAttribute("currentTeacherId", currentTeacherId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("add-form.jsp");
        requestDispatcher.forward(request, response);
    }
}

