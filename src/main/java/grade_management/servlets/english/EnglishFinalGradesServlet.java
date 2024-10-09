package grade_management.servlets.english;

import grade_management.entity.finals.EnglishFinal;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/english-teacher-home/english-final-grades")
public class EnglishFinalGradesServlet extends HttpServlet {

    ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EnglishFinal> englishFinalGrades = managementDAO.listFinalGrades(EnglishFinal.class, "English");
            request.setAttribute("englishFinalGrades", englishFinalGrades);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("english-final-grades.jsp");
        requestDispatcher.forward(request, response);
    }
}
