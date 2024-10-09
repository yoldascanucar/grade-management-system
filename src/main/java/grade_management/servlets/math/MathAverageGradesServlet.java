package grade_management.servlets.math;
import grade_management.entity.letter_grades.MathLetterGrade;
import grade_management.management_dao.ManagementDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/math-teacher-home/math-letter-grades")
public class MathAverageGradesServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<MathLetterGrade> mathAverageGradesList = managementDAO.listLetterTableRecords("Math", MathLetterGrade.class);
        request.setAttribute("mathAverageGradesList", mathAverageGradesList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/math-letter-grades.jsp");
        requestDispatcher.forward(request,response);
    }
}
