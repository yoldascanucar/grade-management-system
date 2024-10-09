package grade_management.servlets.english;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/english-teacher-home/*")
public class EnglishTeacherHomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String pathInfo = request.getPathInfo();

       if (pathInfo == null || pathInfo.equals("/")) {
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/english-teacher-home.jsp");
           requestDispatcher.forward(request, response);
       } else {
           String subPath = pathInfo.substring(1);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + subPath);
           requestDispatcher.forward(request , response);
       }
    }
}
