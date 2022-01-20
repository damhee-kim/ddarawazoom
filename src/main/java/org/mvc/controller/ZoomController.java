package org.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mvc.bean.CoachInfoDTO;
import org.mvc.bean.FileInfo;
import org.mvc.bean.LikeDTO;
import org.mvc.bean.ReviewDTO;
import org.mvc.bean.ZoomDTO;
import org.mvc.service.MyRoomService;
import org.mvc.service.ZoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Controller
@RequestMapping("/ddarawazoom") 
public class ZoomController {
	
	@Autowired
	private ZoomService service;
	
	@Autowired
	private MyRoomService myService;
	
	@Autowired
	private FileInfo fileInfo;   
	
	// ===== zoom강의 메인(리스트) ===== //
	@RequestMapping("zoom")
	public String main(Model model, HttpServletRequest request) {
		log.info(" -----CT-----> Main ");
		String pageNum= request.getParameter("pageNum");	
		if (pageNum == null){	
			pageNum = "1";		
		}
		
		int pageSize = 9; 		
		int currentPage = Integer.parseInt(pageNum); 
		int firstRownum = (currentPage-1)*pageSize + 1;	
		int lastRownum = currentPage*pageSize;		
		int number = 0;
		
		int pageBlock = 5;	
		int contentCount = service.zoomCount();	
		int totalPage;	
		int startPage;	
		int endPage;	
		
		
		number = contentCount-(currentPage-1)*pageSize;
		
		totalPage = contentCount/pageSize + (contentCount%pageSize == 0 ? 0 : 1);
		startPage = (currentPage/pageBlock)*pageBlock + 1;
		endPage = startPage + pageBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		if (contentCount > 0){
			model.addAttribute("contentCount", contentCount);
			model.addAttribute("list", service.zoomList(firstRownum, lastRownum));  
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("number", number);
			model.addAttribute("pageNum", pageNum);
		} else {
			model.addAttribute("contentCount", 0);
		}
		return "/zoom/class/zclass";
	}
	
	// ===== 조회수 더하기(redirect zclasscontent) ===== //
	@RequestMapping("zoomReadcount")
	public String zoomReadcount(int num , RedirectAttributes rttr) {
		log.info(" -----CT-----> Readcount ");
		service.zoomReadcount(num);
		rttr.addAttribute("num" , num);
		return "redirect:/ddarawazoom/zclasscontent";   
	}
	
	// ===== zoom강의별 내용화면 ===== //
	@RequestMapping("/zclasscontent")
	public String zclasscontent(ZoomDTO dto , int num , Model model , HttpSession session , HttpServletRequest request) { 
		log.info(" -----CT-----> zoomClassContent ");
		
		String id = (String)session.getAttribute("id");
		
		// 임시 멤버 아이디
		id = "kimcoach";
			
		model.addAttribute("result" , myService.likeCheck(id, num));
		
		log.info("=====log=="+ myService.likeCheck(id, num));

		
		model.addAttribute("ZoomDTO" , service.zoomContent(num)); 
		
		String pageNum= request.getParameter("pageNum");	
		if (pageNum == null){	
			pageNum = "1";		
		}
		
		int pageSize = 7;		
		int currentPage = Integer.parseInt(pageNum); 
		int firstRownum = (currentPage-1)*pageSize + 1;	
		int lastRownum = currentPage*pageSize;		
		int number = 0;
		
		int pageBlock = 5;	
		int contentCount = service.reCount();	
		int totalPage;	
		int startPage;	
		int endPage;	
		
		
		number =contentCount-(currentPage-1)*pageSize;
		
		totalPage = contentCount/pageSize + (contentCount%pageSize == 0 ? 0 : 1);
		startPage = (currentPage/pageBlock)*pageBlock + 1;
		endPage = startPage + pageBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		if (contentCount > 0){
			model.addAttribute("contentCount", contentCount);
			model.addAttribute("reList", service.reList(firstRownum, lastRownum));  
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("number", number);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("intro" , dto.getIntro());
		} else {
			model.addAttribute("contentCount", 0);
		}
		return "/zoom/class/zclasscontent";   
	}
	
