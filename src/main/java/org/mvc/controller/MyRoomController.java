package org.mvc.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mvc.bean.BodyProfileDTO;
import org.mvc.bean.FileInfo;
import org.mvc.bean.LikeDTO;
import org.mvc.bean.MyProfileDTO;
import org.mvc.bean.ReviewDTO;
import org.mvc.bean.ScheduleDTO;
import org.mvc.bean.UserInfoDTO;
import org.mvc.bean.ZoomDTO;
import org.mvc.service.MyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/myroom")
public class MyRoomController {
	
	@Autowired
	private MyRoomService service;
	
	@Autowired
	private FileInfo fileInfo;
  
//  =========== 마이룸 메인화면 ===========  //	
	@RequestMapping()
	public String list() {
		log.info("	-----CT----->myroomMain");
		
		return "/myroom/main";
	}


	
//  =========== 멤버 정보 관련 코드 시작 ===========  //		
	@RequestMapping("/userInfo")
	public String userInfo(HttpSession session, Model model) {
		log.info("	-----CT----->userInfo");
		
		String id = (String)session.getAttribute("id");
		
		//임시 멤버 아이디
		id = "dam";
		model.addAttribute("userInfo", service.getUserInfo(id));
		
		return "/myroom/userinfo/userinfo";
	}
	
	@RequestMapping("/infoUpdate")
	public String userInfoUpdate(HttpSession session, Model model) {
		log.info("	-----CT----->infoUpdate");

		String id = (String)session.getAttribute("id");
		
		//임시 멤버 아이디
		id = "dam";
		model.addAttribute("userInfo", service.getUserInfo(id));
		
		return "/myroom/userinfo/infoUpdate";
	}
	
	@RequestMapping("/infoUpdateData")
	public @ResponseBody int infoUpdateData(@RequestBody UserInfoDTO userDTO, HttpSession session) {
		log.info("	-----CT----->infoUpdateData");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		
		userDTO.setId(id);
		int result = service.updateInfo(userDTO);
		
		return result;
	}
	
	@RequestMapping("/imgUpdate")
	public String imgUpdate() {
		log.info("	-----CT----->imgUpdate");
		
		return "/myroom/userinfo/imgUpdate";
	}
	
	@RequestMapping("/imgUpdatePro")
	public @ResponseBody int imgUpdatePro(UserInfoDTO userDTO, MultipartFile save, HttpSession session) {
		log.info("	-----CT----->imgUpdatePro");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		
		userDTO.setId(id);
		
		int result = 0;
		
		String file = fileInfo.imgUpload(save, id);
		if(file != null) {
			userDTO.setImg(file);
			result = service.updateImg(userDTO);
		}
		return result;
	}
//=========== 멤버 정보 관련 코드 종료 ===========  //		
	


//=========== 멤버 일정 관련 코드 시작 ===========  //		
	@RequestMapping("/class")
	public String memberClass() {
		log.info("	-----CT----->class");

		return "/myroom/class/class";
	}
	
	@RequestMapping("/getClass")
	public @ResponseBody ArrayList<ScheduleDTO> getClass(HttpSession session, Model model){
		log.info("	-----CT----->getClass");

		String m_id = (String)session.getAttribute("m_id");
		
		//  임시 멤버 아이디
		m_id = "dam";
		
		return service.getAllClass(m_id);
	}
//===========  멤버 일정 관련 코드 끝 ===========  //	
		
		
	
//  =========== 바디프로필 관련 코드 시작 ===========  //		
	@RequestMapping("/bodyprofile")
	public String bodyprofile(UserInfoDTO userDTO, HttpSession session, Model model) { 
		log.info("	-----CT----->/bodypfile/bodyprofile");

		String b_id = (String)session.getAttribute("b_id");
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		model.addAttribute("myProfileDTO", service.getMyProfile(b_id));
		model.addAttribute("bodyProfileDTO", service.getBodyProfile(b_id));
		model.addAttribute("bodyList", service.bodyList(b_id));
		model.addAttribute("number", 1);
		return "/myroom/bodyprofile/content";
	}
	
	
	@RequestMapping("/bodyprofile/myWrite")
	public String myWrite() {
		log.info("	-----CT----->/bodypfile/myWrite");

		return "/myroom/bodyprofile/myWrite";
	}
	
	@RequestMapping("/bodyprofile/myWritePro")
	public String myWritePro(MyProfileDTO myDTO, Model model) {
		log.info("	-----CT----->/bodypfile/myWritePro");

		model.addAttribute("result", service.myWrite(myDTO));
		
		return "/myroom/bodyprofile/myWritePro";
	}
	
	@RequestMapping("/bodyprofile/myUpdate")
	public String myUpdate(HttpSession session, Model model) {
		log.info("	-----CT----->/bodypfile/myUpdate");

		String b_id = (String)session.getAttribute("b_id");
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		model.addAttribute("myProfileDTO", service.getMyProfile(b_id));
		
		return "/myroom/bodyprofile/myUpdate";
	}
	
