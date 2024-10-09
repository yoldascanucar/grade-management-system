package grade_management.servlets.login_servlets;


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
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet("/student-login")
public class StudentLoginPageServlet extends HttpServlet {

    ManagementDAO managementDAO;

    @Override
    public void init() {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student-login-page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNumber = request.getParameter("studentNumber");
        String mailAddress = request.getParameter("mailAddress");

        boolean hasStudentNumberError = false;
        boolean hasMailAddressError = false;

        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            request.setAttribute("emptyStudentNumberError", "Student Number field cannot be empty");
            studentNumber = "";
            hasStudentNumberError = true;
        } else if (studentNumber.length() != 6 || !managementDAO.isAllDigits(studentNumber)) {
            if (studentNumber.length() != 6 && managementDAO.isAllDigits(studentNumber)) {
                request.setAttribute("sizeError", "Student number must be 6 digits long");
            } else if (!managementDAO.isAllDigits(studentNumber)){
                request.setAttribute("typeMismatchError", "Student Number can only contain digits");
            }
            studentNumber = "";
            hasStudentNumberError = true;
        }

        if (mailAddress == null || mailAddress.trim().isEmpty()) {
            mailAddress = "";
            request.setAttribute("emptyMailAddressError", "Mail Address field cannot be empty");
            hasMailAddressError = true;
        } else if (!mailAddress.matches("^[^@\\s]+@[^@\\s]+(\\.[^@\\s]{2,})+$")) {
            mailAddress = "";
            request.setAttribute("invalidMailError", "Please enter a valid mail address");
            hasMailAddressError = true;
        }

        request.setAttribute("studentNumber", studentNumber);
        request.setAttribute("mailAddress", mailAddress);

        if (hasStudentNumberError || hasMailAddressError) {
            forwardToLogin(request, response);
        } else {

            int studentNo = Integer.parseInt(studentNumber);
            boolean isAuthenticated = managementDAO.authenticateStudentForLogin(studentNo, mailAddress);

            if (isAuthenticated) {
                Student student = managementDAO.getStudentByStudentNumber(studentNo);

                Map<String, String> studentIdInfo = getStringStringMap(student);

                HttpSession session = request.getSession();
                session.setAttribute("studentIdInfo", studentIdInfo);

                response.sendRedirect(request.getContextPath() + "/student-dashboard");
            } else {

                request.setAttribute("studentNumber", studentNumber);
                request.setAttribute("mailAddress", mailAddress);
                forwardToLogin(request, response);
            }
        }
    }

    private static Map<String, String> getStringStringMap(Student student) {
        // Sadece Digital Id kartta kullanılacak bilgiler
        Map<String, String> studentIdInfo = new LinkedHashMap<>();
        studentIdInfo.put("id", String.valueOf(student.getId()));
        studentIdInfo.put("Identity Number", student.getIdentityNumber());
        studentIdInfo.put("Student Number", String.valueOf(student.getStudentNumber()));
        studentIdInfo.put("First Name", student.getFirstName());
        studentIdInfo.put("Last Name", student.getLastName());
        studentIdInfo.put("Email Address", student.getMailAddress());
        return studentIdInfo;
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student-login-page.jsp");
        requestDispatcher.forward(request, response);
    }

}
