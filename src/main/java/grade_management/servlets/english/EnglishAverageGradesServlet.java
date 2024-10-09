package grade_management.servlets.english;


import grade_management.entity.letter_grades.EnglishLetterGrade;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/english-teacher-home/english-letter-grades")
public class EnglishAverageGradesServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EnglishLetterGrade> englishAverageGradesList = managementDAO.listLetterTableRecords("English", EnglishLetterGrade.class);
        request.setAttribute("englishAverageGradesList", englishAverageGradesList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/english-letter-grades.jsp");
        requestDispatcher.forward(request,response);
    }
}
