package com.jail.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jail.spring.member.controller.dto.JoinRequest;
import com.jail.spring.member.controller.dto.LoginRequest;
import com.jail.spring.member.controller.dto.ModifyRequest;
import com.jail.spring.member.domain.MemberVO;
import com.jail.spring.member.service.MemberService;

@Controller
@RequestMapping("/member")
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
	//로그인! dto 과정?에서 밑에코드주석처리!
	//@RequestMapping(value="/login", method=RequestMethod.POST) 
	@PostMapping("/login")
	public String memberLogin(
			//@RequestParam("memberId") String memberId
			//,@RequestParam("memberPw") String memberPw
			@ModelAttribute LoginRequest member
			,HttpSession session ,Model model) {
		//try 연습!!
		try {
			//LoginRequest member = new LoginRequest(memberId, memberPw);
			MemberVO member1 = mService.selectOneByLogin(member);
			if(member1 != null) {
				// 로그인 성공 하면 세션에 정보 저장!
				//HttpSession session = request.getSession();
				session.setAttribute("memberId", member1.getMemberId());
				session.setAttribute("memberName", member1.getMemberName());
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
	//회원로그아웃
	//@RequestMapping(value="/logout", method=RequestMethod.GET)
	@GetMapping("/logout")
	public String memberLogout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	//회원가입 페이지 이동
	//@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String memberInsertForm() {
		return "member/insert";
	}
	//회원가입
	//@RequestMapping(value="/insert", method=RequestMethod.POST)
	@PostMapping("/insert")
	public String memberInsert(
			@ModelAttribute JoinRequest member
			,HttpServletRequest request, HttpServletResponse response) {
		//JoinRequest member = new JoinRequest(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress);	
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
		
	}
	//마이페이지(회원상세페이지)
	//@RequestMapping(value="/detail", method=RequestMethod.GET)
	@GetMapping("/detail")
	public String memberMyPage(HttpSession session, Model model) {
		try {
			//세션에서 아이디 가져오기
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
	//회원탈퇴
	//@RequestMapping(value="/delete", method=RequestMethod.GET)
	@GetMapping("/delete")
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
	//회원정보 수정페이지로 이동
	//@RequestMapping(value="/update", method=RequestMethod.GET)
	@GetMapping("/update")
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
	//회원정보수정
	//@RequestMapping(value="/update", method=RequestMethod.POST)
	@PostMapping("/update")
	public String memberUpdate(
			//	@RequestParam("memberId")String memberId
			//,@RequestParam("memberPw") String memberPw
			//,@RequestParam("memberEmail") String memberEmail
			//,@RequestParam("memberPhone") String memberPhone
			//,@RequestParam("memberAddress") String memberAddress
			@ModelAttribute ModifyRequest member
			, Model model) {
		try {
			//ModifyRequest member = new ModifyRequest(memberId, memberPw, memberEmail, memberPhone, memberAddress);
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
