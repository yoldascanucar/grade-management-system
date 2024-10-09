package grade_management.servlets.forget_servlet;

import grade_management.util.EmailUtil;
import grade_management.entity.student.Student;
import grade_management.management_dao.ManagementDAO;
import grade_management.util.ValidationUtils;

import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/forgot-student-number")
public class ForgotStudentNumberServlet extends HttpServlet {

    private ManagementDAO managementDAO = null;
    private ValidationUtils validationUtils = null;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
        validationUtils = new ValidationUtils(managementDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("url", "forgot-student-number" );
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("renew-school-number-page.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = validationUtils.validateUserInput(request);

        if (!errors.isEmpty()) {
            for (Map.Entry<String, String> error : errors.entrySet()) {
                request.setAttribute(error.getKey(), error.getValue());
            }
            forwardToLogin(request, response);
        } else {
            String mailAddress = request.getParameter("mailAddress");
            String identityNumber = request.getParameter("identityNumber");
            String firstName = request.getParameter("firstName");

            boolean isStudentAuthenticated = validationUtils.studentNumberRenewalAuthentication(mailAddress, identityNumber, firstName);



            if (isStudentAuthenticated) {
                int newStudentNumber = Integer.parseInt(String.valueOf(managementDAO.studentNumberGeneration()));
                Student student = managementDAO.getStudentByIdentityNumber(identityNumber);
                managementDAO.updateStudent(newStudentNumber, student);


                // email
                EmailUtil emailUtil = new EmailUtil();

                String emailSubject = "Your New Student Number";
                String emailContent = "Your New Student Number is: " + newStudentNumber;

                try {
                    emailUtil.emailSender(mailAddress, emailSubject, emailContent);
                } catch (AddressException e) {
                    throw new RuntimeException(e);
                }

                response.sendRedirect(request.getContextPath() + "/student-login");
            }else {
                response.sendRedirect(request.getContextPath() + "/forgot-student-number");
            }
        }
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("renew-school-number-page.jsp");
        requestDispatcher.forward(request, response);
    }
}