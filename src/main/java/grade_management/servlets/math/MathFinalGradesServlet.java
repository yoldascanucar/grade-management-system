package grade_management.servlets.math;

import grade_management.entity.finals.MathFinal;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/math-teacher-home/math-final-grades")
public class MathFinalGradesServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<MathFinal> studentMathFinal = managementDAO.listFinalGrades(MathFinal.class, "Math");
            request.setAttribute("studentMathFinal", studentMathFinal);
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/math-final-grades.jsp");
        dispatcher.forward(request, response);
    }
}
