package grade_management.servlets.physics;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/physics-teacher-home/*")
public class PhysicsTeacherHomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String pathInfo = request.getPathInfo();

       if (pathInfo == null || pathInfo.equals("/")) {
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("physics-teacher-home.jsp");
           requestDispatcher.forward(request, response);
       } else {
           String subPath = pathInfo.substring(1);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + subPath);
           requestDispatcher.forward(request, response);
       }
    }
}
