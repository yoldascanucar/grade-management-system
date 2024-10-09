package grade_management.servlets.english;


import grade_management.entity.midterms.EnglishMidterm;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/english-teacher-home/english-midterm-grades")
public class EnglishMidtermGradesServlet  extends HttpServlet {

    ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
       managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EnglishMidterm> englishMidtermGrades = managementDAO.listMidtermGrades(EnglishMidterm.class, "English");
            request.setAttribute("englishMidtermGrades", englishMidtermGrades);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/english-midterm-grades.jsp");
        dispatcher.forward(request, response);
    }
}
