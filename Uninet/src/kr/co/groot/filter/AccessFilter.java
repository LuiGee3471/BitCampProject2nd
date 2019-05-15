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

/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter({ "/main", "/board/*", "/mypage/*", "/message", "/message/*",
    "/lecture/*", "/mypost", "/mycomment", "/stat/*", "/info" })
public class AccessFilter implements Filter {

  public AccessFilter() {
  }

  public void destroy() {
  }

  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    Staff loggedUser = (Staff) session.getAttribute("staff");
    if (loggedUser == null) {
      res.sendRedirect(req.getContextPath());
      return;
    }
    
    chain.doFilter(request, response);
  }


  public void init(FilterConfig fConfig) throws ServletException {
  }

}
