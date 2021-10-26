package util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUploadUtils {

	public static String UPLOAD_PATH = "";
	
	public static ArrayList<String> upload(Part part, HttpServletRequest request, String uploadPath) throws Exception {
		
		UPLOAD_PATH = request.getServletContext().getRealPath("/") + "/upload" + uploadPath;
		
		ArrayList<String> fileName = new ArrayList<String>();
		
		System.out.println(part.getSubmittedFileName());
		String originalFileName = part.getSubmittedFileName();

		File file = new File(uploadPath + "/" + originalFileName);
		String systemFileName = "";

		if (file.exists()) {
			systemFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + UUID.randomUUID()
					+ originalFileName.substring(originalFileName.lastIndexOf("."));
		} else {
			systemFileName = originalFileName;
		}

		part.write(uploadPath + "/" + systemFileName);
		part.delete();
		
		fileName.add(originalFileName);
		fileName.add(systemFileName);
		
		return fileName;
	}
}
