package com.example.web03.model.dao;

import java.util.List;

import com.example.web03.model.dto.MemberVO;

// 유연한 개발을 위해 dao를 인터페이스로 선언
public interface MemberDAO {
	public String getTime();
	public int insertMember(MemberVO vo) throws Exception;
	public List<MemberVO> memberList();
	public MemberVO memberInfo(String userid);
    public boolean pwdCheck(String userid,String userpw);
	
}
