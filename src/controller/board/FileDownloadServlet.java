package controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.BufferedInputFilter;
import org.apache.tomcat.util.http.fileupload.FileUpload;

import util.board.FileUtils;

@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String boardfileOrigin = request.getParameter("boardfileOrigin");
		String boardfileSys = request.getParameter("boardfileSys");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			File file = new File(FileUtils.UPLOAD_PATH + "/" + boardfileSys);
			
			if (file.exists()) {
				response.setHeader("Content-Type", "application/octet-stream");
				response.setHeader("Content-Transfer-Encoding", "binary");
				boardfileOrigin = new String(boardfileOrigin.getBytes("utf-8"), "ISO_8859_1");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + boardfileOrigin + "\"");
				int len = (int)file.length();
				response.setContentLength(len);
			
				String filePath = FileUtils.UPLOAD_PATH + "/" +boardfileSys;
				bis = new BufferedInputStream(new FileInputStream(filePath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buf = new byte[1024];
				int readByte = 0;
				while ((readByte = bis.read(buf)) != -1) {
					bos.write(buf, 0, readByte);
					
				}
			}
			else {
				throw new Exception("파일이 존재하지 않습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
	}

}
