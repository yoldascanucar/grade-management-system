package grade_management.util;

import grade_management.management_dao.ManagementDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ValidationUtils {

    private final ManagementDAO managementDAO;

    public ValidationUtils(ManagementDAO managementDAO) {
        this.managementDAO = managementDAO;
    }

    public Map<String, String> validateUserInput(HttpServletRequest request) {
        String identityNumber = request.getParameter("identityNumber");
        String mailAddress = request.getParameter("mailAddress");
        String firstName = request.getParameter("firstName");

        Map<String, String> errors = new HashMap<>();

        if (identityNumber == null || identityNumber.trim().isEmpty()) {
            errors.put("emptyIdentityNumberFieldError", "Identity Number field cannot be empty");
        } else if (identityNumber.length() != 11 || !managementDAO.isAllDigits(identityNumber)) {
            if (identityNumber.length() != 11 && managementDAO.isAllDigits(identityNumber)) {
                errors.put("sizeError", "Identity Number must be 11 digits long");
            } else {
                errors.put("typeMismatchError", "Identity Number can only contain digits");
            }
        }

        if (mailAddress == null || mailAddress.trim().isEmpty()) {
            errors.put("emptyMailAddressFieldError", "Mail Address field cannot be empty");
        } else if (!mailAddress.matches("^[^@\\s]+@[^@\\s]+(\\.[^@\\s]{2,})+$")) {
            errors.put("invalidMailError", "Please enter a valid mail address");
        }

        if (firstName == null || firstName.trim().isEmpty()) {
            errors.put("emptyFirstNameFieldError", "First Name field cannot be empty");
        } else if (!managementDAO.isAllChars(firstName)) {
            errors.put("typeMismatchError", "First Name can only contain characters");
        }

        return errors;
    }

    public boolean studentNumberRenewalAuthentication(String mailAddress, String identityNumber, String firstName) {
        return managementDAO.authenticateStudentForNumberRenewal(mailAddress, identityNumber, firstName);
    }

    public boolean teacherNumberRenewalAuthentication(String mailAddress, String identityNumber, String firstName) {
        return managementDAO.authenticateTeacherForNumberRenewal(mailAddress, identityNumber, firstName);
    }
}