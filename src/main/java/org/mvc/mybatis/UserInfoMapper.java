package org.mvc.mybatis;

import java.util.List;

import org.mvc.bean.CoachInfoDTO;
import org.mvc.bean.UserInfoDTO;

public interface UserInfoMapper {

	//아이디 중복 확인
	//public List<UserInfoDTO> getList(String id);
	
	//회원가입 정보 저장
	public int insertUserInfo(UserInfoDTO dto);
	
	//회원 가입일자
	//public List<UserInfoDTO> getList( regdate);
	
	//회원 로그인 하기. 그러려면 id 와 pw 를 데이터와 비교해야 한다
	public int getUserInfo(UserInfoDTO dto);
	
	// 카카오 로그인(카카오에서 가져온 정보 DB에 저장)
	public int kakaoInsert(UserInfoDTO dto);
	
	// 카카오로 로그인한 사용자의 정보가 DB에 저장돼있는지 확인
	public int kakaoCheck(UserInfoDTO dto);
	
	// 네이버 로그인(네이버에서 가져온 정보 DB에 저장)
	public int naverInsert(UserInfoDTO dto);
	
	// 네이버로 로그인한 사용자의 정보가 DB에 저장돼있는지 확인
	public int naverCheck(UserInfoDTO dto);
	
	// 코치 로그인
	public int coachCheck(CoachInfoDTO coachDTO);
}