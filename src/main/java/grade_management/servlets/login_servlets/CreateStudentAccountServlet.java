    package grade_management.servlets.login_servlets;
    import grade_management.util.EmailUtil;
    import grade_management.entity.student.Student;
    import grade_management.management_dao.ManagementDAO;

    import javax.mail.internet.AddressException;
    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.List;

    @WebServlet("/create-account")
    public class CreateStudentAccountServlet extends HttpServlet {

        ManagementDAO managementDAO;

        @Override
        public void init() throws ServletException {
            managementDAO = new ManagementDAO();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-account-page.jsp");
            requestDispatcher.forward(request, response);
        }


        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            List<String> identityNumbersList = managementDAO.getAllIdentityNumbers();
            List<String> mailAddressesList = managementDAO.getAllMailAddresses();

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String identityNumber = request.getParameter("identityNumber");
            String mailAddress = request.getParameter("mailAddress");


            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("identityNumber", identityNumber);
            request.setAttribute("mailAddress", mailAddress);

            boolean hasFirstNameError = false;
            boolean hasLastNameError = false;
            boolean hasIdentityNumberError = false;
            boolean hasMailAddressError = false;


            if (firstName == null || firstName.trim().isEmpty()) {
                request.setAttribute("emptyFirstNameFieldError","First Name field can not be empty");
                hasFirstNameError = true;
            } else if (!managementDAO.isAllChars(firstName)) {
                request.setAttribute("typeMismatchError", "First Name can only contain characters");
                firstName = "";
                hasFirstNameError = true;
            }

            if (lastName == null || lastName.trim().isEmpty()) {
                request.setAttribute("emptyLastNameFieldError","Last Name field can not be empty");
                hasLastNameError = true;
            } else if (!managementDAO.isAllChars(lastName)) {
                request.setAttribute("typeMismatchError", "Last Name can only contain characters");
                lastName = "";
                hasLastNameError = true;
            }

            if (identityNumber == null || identityNumber.trim().isEmpty()) {
                request.setAttribute("emptyIdentityNumberFieldError", "Identity Number field can not be empty");
                hasIdentityNumberError = true;
            } else if (identityNumber.length() != 11 || !managementDAO.isAllDigits(identityNumber) || identityNumbersList.contains(identityNumber)) {
                 if (identityNumber.length() != 11 && managementDAO.isAllDigits(identityNumber)) {
                     request.setAttribute("sizeError", "Identity Number can only 11 digit long");
                 } else if (!managementDAO.isAllDigits(identityNumber)) {
                     request.setAttribute("typeMismatchError", "Identity Number can only contain digits");
                 } else if (identityNumbersList.contains(identityNumber)) {
                     request.setAttribute("duplicateIdError", "This Identity Number is belong to an enrolled student");
                 }
                 identityNumber = "";
                 hasIdentityNumberError = true;
            }

            if (mailAddress == null || mailAddress.trim().isEmpty()) {
                mailAddress = "";
                request.setAttribute("emptyMailAddressFieldError", "Mail Address field cannot be empty");
                hasMailAddressError = true;
            } else if (!mailAddress.matches("^[^@\\s]+@[^@\\s]+(\\.[^@\\s]{2,})+$") || mailAddressesList.contains(mailAddress)) {
                if (!mailAddress.matches("^[^@\\s]+@[^@\\s]+(\\.[^@\\s]{2,})+$") && !mailAddressesList.contains(mailAddress)) {
                    request.setAttribute("invalidMailError", "Please enter a valid mail address");
                } else if (mailAddressesList.contains(mailAddress)) {
                    request.setAttribute("duplicateMailAddressError", "This Mail Address is belong to an enrolled student");
                }
                mailAddress = "";
                hasMailAddressError = true;
            }




            if (hasFirstNameError || hasLastNameError || hasIdentityNumberError || hasMailAddressError) {
                forwardToCreatePage(request, response);
            } else {
                int studentNumber = Integer.parseInt(managementDAO.studentNumberGeneration());
                Student student = new Student(firstName, lastName, studentNumber, identityNumber, mailAddress );
                managementDAO.addAccountInDB(student);

                // email

                EmailUtil emailUtil = new EmailUtil();
                String emailSubject = "Welcome";
                String emailContent =
                        "We are happy to have you in our college " + firstName +  lastName;
                try {
                    emailUtil.emailSender(mailAddress, emailSubject, emailContent);
                } catch (AddressException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect(request.getContextPath() + "/student-login");
            }
        }


        public void forwardToCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-account-page.jsp");
            requestDispatcher.forward(request, response);
        }
}

