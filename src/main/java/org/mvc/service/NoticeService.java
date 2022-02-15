package org.mvc.service;

import java.util.List;

import org.mvc.bean.CoachInfoDTO;
import org.mvc.bean.NoticeDTO;
import org.mvc.bean.Notice_CDTO;
import org.mvc.bean.UserInfoDTO;

public interface NoticeService {

	// 유저, 코치 정보
	public UserInfoDTO getUserInfo(String id);
		
	public CoachInfoDTO getCoachInfo(String c_id);
	
	// 공지 글 가져오기
	public List<NoticeDTO> noticeList(int startRow, int endRow);

	// 공지 글 수
	public int noticeCount();
	
	// 공지 글 작성
	public int noticeWrite(NoticeDTO noticeDTO);
	
	// 관련 공지 글 출력
	public NoticeDTO getNotice(NoticeDTO noticeDTO);
	
	// 공지 글 수정
	public int noticeUpdate(NoticeDTO noticeDTO);
	
	// 공지 글 삭제
	public int noticeDelete(int num);
	
	// 공지 조회수
	public int noticeViewCount(int num);
	
	// 공지 댓글 작성
	public int commentWrite(Notice_CDTO notice_CDTO);
	
	// 관련 글 공지 댓글 수
	public int commentCount(int num);
	
	// 공지 댓글 리스트
	public List<Notice_CDTO> getCommentList(int num, int startRow, int endRow);
		
	// 공지 댓글 삭제
	public int deletedChange(int c_num);
	
	// 관리자 댓글 경고
	public int commentDeletedChange(int c_num);
	
	// 관리자 댓글 경고 취소
	public int commentDeletedCancell(int c_num);
	
	// 공지 댓글 가져오기
	public Notice_CDTO getComment(int c_num);
	
	// 공지 댓글 수정
	public int commentUpdate(Notice_CDTO notice_CDTO);
	
	// 대댓글 작성
	public int insertReComment(Notice_CDTO notice_CDTO);
	
	// 관리자 관리 - 모든 댓글
	public List<Notice_CDTO> getAllComment(int startRow, int endRow);
	
	// 관리자 관리 - 댓글 수
	public int getcommentCount();
	
	// 공지사항 검색 기능
	public List<NoticeDTO> searchNoticeList(String category, String input, int startRow, int endRow);
	
	// 검색 결과 수
	public int searchCount(String category, String input);
	
	// 공지사항 댓글 검색 리스트
	public List<Notice_CDTO> searchCommentList(String category, String input, int startRow, int endRow);	
	
	// 댓글 검색 결과 수
	public int searchCommentCount(String category, String input);
}
