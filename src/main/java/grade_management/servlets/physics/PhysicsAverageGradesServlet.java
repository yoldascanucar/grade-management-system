package grade_management.servlets.physics;

import grade_management.entity.letter_grades.PhysicsLetterGrade;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/physics-teacher-home/physics-letter-grades")
public class PhysicsAverageGradesServlet extends HttpServlet {


    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PhysicsLetterGrade> physicsAverageGradesList = managementDAO.listLetterTableRecords("Physics", PhysicsLetterGrade.class);
        request.setAttribute("physicsAverageGradesList", physicsAverageGradesList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/physics-letter-grades.jsp");
        requestDispatcher.forward(request,response);
    }
}
