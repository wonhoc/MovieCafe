package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutHandler")
public class LogoutHandlerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		
		if(userId != null) {
			session.invalidate();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.print(userId + "´ÔÀÌ ·Î±×¾Æ¿ôÇÏ¼Ì½À´Ï´Ù.");
			pw.close();
		} else {
			response.sendRedirect(request.getContextPath() + "/loginUserForm.jsp" );
		}
	}

}
