package com.example.web03.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.web03.model.dao.MemberDAO;
import com.example.web03.model.dto.MemberVO;

//현재 클래스를 스프링 빈(컨트롤러)로 등록시킴
//스프링에서 관리하는 객체
// MemberController con = new MemberController();
// con.getTime();

@Controller
public class MemberController {
	// MemberDAO 객체를 스프링에서 만들어서 주입시킴. new를 해주는 역활
	@Inject
	MemberDAO memberDao;
	// http://localhost:8080/web03/member/getTime
	@RequestMapping("/member/getTime")
	public void getTime(Model model){
		String time=memberDao.getTime();
		System.out.println("현재 시각:" + time);
		//void의 경우 method 이름과 동일한 페이지로 이동
		// getTime.jsp
		//모델애 변수 추가
		model.addAttribute("time", time);
	}
	
	@RequestMapping("/member/memberInsert")
	public String memberForm(@ModelAttribute MemberVO vo, Model model,
			HttpSession session) throws Exception{
		// /member/memberForm.jsp로 리다이렉트
		//폼에서 입력한 값이 vo 에 저장 됨
		if(vo.getUserid()==null){
			return "member/memberForm";
		}else{
			// dao에 insert 요청
			System.out.println(vo.getUserid());
			System.out.println(vo.getUserpw());
			System.out.println(vo.getUsername());
			System.out.println(vo.getEmail());
			int result = memberDao.insertMember(vo);
			if (result > 0) {
				System.out.println("insert 성공");
				session.setAttribute("userid", vo.getUserid());
				session.setAttribute("username", vo.getUsername());
				model.addAttribute("message", "회원가입 처리가 완료 되었습니다.");
				// main.jsp 로 이동
				return "/member/main"; 
			}
			else {
				System.out.println("insert 실패");
				model.addAttribute("message", "회원등록 과정에서 에러가 발생 했습니다.");
				// memberForm.jsp 로 이동
				return "/member/memberForm";
			}
		}
	}
	
	@RequestMapping("/member/memberList")
	public void memberList(Model model){
		// dao 호출
		// 모델에 저장
		model.addAttribute("list", memberDao.memberList());
		// 리턴 타입이 void 인 경우에는 JSP로 포워딩
		// /WEB-INF/views/member/memberList.jsp 로 포워딩
	}
	
	// http://localhost:8080/member/memberInfo?userid=shim
	@RequestMapping("/member/memberInfo")
	public void memberInfo(String userid, Model model){
		//dao호출, 모델에 저장
		model.addAttribute("dto", memberDao.memberInfo(userid));
		// /WEB-INF/views/member/memberInfo.jsp 로 포워딩
	}
	
	@RequestMapping("/member/memberUpdate")
	public void memberUpdate(@ModelAttribute MemberVO vo){
		//폼에 입력한 데이터들은 ModelAttribute에 저장 됨.
		//비밀번호가 정확한지 확인
		boolean result=memberDao.pwdCheck(vo.getUserid(), vo.getUserpw());
		if(result){
			System.out.println("맞는 비번입니다.");
		}else{
			System.out.println("비밀번호 틀림");
		}
		//비밀번호가 맞으면 update => 리스트로 이동
		// 비밀번호가 틀리면 memberInfo 로 돌아감.
	}
	
	/*@RequestMapping("/member/memberInsert")
	public void memberInsert(@ModelAttribute MemoVO vo, Model model){
		//폼에서 입력한 값이 vo 에 저장 됨
	}*/
	
}
