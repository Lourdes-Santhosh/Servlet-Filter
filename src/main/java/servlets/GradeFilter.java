package servlets;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/GradeCalculatorServlet")
public class GradeFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        int subject1 = Integer.parseInt(request.getParameter("subject1"));
        int subject2 = Integer.parseInt(request.getParameter("subject2"));
        int subject3 = Integer.parseInt(request.getParameter("subject3"));
        
        int average = (subject1 + subject2 + subject3) / 3;
        
        if (average >= 0 && average <= 100) {
            request.setAttribute("averageMark", average);
            
            if (average >= 90) {
                request.getRequestDispatcher("AGrade").forward(request, response);
            } else if (average >= 80 && average <= 89) {
                request.getRequestDispatcher("BGrade").forward(request, response);
            } else if (average >= 70 && average <= 79) {
                request.getRequestDispatcher("CGrade").forward(request, response);
            } else if (average >= 60 && average <= 69) {
                request.getRequestDispatcher("DGrade").forward(request, response);
            } else if (average >= 50 && average <= 59) {
                request.getRequestDispatcher("EGrade").forward(request, response);
            } else {
                request.getRequestDispatcher("FailServlet").forward(request, response);
            }
        } else {
            response.getWriter().println("Error: Invalid marks entered");
        }
    }
    
    public void init(FilterConfig config) throws ServletException {
    }
    
    public void destroy() {
    }
}
