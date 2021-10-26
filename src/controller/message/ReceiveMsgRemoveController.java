package controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.service.message.MsgService;

public class ReceiveMsgRemoveController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//메세지 삭제 요청
		try {
		MsgService service = MsgService.getInstance();
		//삭제 요청한 쪽지 번호 배열
		String[] removeNos = request.getParameterValues("removeCheckBox");
		
		for(String Nos : removeNos) {	
		int no =  Integer.parseInt(Nos.substring(0, Nos.indexOf(","))); //삭제 요청한 내게 온 쪽지 번호	
		int isRead = Integer.parseInt(Nos.substring(Nos.indexOf(",") + 1, Nos.lastIndexOf(","))); //삭제 요청한 내게 온 쪽지의 읽음 상태
		String receiveId = Nos.substring(Nos.lastIndexOf(",") + 1, Nos.length());
		System.out.println("no : " + no + " isRead : " + isRead);
		if(isRead == 1) {	
			service.removeReceiveMsg(no);
		}else {
		//만약 '읽지않음' 상태였다면 address 테이블에 update하고 삭제
			service.ReadUpdateRemove(no, receiveId);
			
		}//if end
		
		}//for end
		
		
		
		} catch (Exception e) {
			throw e;
		}//end
		
		return new ActionForward("/receiveMsgList.do", true);
		
	}//execute() end
}// class end
