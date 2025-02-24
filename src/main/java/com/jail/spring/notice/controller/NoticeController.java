package com.jail.spring.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jail.spring.notice.domain.NoticeVO;
import com.jail.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	
	private NoticeService nService;
	
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService = nService;
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.GET)
	public String showNoticeForm() {
		return "notice/insert";
	}
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(@RequestParam("noticeSubject") String noticeSubject
			,@RequestParam("noticeContent") String noticeContent
			,@RequestParam("uploadFile") MultipartFile uploadFile
			,HttpSession session,Model model) { 
			try {
				String noticeWriter = session.getAttribute("memberId") != null 
						?(String)session.getAttribute("memberId") : "anonymous";
				String noticeFilename = uploadFile.getOriginalFilename();
				String noticeFileRename = null;
				String noticeFilepath = null;
				if(noticeFilename != null) {
					
					
					//시간을 이용하기위한 simpleDatefomat
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					//Date임포트는 유틸패키지로!지금시간이 나온다!위yyyy랑같은코드로묶는다?이걸로파일이름을할거다(시분초로)
					//현재시간을 내가 원하는 패턴으로 변환
					String transStr = sdf.format(new Date(System.currentTimeMillis())); 
					//원본파일의 확장자 가져오기
					String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
					//파일이름 변경완료
					noticeFileRename = transStr +"." +ext;
					noticeFilepath ="/resources/nUploadFiles/"+noticeFileRename;
					
					String folderPath = session.getServletContext().getRealPath("/resources/nUploadFiles");
					String savePath = folderPath +"\\"+ noticeFileRename;
					uploadFile.transferTo(new File(savePath));
				}
				//NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter);
				NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter, noticeFilename, noticeFileRename, noticeFilepath);
				int result = nService.insertNotice(notice);
				if(result > 0 ) {
					return "redirect:/notice/list";
				}else {
					model.addAttribute("errorMsg","공지사항 등록이 완료되지 않았습니다");
					return "common/error";
				}

			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("errorMsg", e.getMessage());
				return "common/error";
			}
				
			
	}
	@RequestMapping(value="/notice/update", method=RequestMethod.GET)
	public String showmodifyForm(@RequestParam("noticeNo") int noticeNo
			,Model model) {
		try {
			NoticeVO notice = nService.selectOneByNo(noticeNo);
			model.addAttribute("notice",notice);
			return "notice/update";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/notice/update", method=RequestMethod.POST)
 	public String updateNotice(@RequestParam("noticeNo")int noticeNo
 			,@RequestParam("noticeSubject") String noticeSubject
 			,@RequestParam("noticeContent") String noticeContent
 			,@RequestParam("reloadFile") MultipartFile reloadFile
 			,HttpSession session
 			,Model model) {
 			try {
				String noticeFilename = reloadFile.getOriginalFilename();
 				String noticeFileRename = null;
 				String noticeFilepath = null;
 				if(noticeFilename != null) {
 					String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
 					//String renameStr = UUID.randomUUID() +;
 					noticeFileRename= UUID.randomUUID()+"."+ ext;
 					String folderPath = session.getServletContext().getRealPath("/resources.nUploadFiles");
 					String savePath = folderPath + "\\" + noticeFileRename;
 					reloadFile.transferTo(new File(savePath));//파일저장!!
 					noticeFilepath = "/resources/nUploadFiles/" + noticeFileRename;
 				}
 				
				NoticeVO notice = new NoticeVO(noticeNo, noticeSubject, noticeContent, noticeFilename, noticeFileRename, noticeFilepath);
 				int result =  nService.updateNotice(notice);
 				if(result > 0) {
 					return "redirect:/notice.detail?noticeNo="+notice.getNoticeNo();
 					
 				}else {
 					model.addAttribute("errorMsg","수정이 완료되지 않았습니다");
 					return "common/error";
 				}
			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("errorMsg", e.getMessage());
				return "common/error";
			}
 	}
	@RequestMapping(value="/notice/delete",method=RequestMethod.GET)
	public String deleteNotice(
			@RequestParam("noticeNo") int noticeNo
			,Model model) {
		try {
			int result = nService.deleteNotice(noticeNo);
			return "redirect:/notice/list";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
	
	
	@RequestMapping(value="/notice/list", method=RequestMethod.GET)
	public String shownoticeList(
			@RequestParam(value="page", defaultValue="1")int currentPage
			,Model model) {
		try {
			// int currentPage = request.getParameger("currentPage") != null
			// ? Integer.parseInt(reqeust.getParameter("currentPage")) : "1";
			List<NoticeVO> nList = nService.selectList(currentPage);
			int totalCount = nService.getTotalCount();
			int boardLimit = 10;
			int maxPage = 0;
			if(totalCount % boardLimit !=0) {
				maxPage = totalCount /boardLimit +1;
			}else {
				maxPage = totalCount /boardLimit;
			}
			int naviLimit = 5;
			
			int startNavi = ((currentPage-1)/naviLimit)*naviLimit+1;
			int endNavi = (startNavi-1)+naviLimit;
			if(endNavi > maxPage ) {
				endNavi = maxPage;
			}
			
			if(!nList.isEmpty()) {
				model.addAttribute("maxPage",maxPage);
				model.addAttribute("startNavi",startNavi);
				model.addAttribute("endNavi",endNavi);
				model.addAttribute("nList",nList);
				return "notice/list";
			}else {
				model.addAttribute("errorMsg","데이터가 존재하지않습니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/notice/search", method=RequestMethod.GET)
	public String showSearchList(
			@RequestParam("searchCondition") String searchCondition
			,@RequestParam("searchKeyword") String searchKeyword
			,@RequestParam(value="page",defaultValue="1") int currentPage 
			,Model model) {
			//1.VO만들기
			// se
		try {
			Map<String, String> paramMap = new HashMap<String, String>(); 
			paramMap.put("searchCondition", searchCondition);
			paramMap.put("searchKeyword", searchKeyword);
			List<NoticeVO> searchList = nService.searchListByKeyword(paramMap, currentPage);
			int totalCount = nService.getTotalCount(paramMap);
			int boardLimit = 10;
			int maxPage = 0;
			if(totalCount % boardLimit !=0) {
				maxPage = totalCount /boardLimit +1;
			}else {
				maxPage = totalCount /boardLimit;
			}
			int naviLimit = 5;
			
			int startNavi = ((currentPage-1)/naviLimit)*naviLimit+1;
			int endNavi = (startNavi-1)+naviLimit;
			if(endNavi > maxPage ) {
				endNavi = maxPage;
			}
			model.addAttribute("maxPage",maxPage);
			model.addAttribute("startNavi",startNavi);
			model.addAttribute("endNavi",endNavi);
			model.addAttribute("seachList", searchList);
			model.addAttribute("searchCondition",searchCondition);
			model.addAttribute("searchKeyword",searchKeyword);
			return "notice/search";
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
		}

	@RequestMapping(value="/notice/detail", method=RequestMethod.GET)
	public String showNoticeDetatil(
			@RequestParam("noticeNo") int noticeNo
			,Model model) {
		try {
			NoticeVO notice = nService.selectOneByNo(noticeNo);
			model.addAttribute("notice", notice);
			return "notice/detail";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
 }
