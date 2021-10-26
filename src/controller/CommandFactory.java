package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	// constructor
	private CommandFactory() {
		//쪽지쓰기 폼 요청
		map.put("/sendMsgForm.do", "controller.message.SendMsgFormController");
		//쪽지쓰기 요청
		map.put("/sendMsg.do", "controller.message.SendMsgController");
		//보낸 메시지 목록 요청
		map.put("/sendMsgList.do", "controller.message.SendMsgListController");
		//보낸 메시지 삭제 요청
		map.put("/removeSendMsg.do", "controller.message.SendMsgRemoveController");
		//보낸 메세지 상세보기 요청
		map.put("/detailSendMsg.do", "controller.message.SendMsgDetailController");
		//받은 메세지 목록 요청
		map.put("/receiveMsgList.do", "controller.message.ReceivceMsgListController");
		//받은 메세지 상세보기 요청
		map.put("/detailReceiveMsg.do", "controller.message.ReceivceMsgDetailController");
		//받은 메세지 삭제 요청
		map.put("/removeReceiveMsg.do", "controller.message.ReceiveMsgRemoveController");
		//보낸 메세지 상세보기 페이지에서 삭제 요청
		map.put("/removeDetailSendMsg.do", "controller.message.RemoveDetailSendMsgController");
		//받은 메세지 상세보기 페이지에서 삭제 요청
		map.put("/removeDetailReceiveMsg.do", "controller.message.RemoveDetailReceiveMsgController");
		//비동기처리로 보낸사람 확인 요청
		map.put("/CheckReceiveId.do", "controller.message.CheckReceiveIdController");
	}
	
	public static CommandFactory getInstance() {
		if(factory == null) {
			factory = new CommandFactory();
		}
		return factory;
	}
	
	public Command createCommand(String commandURI) throws Exception {
		String commandClass = map.get(commandURI);
		if(commandClass == null) {
			return null;
		}
		try {
			Class<?> cls = Class.forName(commandClass);
			Constructor<?> constructor = cls.getConstructor(null);
			Command command = (Command) constructor.newInstance();
			return command;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
