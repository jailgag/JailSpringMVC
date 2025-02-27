package com.jail.spring.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
	public Map<String, String> saveFile(MultipartFile uploadFile,HttpSession session, String type) throws IllegalStateException, IOException {
			Map<String, String> result = new HashMap<String, String>();
			String folderName = type.equals("notice")? "nUploadFiles": "bUploadFiles";
			String prefix = type.toLowerCase().substring(0,1);
			String noticeFilename = uploadFile.getOriginalFilename();
			String noticeFileRename = null;
			String noticeFilepath = null;
			//시간을 이용하기위한 simpleDatefomat
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			//Date임포트는 유틸패키지로!지금시간이 나온다!위yyyy랑같은코드로묶는다?이걸로파일이름을할거다(시분초로)
			//현재시간을 내가 원하는 패턴으로 변환
			String transStr = sdf.format(new Date(System.currentTimeMillis())); 
			//원본파일의 확장자 가져오기
			String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
			//파일이름 변경완료
			noticeFileRename = transStr +"." +ext;
			noticeFilepath ="/resources/"+folderName+"/"+noticeFileRename;
			String folderPath = session.getServletContext().getRealPath("/resources/"+folderName);
			String savePath = folderPath +"\\"+ noticeFileRename;
			uploadFile.transferTo(new File(savePath));
			result.put(prefix+"Filename", noticeFilename);
			result.put(prefix+"FileRename", noticeFileRename);
			result.put(prefix+"Filepath", noticeFilepath);
			return result;
		}
	}

