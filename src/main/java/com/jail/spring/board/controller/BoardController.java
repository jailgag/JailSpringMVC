package com.jail.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jail.spring.board.controller.dto.BoardAddRequest;
import com.jail.spring.board.controller.dto.BoardModifyRequest;
import com.jail.spring.board.domain.BoardVO;
import com.jail.spring.board.service.BoardService;
import com.jail.spring.common.FileUtil;

@Controller
public class BoardController {
	
	private BoardService bService;
	
	private FileUtil fileUtil;
	

	@Autowired
	public BoardController(BoardService bService, FileUtil fileUtil) {
		this.bService = bService;
		this.fileUtil = fileUtil;
	}
	//페이징처리 시작!아래 RequestParam("page"~~부터!!
	//value="page",defaultValue="1")추가해줬음!
	
	@GetMapping("/board/list")
	public String showBoardList(
			@RequestParam(value="page",defaultValue="1") int currentPage 
 			,Model model) {
		try {
			List<BoardVO> bList = bService.selectBoardList(currentPage);
			model.addAttribute("bList",bList);
			return "board/list";
		} catch (Exception e) {
			// TODO: handle exception
			return "common/error";
		}
		
	}
	
	@GetMapping("/board/insert")
	public String showBoardInsertForm() {
		return "board/insert";
	}
	
	@PostMapping("/board/insert")
	public String insertBoard(@ModelAttribute BoardAddRequest board
			,@RequestParam("uploadFile") MultipartFile uploadFile
			,HttpSession session
			,Model model) {
		try {
			if(session.getAttribute("memberId") != null) {
				board.setBoardWriter((String)session.getAttribute("memberId"));
			}else {
				model.addAttribute("errorMsg","로그인이 필요합니다!!!");
				return "common/error";
			}
			if(uploadFile != null && !uploadFile.getOriginalFilename().isBlank()) {
				Map<String, String> fileInfo = fileUtil.saveFile(uploadFile, session,"board");
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));
			}
			int result = bService.insertBoard(board);
			return"redirect:/board/list";
		} catch (Exception e) {
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
	
	@GetMapping("/board/detail/{boardNo}")
	public String showBoardDetail(@PathVariable("boardNo") int boardNo
				,Model model) {
			try {
				BoardVO board = bService.selectOneByNo(boardNo);
				model.addAttribute("board",board);
				return "board/detail";
			} catch (Exception e) {
				model.addAttribute("errorMsg",e.getMessage());
				return "common/error";
			}
	}
	
	//수정하기!!
	
	@GetMapping("/board/modify/{boardNo}")
	public String showBoardModifyForm(@PathVariable int boardNo
			,Model model
			) {
		try {
			BoardVO board = bService.selectOneByNo(boardNo);
			model.addAttribute("board",board);
			return "board/modify";
		} catch (Exception e) {
			// TODO: handle exception
			//이아래코드e.print~는 무슨역할인가..
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	//수정!!로그인상태에서는 HttpSession, session이 필요함!
	//jsp에추가!<input type="hidden" name="boardWriter" value="${board.boardWriter }">
	
	@PostMapping("/board/modify")
	public String modifyBoard(@ModelAttribute BoardModifyRequest board
			,@RequestParam("reloadFile") MultipartFile reloadFile
			,HttpSession session
			,Model model) {
		try {
			//여기if는 로그인체크
			if(session.getAttribute("memberId") == null) {
				model.addAttribute("errorMsg","로그인이 필요합니다!!");
				return "common/error";
			}
			String memberId = (String)session.getAttribute("memberId");
			if(!memberId.equals(board.getBoardWriter())) {
				model.addAttribute("errorMsg","자신이 작성한 글만 수정할수 있습니다!!");
				return "common/error";
			}
			//업로드 값을 셋팅해주는코드아래
			if(reloadFile != null && !reloadFile.getOriginalFilename().isBlank()) {
				Map<String, String> fileInfo = fileUtil.saveFile(reloadFile, session, "board");
				//()안에 bFilename으로!common에FileUtill클래스에있는 
				//result.put(prefix+"Filename", noticeFilename);의값??
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));
			}
			int result = bService.updateBoard(board);
			//아래코드 핸들러 맵핑!!
			return "redirect:/board/detail/"+board.getBoardNo();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	
	//@RequestParam
	
	@GetMapping("/board/delete/{boardNo}")
	public String deleteBoard(@PathVariable int boardNo
			,Model model) {
		try {
			int result = bService.deleteBoard(boardNo);
			return "redirect:/board/list";
		} catch (Exception e) {
			// TODO: handle exception
			//쿼리문에서 update문으로 바꿨는데응용할수있는부분이
			//만약게시판에서 신고가들어왔다?근데 삭제보다는 블라인드처리를한다?
			//오해가 풀려 다시 올려주세요 할때 다시쓰는것보다 살려주는?그래서 yn으로표시?n으로 표기됨!
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
}
