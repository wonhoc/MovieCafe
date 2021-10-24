package controller.member;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.member.UserInfoVo;
import model.service.member.UserService;

@MultipartConfig(fileSizeThreshold = 1024, maxFileSize = 1024 * 300, maxRequestSize = -1L, location = "")
@WebServlet("/joinUser")
public class RegistUserServlet extends HttpServlet {
	
	// 프로필 사진 업로드 경로. 실제 적용 시 변경할 것.
	public static final String UPLOAD_PATH = "C:/upload";

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

		
		// 프로필 사진 업로드
		Part part = request.getPart("profilePhoto");
		String photoOrigin = part.getSubmittedFileName();
		
		File file = new File(UPLOAD_PATH + "/" + photoOrigin);
		String photoSys = "";
		
		if(file.exists()) {
			photoSys =  
					photoOrigin.substring(0,photoOrigin.lastIndexOf(".")) + "_" + 
					UUID.randomUUID() + photoOrigin.substring(photoOrigin.lastIndexOf(".")); 
					
		} else {
			photoSys = photoOrigin;
		}
		
		part.write(UPLOAD_PATH + "/" + photoSys);
		part.delete();
		
		
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
		
		// 회원 가입 후 이동할 페이지 주소. 추후 수정할 것
		response.sendRedirect(request.getContextPath() + "");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

}
