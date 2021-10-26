package controller.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.member.UserInfoVo;
import model.service.member.UserService;
import util.file.FileUploadUtils;


@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = -1L, maxRequestSize = -1L, location = "/temp")
@WebServlet("/uploadFile")
public class UploadFileServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		request.setCharacterEncoding("utf-8");

		try {			
			String userId = request.getParameter("userId");
			String userPwd = request.getParameter("userPwd");
			String userNick = request.getParameter("userNick");
			String userEmail = request.getParameter("userEmail");
			String userYear = request.getParameter("birthYear");
			String userMonth = request.getParameter("birthMonth");
			String userDate = request.getParameter("birthDate");
			String userContact1 = request.getParameter("contact1");
			String userContact2 = request.getParameter("contact2");
			String userContact3 = request.getParameter("contact3");
			String gender = request.getParameter("gender");
			String userName = request.getParameter("userName");
			
			UserInfoVo user = new UserInfoVo();
			user.setUserId(userId);
			user.setUserPwd(userPwd);
			user.setUserNick(userNick);
			user.setUserEmail(userEmail);
			user.setUserBirth(userYear + "-" + userMonth + "-" + userDate);
			user.setUserContact(userContact1 + "-" + userContact2 + "-" + userContact3 );
			user.setGender(gender);
			user.setUserName(userName);

			Part part = request.getPart("photoSys");
			if(part.getSize() != 0) {
				ArrayList<String> fileName = FileUploadUtils.upload(part, request, "user");
				String photoOrigin = fileName.get(0);
				String photoSys = fileName.get(1);

				user.setPhotoOrigin(photoOrigin);
				user.setPhotoSys(photoSys);

			}			
		
			System.out.println(user);

			UserService service = UserService.getInstance();
			service.modifyUser(user);

			response.sendRedirect(request.getContextPath() + "/indexControl.jsp?contentTemplate=modifyUserForm.do?userId=" + userId);

		} catch (Exception ex) {
			
			ex.printStackTrace();
			request.setAttribute("exception", ex);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

	}

}
