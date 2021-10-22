package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.member.UserInfoVo;
import model.service.member.UserService;

@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = 1024 * 300, maxRequestSize = -1L, location = "/temp")
@WebServlet("/joinUser.do")
public class JoinMemberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		String userBirth = request.getParameter("birthYear" + "birthMonth" + "birthDate");
		String userContact = request.getParameter("contact1" + "contact2" + "contact3");

		String userNick = request.getParameter("userNick");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("pickGender");

		Part part = request.getPart("profilePhoto");
		BoardFileVo file = FileUploadUtils.upload(part);
		
		String photoOrigin = file.getOriginalFileName;
		String photoSys = file.getSystemFileFame;
		
		UserInfoVo userInfoVo = new UserInfoVo();
		
		userInfoVo.setUserId(userId);
		userInfoVo.setUserPwd(userPwd);
		userInfoVo.setUserEmail(userEmail);
		userInfoVo.setUserBirth(userBirth);
		userInfoVo.setUserContact(userContact);
		userInfoVo.setUserNick(userNick);
		userInfoVo.setUserName(userName);
		userInfoVo.setGender(gender);
		userInfoVo.setPhotoOrigin(photoOrigin);
		userInfoVo.setPhotoSys(photoSys);
		
		UserService service = UserService.getInstance();
		service.registUser(userInfoVo);

	}

}
