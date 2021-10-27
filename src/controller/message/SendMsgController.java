package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import controller.ActionForward;
import controller.Command;
import domain.member.UserInfoVo;
import domain.message.AddressVo;
import domain.message.SendMessageVo;
import model.service.message.MsgService;

public class SendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�Խñ� ���� ��û Ŀ�ǵ�
		try {
			
		
		
		request.setCharacterEncoding("utf-8"); //���� �� ���߿� �߰��ϱ�
		
		HttpSession session = request.getSession();
		UserInfoVo user = (UserInfoVo)session.getAttribute("userInfo");
		String userId = user.getUserId();
		
		
		String receiveIds[] = request.getParameterValues("reciveId"); //�޴»�� ���̵�
		String sendMsgContent = request.getParameter("sendMsgContent"); //�߽��� ���� ����

		SendMessageVo msgVo = new SendMessageVo();
		ArrayList<String>addrs = new ArrayList<String>(); //����������� ������ ArrayList
		msgVo.setWriterId(userId);	//세션에 바인딩된 사용자 ID
		msgVo.setSendMsgContent(sendMsgContent);
		//배열에 저장된 값들 주소록에 넣기
		for(String receiveId : receiveIds ) {		
			addrs.add(receiveId);
		}//for end
		msgVo.setAddress(addrs);
		MsgService service = MsgService.getInstance();
		
		service.registerMsg(msgVo);
		} catch (Exception e) {
			throw e;
		}
		

		return new ActionForward("/sendMsgList.do", true);
		

	}//execute() end
}// class end
