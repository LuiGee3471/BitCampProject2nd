package kr.co.groot.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.groot.dto.Staff;

@WebFilter("/index.jsp")
public class LoggedFilter implements Filter {

  public LoggedFilter() {

  }

  public void destroy() {

  }

  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    Staff loggedUser = (Staff) session.getAttribute("staff");
    if (loggedUser != null) {
      res.sendRedirect(req.getContextPath() + "/main");
      return;
    }
    
    chain.doFilter(request, response);
  }

  public void init(FilterConfig fConfig) throws ServletException {
  }

}
