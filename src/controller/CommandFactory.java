package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	//싱글톤 패턴
		private static CommandFactory factory;
		private Map<String, String> map = new HashMap<String, String>();
		
		private CommandFactory() {
			
			
			
			//사용자정보조회
			map.put("/listUser.do", "controller.member.ListUserCommand");
			
		}
		
		
		public static CommandFactory getInstance() {
			if(factory == null) {
				factory = new CommandFactory();
			}
			return factory;
		}
		
		
		public Command createCommand(String commandURI) throws Exception {
			String commandClass = map.get(commandURI);
			if (commandClass == null) {
				return null;
			}
			
			try {	
				// 동적 클래스 로딩 후 인스턴스 생성  
				Class<?> cls = Class.forName(commandClass);
				Constructor<?> constructor = cls.getConstructor(null);
				Command command = (Command)constructor.newInstance();
				return command;
			} catch (Exception e) {
				throw e;
			}
			
		}	
	}
