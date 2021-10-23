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
