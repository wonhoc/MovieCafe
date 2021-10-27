package controller.member;

import java.io.IOException;
import java.util.ArrayList;

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

@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = 1024 * 300, maxRequestSize = -1L, location = "")
@WebServlet("/joinUser")
public class RegistUserServlet extends HttpServlet {
	
	// ������ ���� ���ε� ���. ���� ���� �� ������ ��.

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		request.setCharacterEncoding("utf-8");
		try {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		String userBirth = request.getParameter("birthYear") + "-" +
							request.getParameter("birthMonth") + "-" +
							request.getParameter("birthDate");
		String userContact = request.getParameter("contact1") + "-" +
							request.getParameter("contact2") + "-" +
							request.getParameter("contact3");
		String userNick = request.getParameter("userNick");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("pickGender");
		System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(userEmail);
		System.out.println(userBirth);
		System.out.println(userContact);
		System.out.println(userNick);
		System.out.println(userName);
		System.out.println(gender);
		
		// 프로필 사진 업로드
		
		
		
		
		Part part = request.getPart("profilePhoto");
		ArrayList<String> filName = FileUploadUtils.upload(part, request, "user");
		
		String photoOrigin = filName.get(0);
		String photoSys = filName.get(1);
		
		
		
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
		
		// ȸ�� ���� �� �̵��� ������ �ּ�. ���� ������ ��
		response.sendRedirect(request.getContextPath() + "/main.do");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

}
