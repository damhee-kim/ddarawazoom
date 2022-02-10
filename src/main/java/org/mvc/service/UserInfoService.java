package org.mvc.service;

import java.util.List;

import org.mvc.bean.CoachCareerDTO;
import org.mvc.bean.CoachInfoDTO;
import org.mvc.bean.UserInfoDTO;
import org.mvc.bean.ZoomDTO;
import org.mvc.controller.MainController;
import org.springframework.stereotype.Service;


public interface UserInfoService {

	//아이디 중복 확인
	public int idCheck(String id);
	
	public int c_idCheck(String c_id);
	
	// 닉네임 중복 확인
	public int nickCheck(String nick);
		
	public int c_nickCheck(String c_nick);
	
	// 아이디 찾기
	public String findId(UserInfoDTO dto);
	
	// 이메일 인증
	public int emailCheck(UserInfoDTO dto);
	
	// 비밀번호 변경
	public int updatePw(UserInfoDTO dto);
		
	//회원가입 정보 입력
	//실행이 일어나기 위해서 필요한 변수값...? 
	public int insertUserInfo(UserInfoDTO dto); 
	
	//회원 로그인 데이터 가져오기
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
	
	// 코치 회원가입
	public int coachInsert(CoachInfoDTO coachDTO);
	
	// 코치 경력 추가
	public int careerInsert(CoachCareerDTO careerDTO);
	
	// 코치 프로필 사진 등록
	public int coachImgUpload(CoachInfoDTO dto);
	
	// 코치 소개 페이지
	public List<CoachInfoDTO> getAllCoach();
	
	// 코치 정보
	public CoachInfoDTO coachInfo(String c_id);
		
	// 코치 경력
	public List<CoachCareerDTO> coachCareer(String c_id);
	
	// 등록한 강의
	public List<ZoomDTO> getCoachClass(String c_id);
}
