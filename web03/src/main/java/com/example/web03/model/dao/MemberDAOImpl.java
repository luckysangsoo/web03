package com.example.web03.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.web03.model.dto.MemberVO;

//스프링 빈-스프링에서 라이프 사이클을 관리해주는 bean
//현재 클래스를 스프링 빈(bean)으로 만들어주는 어노테이션
@Repository
public class MemberDAOImpl implements MemberDAO {
// SqlSession 객체를 주입(Injection) 시키는 어노테이션
//
	@Inject
	SqlSession sqlSession;
	
	@Override
	public String getTime() {
		
		return sqlSession.selectOne("getTime");
	}

	@Override
	public int insertMember(MemberVO vo) {
    //mysql session close() 작업은 하지 않음(자동으로 처리됨)
	//커밋하지 앟음(auto commit)
		try {
			return sqlSession.insert("inserMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<MemberVO> memberList() {
		List<MemberVO> list = null;
		try {
			list=sqlSession.selectList("memberList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberVO memberInfo(String userid) {
		MemberVO vo = null;
		try {
			vo=sqlSession.selectOne("memberInfo", userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean pwdCheck(String userid, String userpw) {
		int result=0;
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("userid", userid);
			map.put("userpw", userpw);
			// mybatis에는 1개의 매개변수만 전달할 수 있다.
			//전달 할 값이 여러개 일떄는 Map 또는  dto를 사용해야 한다.
			result = sqlSession.selectOne("pwdCheck", map);
			if(result==1) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
