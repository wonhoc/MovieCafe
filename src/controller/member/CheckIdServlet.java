package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.member.UserService;


@WebServlet("/checkId")
public class CheckIdServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		try {
			String inputId = request.getParameter("userId");
			
			UserService service = UserService.getInstance();
			boolean isChecked = service.checkId(inputId);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
