package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.dao.message.ReceiveMSgDao;

public class CheckReceiveIdController implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String receiveId = request.getParameter("userId");
		
		//DB에서 입력받은 아이디를 조회한 값을 return해주기
		int resultCount = ReceiveMSgDao.getInstance().selectId(receiveId);
		System.out.println(resultCount);
		request.setAttribute("resultCount", resultCount);	
		return new ActionForward("/message/countReceiveId.jsp", false);
		
		
		
	}//execute() end
	
}//class end
