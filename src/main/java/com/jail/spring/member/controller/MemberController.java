package com.jail.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jail.spring.member.domain.MemberVO;
import com.jail.spring.member.service.MemberService;
import com.jail.spring.member.service.impl.MemberServiceImpl;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	
	@RequestMapping(value="/main")
	public String showMain(HttpServletRequest request) {
		//request.getRequestDispatcher("/WEB-INF/views/main.jsp")
		//.forward(request, response);
		//아래시작코드2개 method.get,post 연습필요함!!
		return "main";
	}
	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String memberInsertForm() {
		return "member/insert";
	}
	
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	//HttpServletRequest임포트할때 콘스하고 맨아래로내린다음 거기걸로해야 나옴!!복습할때 확인!
	public String memberInsert( //void를 String으로 바꿔줌!
			@RequestParam("memberId")String memberId
			,@RequestParam("memberPw") String memberPw
			,@RequestParam("memberName") String memberName
			,@RequestParam("memberAge") int memberAge
			,@RequestParam("memberGender") String memberGender
			,@RequestParam("memberEmail") String memberEmail
			,@RequestParam("memberPhone") String memberPhone
			,@RequestParam("memberAddress") String memberAddress
			,HttpServletRequest request, HttpServletResponse response) {
		MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress);	
		//?아래코드는???
		//MemberServiceImpl mService = new MemberServiceImpl();
		//아래코드 임포트주의!!
		//MemberService mService = new MemberServiceImpl();
		int result = mService.insertMember(member);
			if(result > 0) {
				//성공시 메인페이지(로그인페이지로이동!)
				//response.sendRedirect("/"); ??코드바뀜??
				return "redirect:/";
			}else {
				//실패시 에러페이지로 이동
				return "common/error";
			}
		//아래 옮기기 성공!!
		//String memberId = request.getParameter("memberId");
		//String memberPw = request.getParameter("memberPw");
		//String memberNmae = request.getParameter("memberName");
		//int memberAge = Integer.parseInt(request.getParameter("memberAge"));
		//String memberGender = request.getParameter("memberGender");
		//String memberEmail = request.getParameter("memberEmail");
		//String memberPhone = request.getParameter("memberPhone");
		//String memberAddress = request.getParameter("memberAddress");
	}
	@RequestMapping(value="/member/login", method=RequestMethod.POST) 
	public String memberLogin(
			@RequestParam("memberId") String memberId
			,@RequestParam("memberPw") String memberPw
			,HttpSession session ,Model model) {
		//try 연습!!
		try {
			MemberVO member = new MemberVO(memberId, memberPw);
			member = mService.selectOneByLogin(member);
			if(member != null) {
				// 로그인 성공 하면 세션에 정보 저장!
				//밑에 코드 다시 복습!!
				//HttpSession session = request.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				System.out.println("테스트");
				//
				return "redirect:/";
			}else {
				// 로그인 실패시 에러페이지로 이동
				model.addAttribute("errorMsg","존재하지 않은 정보입니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}	
	}
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	//마이페이지
	@RequestMapping(value="/member/detail", method=RequestMethod.GET)
	public String memberMyPage(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			//selectOneById형변환에러 memberService에서 String으로 바꿔줌!
			MemberVO member = mService.selectOneById(memberId); //The method selectOneById(MemberVO) in the type MemberService is not applicable for the arguments (String)
			if(member != null) {
				model.addAttribute("member",member);
				
				return "member/detail";
				
			}else {
				//에러페이지
				model.addAttribute("errorMsg","존재하지않은 정보입니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
		
	}
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String memberDelete(HttpSession session, Model model) {
		//회원탈퇴 진행하고 로그아웃해서 메인으로 이동하도록해야함
		try {
			String memberId = (String)session.getAttribute("memberId");
			int result = mService.deleteMember(memberId);
			if(result > 0) {
				//로그아웃
				return "redirect:/member/logout";
			}else {
				//에러페이지이동
				model.addAttribute("errorMsg","서비스가완료되지않았습니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/member/update", method=RequestMethod.GET)
	public String memberUpdateForm(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = mService.selectOneById(memberId);
			if(member != null) {
				model.addAttribute("member",member);
				return "member/update";
			}else {
				//에러페이지
				model.addAttribute("errorMsg","존재하지 않는 정보입니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public String memberUpdate(@RequestParam("memberId")String memberId
			,@RequestParam("memberPw") String memberPw
			,@RequestParam("memberEmail") String memberEmail
			,@RequestParam("memberPhone") String memberPhone
			,@RequestParam("memberAddress") String memberAddress
			, Model model) {
		try {
			MemberVO member = new MemberVO(memberId, memberPw, memberEmail, memberPhone, memberAddress);
			int result = mService.updateMember(member);
			if(result > 0) {
				return "redirect:/member/detail";
			}else {
				model.addAttribute("errorMsg","서비스가 완료되지 않았습니다");
				return "common/error";
			}
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
}	
