package grade_management.servlets.physics;


import grade_management.entity.finals.PhysicsFinal;
import grade_management.management_dao.ManagementDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/physics-teacher-home/physics-final-grades")
public class PhysicsFinalGradesServlet extends HttpServlet {

    private ManagementDAO managementDAO;

    @Override
    public void init() throws ServletException {
        managementDAO = new ManagementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<PhysicsFinal> studentPhysicsFinal = managementDAO.listFinalGrades(PhysicsFinal.class, "Physics");
            request.setAttribute("studentPhysicsFinal", studentPhysicsFinal);
        }catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/physics-final-grades.jsp");
        dispatcher.forward(request, response);
    }
}