	// ===== zoom 강의등록 ===== //
	@RequestMapping("/zwriteForm")
	public String zwriteForm(HttpSession session , CoachInfoDTO dto , Model model) {
		log.info(" -----CT-----> writeForm ");	
		
		String c_id = (String)session.getAttribute("c_id");
		c_id = "kimcoach";
		model.addAttribute("c_id" , c_id);
		
		return "/zoom/class/zwriteForm"; 
	}
	
	@RequestMapping("/zwritePro")
	public String zwritePro(ZoomDTO dto , Model model) {
		log.info(" -----CT-----> writePro ");
		log.info("dto=" + dto);
		
		model.addAttribute("result" , service.zoomInsert(dto));
		return "/zoom/class/zwritePro"; 
	}
	
	@RequestMapping("re_writeForm")
	public String re_writeForm(ZoomDTO dto , Model model) {
		
		model.addAttribute("title" , dto.getTitle()); 
		model.addAttribute("class_num" , dto.getNum());
		
		log.info("title=" , dto.getTitle());
		log.info("class_num=" , dto.getNum());
		
	
		return "/zoom/class/zclasscontent"; 
	}
	
	@RequestMapping("re_writePro")
	public String re_writePro(ReviewDTO dto , Model model) {
		model.addAttribute("num" , dto.getClass_num());
		model.addAttribute("result" , service.reInsert(dto));
		return "/zoom/class/re_writePro";
	}
	
	// ===== 이미지 업데이트 ===== //
	@RequestMapping("/imgUpdate")
	public String imgUpdate(ZoomDTO dto , Model model) {
		log.info(" -----CT-----> imgUpdate ");
		model.addAttribute("img", dto.getImg());
		model.addAttribute("num", dto.getNum());
		model.addAttribute("intro", dto.getIntro());
		
		log.info("img=" + dto.getImg());
		log.info("intro=" + dto.getIntro());
		log.info("num=" + dto.getNum());
		
		return "/zoom/class/imgUpdate";
	}
	
	@RequestMapping("/imgUpdatePro")
	public String imgUpdatePro(ZoomDTO dto , MultipartFile save, Model model) {
		log.info(" -----CT-----> imgUpdatePro ");
		
		log.info("img=" + dto.getImg());
		log.info("intro=" + dto.getIntro());
		log.info("num=" + dto.getNum());
		
		String fileName = fileInfo.classImgUpload(save, dto.getNum()); 	
		
		log.info("fileName=" , fileName);
		if(fileName != null) {
			dto.setImg(fileName);
			model.addAttribute("result" , service.imgUpdate(dto)); 
		}
		model.addAttribute("num" , dto.getNum());
		return "/zoom/class/imgUpdatePro";   
	}
		
	// ===== zoom강의 삭제 ===== //
	@RequestMapping("/zdeleteForm")
	public String zdeleteForm(@ModelAttribute("num") int num) {
		log.info(" -----CT-----> deleteForm ");
		return "/zoom/class/zdeleteForm";
	}
	
	@RequestMapping("zdeletePro")
	public String zdeletePro(int num , Model model) {
		log.info(" -----CT-----> deletePro ");
		model.addAttribute("result" , service.zoomDelete(num));
		return "/zoom/class/zdeletePro";
	}
	
	// ===== zoom강의 수정 ===== //
	@RequestMapping("zupdateForm")
	public String zupdateForm(int num , ZoomDTO dto , Model model) {
		log.info(" -----CT-----> UpdateForm ");
		log.info("dto" + dto);
		model.addAttribute("ZoomDTO" , service.zoomContent(num));
		return "/zoom/class/zupdateForm"; 
	}
	
	@RequestMapping("zupdatePro")
    public String zupdatePro(ZoomDTO dto , Model model) {
        log.info(" -----CT-----> updatePro "); 
        log.info("dto=" + dto);
          
        	model.addAttribute("result" , service.zoomUpdate(dto));
			return "/zoom/class/zupdatePro"; 
		}
	}	