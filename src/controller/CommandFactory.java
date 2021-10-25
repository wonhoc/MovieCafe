package controller;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	private static CommandFactory factory;
	
	private Map<String, String> map = new HashMap<String, String>();
	
	// constructor
	private CommandFactory() {
		// 영화 목록 조회 요청
		map.put("/main.do", "controller.movie.MovieListCommand");
		
		// 영화 삭제 요청
		map.put("/removeMovie.do", "controller.movie.RemoveMovieCommand");
		
		// 영화 상세 조회 요청
		map.put("/detailMovie.do", "controller.movie.DetailMovieCommand");
		
		// 영화 정보 수정 폼 요청
		map.put("/modifyMovieForm.do", "controller.movie.ModifyMovieFormCommand");
		
		// 관람평 작성 폼 요청
		map.put("/registerGuanramForm.do", "controller.movie.RegisterGuanramFormCommand");
		
		// 관람평 작성 요청
		map.put("/registerGuanram.do", "controller.movie.RegisterGuanramCommand");
		
		// 관람평 삭제 요청
		map.put("/removeGuanram.do", "controller.movie.RemoveGuanramCommand");
		
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
