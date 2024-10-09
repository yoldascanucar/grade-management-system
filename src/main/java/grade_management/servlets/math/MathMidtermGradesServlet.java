package grade_management.servlets.math;
import grade_management.entity.midterms.MathMidterm;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/math-teacher-home/math-midterm-grades")
public class MathMidtermGradesServlet extends HttpServlet {


    private ManagementDAO managementDAO;

    public void init() throws ServletException {
         managementDAO = new ManagementDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<MathMidterm> mathMidtermGrades = managementDAO.listMidtermGrades(MathMidterm.class, "Math");
            request.setAttribute("mathMidtermGrades", mathMidtermGrades);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/math-midterm-grades.jsp");
        dispatcher.forward(request, response);
    }
}
