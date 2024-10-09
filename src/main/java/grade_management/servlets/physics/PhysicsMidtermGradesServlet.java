package grade_management.servlets.physics;

import grade_management.entity.midterms.PhysicsMidterm;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/physics-teacher-home/physics-midterm-grades")
public class PhysicsMidtermGradesServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PhysicsMidterm> physicsMidtermGrades = managementDAO.listMidtermGrades(PhysicsMidterm.class, "Physics");
            request.setAttribute("physicsMidtermGrades", physicsMidtermGrades);

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("physics-midterm-grades.jsp");
        dispatcher.forward(request, response);
    }
}