	@RequestMapping("/bodyprofile/myUpdatePro")
	public String myUpdatePro(HttpSession session, MyProfileDTO myDTO, MultipartFile save, Model model) {		
		log.info("	-----CT----->/bodypfile/bodyUpdatePro");

		String b_id = (String)session.getAttribute("b_id");
		
		log.info("=====save"+save);
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		myDTO.setB_id(b_id);
		
		model.addAttribute("result", service.myUpdate(myDTO));
		
		return "/myroom/bodyprofile/myUpdatePro";
	}
	
	@RequestMapping("/bodyprofile/bodyWrite")
	public String bodyWrite() {
		log.info("	-----CT----->/bodypfile/bodyWrite");
		
		return "/myroom/bodyprofile/bodyWrite";
	}
	
	@RequestMapping("/bodyprofile/bodyWritePro")
	public String bodyWritePro(BodyProfileDTO bodyDTO, MultipartFile save, HttpSession session, Model model) {
		log.info("	-----CT----->/bodypfile/bodyWritePro");

		String b_id = (String)session.getAttribute("b_id");
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		bodyDTO.setB_id(b_id);
		
		String  file = fileInfo.imgUpload(save, b_id);
		if(file != null) {
			bodyDTO.setB_img(file);
			model.addAttribute("result",service.bodyWrite(bodyDTO));
		}
		return "/myroom/bodyprofile/bodyWritePro";
	}
	
	@RequestMapping("/bodyprofile/bodyUpdate")
	public String bodyUpdate(BodyProfileDTO bodyDTO, HttpSession session, Model model) {
		log.info("	-----CT----->/bodypfile/bodyUpdate");

		String b_id = (String)session.getAttribute("b_id");
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		bodyDTO.setB_id(b_id);
		
		model.addAttribute("bodyProfileDTO", service.getBodyInfo(bodyDTO));
		
		return "/myroom/bodyprofile/bodyUpdate";
	}
	
	@RequestMapping("/bodyprofile/bodyUpdatePro")
	public String bodyUpdatePro(BodyProfileDTO bodyDTO,  MultipartFile save, HttpSession session, Model model) {
		log.info("	-----CT----->/bodypfile/bodyUpdatePro");

		String b_id = (String)session.getAttribute("b_id");
		
		// 임시 멤버 아이디
		b_id = "dam";
		
		bodyDTO.setB_id(b_id);
		
		String b_num = Integer.toString(bodyDTO.getB_num()); // num  String 형변환 
		
		String  file = fileInfo.imgUpload(save, b_num);
		if(file != null) {
			bodyDTO.setB_img(file);
			model.addAttribute("result", service.bodyUpdate(bodyDTO));
		}
		
		return "/myroom/bodyprofile/bodyUpdatePro";
	}
	
	@RequestMapping("/bodyprofile/bodyDelete")
	public String bodyDelete(int b_num, Model model) {
		log.info("	-----CT----->/bodypfile/bodyDelete");

		model.addAttribute("b_num",b_num);
		
		return "/myroom/bodyprofile/bodyDelete";
	}
	
	@RequestMapping("/bodyprofile/bodyDeletePro")
	public String bodyDeletePro(int b_num, Model model) {
		log.info("	-----CT----->/bodypfile/bodyDeletePro");
		
		model.addAttribute("result",service.bodyDelete(b_num));
		
		return "/myroom/bodyprofile/bodyDeletePro";
	}

//  =========== 바디프로필 그래프  ===========  //		
	@RequestMapping("/getBodyList")
	public @ResponseBody List<BodyProfileDTO> getBodyList(Model model){
		log.info("	-----CT----->getBodyList");

		//		=========== 날짜 포맷을 mm월 dd일로 바꾼 후 view로 보내는 코드 ===========		//
		
		List<BodyProfileDTO> list = service.bodyList("dam"); // list 변수에 DB에서 가져온 값을 대입
		List<BodyProfileDTO> resultList = new ArrayList<BodyProfileDTO>(); // view로 보낼 리스트 선언
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일"); // 변경할 날짜 포맷 선언 후 생성
		for(BodyProfileDTO dto : list) { // DB에서 가져온 값을 하나씩 꺼내옴
			String date = sdf.format(dto.getB_date()); // dto안의 b_date의 포맷 변경 후 String 변수에 대입
			
			if(date.substring(4, 5).equals("0")) { // date문자열이(만약 날짜의 월)이 0으로 시작한다면
				StringBuffer dateDelete = new StringBuffer(date);
				date = dateDelete.deleteCharAt(4).toString(); // 문자열 0을 제거 (1번째 index부터 문자열을 잘라냄)
				
				if(date.substring(7, 8).equals("0")) { // 만약 날짜의 일이 0으로 시작한다면 (문자열의 3번째 index가 0이라면)
					 // 문자열을 삭제하는 함수를 사용하기 위해 StringBuffer 클래스 생성
					date = dateDelete.deleteCharAt(7).toString(); // 문자열 0을 제거 (3번째 index를 제거) 후 date 변수에 대입
				}
			}
			dto.setParse_date(date); // 위에서 변환한 날짜를 dto안에 대입
			resultList.add(dto); // view로 보낼 리스트에 dto 대입
		}
		model.addAttribute("bodyList", resultList);
		
		return resultList;
	}
//  =========== 바디프로필 관련 코드 종료 ===========  //		

	
	
//  =========== 관심 페이지 관련 코드 시작 ===========  //	
	@RequestMapping("/locker")
	public String locker() {
		log.info("	-----CT----->locker");
		
		return "/myroom/locker/locker";
	}
	
