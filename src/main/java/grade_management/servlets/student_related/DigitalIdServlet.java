package grade_management.servlets.student_related;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@WebServlet("/my-digital-id-card")
public class DigitalIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Map<String, String> studentIdInfo = (Map<String, String>) session.getAttribute("studentIdInfo");

        request.setAttribute("studentIdInfo", studentIdInfo);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("id-card.jsp");
        requestDispatcher.forward(request, response);
    }
}
