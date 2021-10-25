package util.board;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

import domain.board.BoardFileVo;

public class FileUtils {

	public static final String UPLOAD_PATH = "C:\\upload";
	
	public static BoardFileVo uploadFile(Part part) throws IOException {
		
		String boardfileOrigin = part.getSubmittedFileName();
		
		String boardfileSys = "";
		File tempFile = new File(UPLOAD_PATH + File.separator+ boardfileOrigin);
		
		if (tempFile.exists()) {
			
			UUID uuid = UUID.randomUUID();
			String filename = boardfileOrigin.substring(0, boardfileOrigin.lastIndexOf("."));
			String extension = boardfileOrigin.substring(boardfileOrigin.lastIndexOf("."));
			boardfileSys = filename + "_" + uuid + extension;
		}else {
			boardfileSys = boardfileOrigin;
		}
		
		part.write(UPLOAD_PATH + File.separator + boardfileSys);
		part.delete();
		
		return new BoardFileVo(boardfileOrigin, boardfileSys, part.getSize());
		
	}
	
	
}