	@RequestMapping("/likeZoom")
	public String likeZoom(HttpSession session, Model model) {
		log.info("	-----CT----->likeZoom");

		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		
		model.addAttribute("ZoomDTO", service.likeZoomList(id));
		
		return "/myroom/locker/likeZoom";
	}
	
	@RequestMapping("/likeZoom_in")
	public @ResponseBody int zoomLikeWrite(HttpSession session, @RequestBody LikeDTO likeDTO) {
		log.info("	-----CT----->likeZoom_in");
		
		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		likeDTO.setId(id);
		
	
		log.info(""+likeDTO.getZoom_num());
		result = service.zoomLikeWrite(id, likeDTO.getZoom_num());
		
		return result;
	}
	
	@RequestMapping("/likeZoom_out")
	public @ResponseBody int zoomLikeDelete(HttpSession session, @RequestBody LikeDTO likeDTO) {
		log.info("	-----CT----->likeZoom_out");
		
		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		likeDTO.setId(id);
		result = service.zoomLikeDelete(id, likeDTO.getZoom_num());
		
		return result;
	}
	
	@RequestMapping("/likeVod")
	public String likeVod(HttpSession session, Model model) {
		log.info("	-----CT----->likeVod");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		
		model.addAttribute("VodDTO", service.likeVodList(id));
		
		return "/myroom/locker/likeVod";
	}
	
	@RequestMapping("/likeVod_in")
	public @ResponseBody int vodLikeWrite(HttpSession session, @RequestBody LikeDTO likeDTO) {
		log.info("	-----CT----->likeVod_in");
		
		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		id ="dam";
		
		likeDTO.setId(id);
		
		result = service.vodLikeWrite(id, likeDTO.getVod_num());
		
		return result;
	}
	
	@RequestMapping("/likeVod_out")
	public @ResponseBody int vodLikeDelete(HttpSession session, @RequestBody LikeDTO likeDTO) {
		log.info("	-----CT----->likeVod_out");
		
		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		likeDTO.setId(id);
		result = service.vodLikeDelete(id, likeDTO.getZoom_num());
		
		return result;
	}
//  =========== 관심 페이지 관련 코드 끝 ===========  //		

	
//  =========== 멤버 리뷰 관련 코드 시작 ===========  //
	@RequestMapping("/review")
	public String memberReview(ZoomDTO zoomDTO, HttpSession session, Model model, String pageNum) {
		log.info("	-----CT----->review");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		
		int pageSize = 10;
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		
		count = service.reviewCount(id);
		
		List reviewList = null;
		if(count > 0) {
			reviewList = service.getReview(zoomDTO.getNum(), id);
		}
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("count", count);
		return "/myroom/review/review";
	}
	
	@RequestMapping("/reviewUpdate")
	public String reviewUpdate(HttpSession session, Model model, ReviewDTO reviewDTO) {
		log.info("	-----CT----->coachInfoUpdate");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		reviewDTO.setId(id);
		
		model.addAttribute("review" , service.getMyReview(reviewDTO));
		
		return "/myroom/review/reviewUpdate";
	}
	
	@RequestMapping("/reviewUpdateData")
	public @ResponseBody int reviewUpdatePro(HttpSession session, @RequestBody ReviewDTO reviewDTO) {
		log.info("	-----CT----->reviewUpdateData");

		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "dam";
		reviewDTO.setId(id);
		
		result = service.updateReview(reviewDTO);
		
		return result;
	}
	
	@RequestMapping("/reviewDelete")
	public @ResponseBody int reviewDelete(HttpSession session, @RequestBody ReviewDTO reviewDTO) {
		log.info("	-----CT----->reviewDelete");

		int result = 0;
		
		String id = (String)session.getAttribute("id");
		
		//임시 멤버 아이디
		id = "dam";
		reviewDTO.setId(id);

		result = service.deleteReview(reviewDTO);
		
		return result;
	}
//  =========== 멤버 리뷰 관련 코드 끝 ===========  //		

}
	