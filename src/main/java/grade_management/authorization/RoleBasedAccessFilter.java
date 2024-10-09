package grade_management.authorization;


import grade_management.entity.teacher.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/math-teacher-home/*", "/physics-teacher-home/*", "/english-teacher-home/*"})
public class RoleBasedAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        if (session == null || session.getAttribute("teacher") == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/teacher-login-page");
            return;
        }

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        String role = teacher.getRole().trim();
        String requestURI = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();

        boolean authorized = false;

        if (requestURI.startsWith(contextPath + "/math-teacher-home") && role.equals("MathTeacher")) {
            authorized = true;
        } else if (requestURI.startsWith(contextPath + "/physics-teacher-home") && role.equals("PhysicsTeacher")) {
            authorized = true;
        } else if (requestURI.startsWith(contextPath + "/english-teacher-home") && role.equals("EnglishTeacher")) {
            authorized = true;
        }

        if (authorized) {
            filterChain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect(contextPath + "/access-denied");
        }
    }

    @Override
    public void destroy() {

    }
}
