package controller.message;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import controller.ActionForward;
import controller.Command;
import domain.message.AddressVo;
import domain.message.SendMessageVo;
import model.service.message.MsgService;

public class SendMsgController implements Command {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�Խñ� ���� ��û Ŀ�ǵ�
		try {
			
		
		//HttpSession session = request.getSession();
		//String writer = session.getAttribute("userId"); //�α��� ������ ����� ����
		request.setCharacterEncoding("utf-8"); //���� �� ���߿� �߰��ϱ�
		
		String writer = request.getParameter("userid"); //�׽�Ʈ��
		String receiveIds[] = request.getParameterValues("reciveId"); //�޴»�� ���̵�
		String sendMsgContent = request.getParameter("sendMsgContent"); //�߽��� ���� ����

		SendMessageVo msgVo = new SendMessageVo();
		ArrayList<String>addrs = new ArrayList<String>(); //����������� ������ ArrayList
		msgVo.setWriterId(writer);	//세션에 바인딩된 사용자 ID
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
