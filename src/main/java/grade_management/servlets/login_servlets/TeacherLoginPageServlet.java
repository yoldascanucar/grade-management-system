package grade_management.servlets.login_servlets;
import grade_management.entity.teacher.Teacher;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/teacher-login")
public class TeacherLoginPageServlet extends HttpServlet {

    ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("teacher-login-page.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherNumber = request.getParameter("teacherNumber");
        String mailAddress = request.getParameter("mailAddress");

        boolean hasTeacherNumberError = false;
        boolean hasMailAddressError = false;


        if (teacherNumber == null || teacherNumber.trim().isEmpty()) {
              request.setAttribute("emptyTeacherNumberError", "Teacher Number field can not be empty");
              teacherNumber = "";
              hasTeacherNumberError = true;
        } else if (teacherNumber.length() != 10 || !managementDAO.isAllDigits(teacherNumber)) {
            if (teacherNumber.length() != 10 && managementDAO.isAllDigits(teacherNumber)) {
                request.setAttribute("sizeError", "Teacher Number must be 10 digits long");
            } else if (!managementDAO.isAllDigits(teacherNumber)) {
                request.setAttribute("typeMismatchError", "Teacher number can only contain digit");
            }
            teacherNumber = "";
            hasTeacherNumberError = true;
        }

        if (mailAddress == null || mailAddress.trim().isEmpty() ) {
           request.setAttribute("emptyMailAddressError", "Email Address field can not be empty");
           mailAddress = "";
           hasMailAddressError = true;
        } else if (!mailAddress.matches("^[^@\\s]+@[^@\\s]+(\\.[^@\\s]{2,})+$")) {
            request.setAttribute("invalidMailError", "Please enter a valid mail address");
            mailAddress = "";
            hasMailAddressError = true;
        }

        request.setAttribute("teacherNumber", teacherNumber);
        request.setAttribute("mailAddress", mailAddress);


        if (hasTeacherNumberError || hasMailAddressError) {
            forwardToLogin(request, response);
        } else {

            boolean isAuthenticated = managementDAO.authenticateTeacherForLogin(teacherNumber, mailAddress);

            if (isAuthenticated) {
                Teacher teacher = managementDAO.getTeacherByCredentials(teacherNumber, mailAddress);

                if (teacher != null) {

                    HttpSession session = request.getSession();
                    session.setAttribute("teacher", teacher);

                    String role = teacher.getRole().trim();
                    String redirectURL;

                    switch (role) {
                        case "MathTeacher":
                            redirectURL = "/math-teacher-home";
                            break;
                        case "PhysicsTeacher":
                            redirectURL = "/physics-teacher-home";
                            break;
                        case "EnglishTeacher":
                            redirectURL = "/english-teacher-home";
                            break;
                        default:
                            redirectURL = "/access-denied";
                            break;
                    }

                    response.sendRedirect(request.getContextPath() + redirectURL);
                }
            }
            else {
                response.sendRedirect(request.getContextPath() + "/teacher-login");
            }
        }
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("teacher-login-page.jsp");
        requestDispatcher.forward(request, response);
    }
}
