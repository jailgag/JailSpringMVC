package com.jail.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jail.spring.common.FileUtil;
import com.jail.spring.common.PageUtil;
import com.jail.spring.notice.controller.dto.NoticeAddRequest;
import com.jail.spring.notice.controller.dto.NoticeModifyRequest;
import com.jail.spring.notice.domain.NoticeVO;
import com.jail.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	
	private NoticeService nService;
	//1추가
	private FileUtil fileUtil;
	//2추가
	private PageUtil pageUtil;
	
	@Autowired
	public NoticeController(NoticeService nService,FileUtil fileUtil,PageUtil pageUtil) {
		this.nService = nService;
		this.fileUtil = fileUtil;
		this.pageUtil = pageUtil;
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.GET)
	public String showNoticeForm() {
		return "notice/insert";
	}
	//수정작업했음(02/26)
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(
			@ModelAttribute NoticeAddRequest notice
			,@RequestParam("uploadFile") MultipartFile uploadFile
			,HttpSession session
			,Model model) { 
			try {
				String noticeWriter = session.getAttribute("memberId") != null 
						?(String)session.getAttribute("memberId") : "anonymous";
				String noticeFilename = null;
				String noticeFileRename = null;
				String noticeFilepath = null;
				//Map<Stirng, String> fileInfo = fileUtil.saveFile(uploadFile, session, noticeFilepath);
				//notice
				if(uploadFile != null && !uploadFile.getOriginalFilename().isBlank()) {
					Map<String,String> fileInfo
					=fileUtil.saveFile(uploadFile,session,"notice");
					noticeFilename = fileInfo.get("nFilename");
					noticeFileRename = fileInfo.get("nFileRename");
					noticeFilepath = fileInfo.get("nFilepath");
					notice.setNoticeWriter(noticeWriter);
					notice.setNoticeFilename(noticeFilename);
					notice.setNoticeFileRename(noticeFileRename);
					notice.setNoticeFilepath(noticeFilepath);
				}
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
 	public String updateNotice(
 			//@RequestParam("noticeNo")int noticeNo
 			//,@RequestParam("noticeSubject") String noticeSubject
 			//,@RequestParam("noticeContent") String noticeContent
 			@ModelAttribute NoticeModifyRequest notice
 			,@RequestParam("reloadFile") MultipartFile reloadFile
 			,HttpSession session
 			,Model model) {
 			try {
				//String noticeFilename = reloadFile.getOriginalFilename();
 				String noticeFilename= null;
 				String noticeFileRename = null;
 				String noticeFilepath = null;
 				if(reloadFile != null && !reloadFile.getOriginalFilename().isBlank()) {
 					//Map<String, String> fileInfo 
 					//= fileUtil.saveFile(reloadFile, session,"notice");
 					//noticeFilename = fileInfo.get("noticeFilename");
 					//if(noticeFilename != null) {
 					//	noticeFileRename = fileInfo.get("noticeFileRename");
 					//	noticeFilepath = fileInfo.get("noticeFilepath");
 				//	}
 					Map<String, String> fileInfo
 					 = fileUtil.saveFile(reloadFile, session, "notice");
 					noticeFilename = fileInfo.get("nFilename");
 					noticeFileRename = fileInfo.get("nFileRename");
 					noticeFilepath = fileInfo.get("nfilepath");
 					notice.setNoticeFilename(noticeFilename);
 					notice.setNoticeFileRename(noticeFileRename);
 					notice.setNoticeFilepath(noticeFilepath);
 				}
 				int result =  nService.updateNotice(notice);
 				if(result > 0) {
 					return "redirect:/notice/detail?noticeNo="+notice.getNoticeNo();
 					
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
			Map<String, Integer> pageInfo =  pageUtil.generatePageInfo(totalCount, currentPage);
			if(!nList.isEmpty()) {
				model.addAttribute("maxPage",pageInfo.get("maxPage"));
				model.addAttribute("startNavi",pageInfo.get("startNavi"));
				model.addAttribute("endNavi",pageInfo.get("endNavi"));
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
			Map<String, Integer> pageInfo = pageUtil.generatePageInfo(totalCount, currentPage);
//			int boardLimit = 10;
//			int maxPage = 0;
//			if(totalCount % boardLimit !=0) {
//				maxPage = totalCount /boardLimit +1;
//			}else {
//				maxPage = totalCount /boardLimit;
//			}
//			int naviLimit = 5;
//			
//			int startNavi = ((currentPage-1)/naviLimit)*naviLimit+1;
//			int endNavi = (startNavi-1)+naviLimit;
//			if(endNavi > maxPage ) {
//				endNavi = maxPage;
//			}
			model.addAttribute("maxPage",pageInfo.get("maxPage"));
			model.addAttribute("startNavi",pageInfo.get("startNavi"));
			model.addAttribute("endNavi",pageInfo.get("endNavi"));
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
