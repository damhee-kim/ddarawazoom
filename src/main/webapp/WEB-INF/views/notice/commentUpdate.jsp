<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<html>
<head>
	<title>공지사항 댓글 수정</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="../../resources/js/manager/notice.js" type="text/javascript" ></script>
	
	<link href="../../resources/css/styles.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="needs-validation" role="form" novalidate id="comment_UpdateForm" action="/ddarawazoom/noticeContent" method="post">
			<input type="hidden" name="c_num" value="${notice_CDTO.c_num}" />
				<c:if test="${sessionScope.id != null}">
			   <input type="hidden" name="writer_id" value="${sessionScope.id}" />
			</c:if>
			<c:if test="${sessionScope.c_id != null}">
			   <input type="hidden" name="writer_id" value="${sessionScope.c_id}" />
			</c:if>
		<p class="text-center" style="font-weight: bold; font-size:20px; margin:10px;">
					* 댓글 수정 *
				</p>
				<div class="col-lg-4"> 
					작 성 자 : ${notice_CDTO.writer_id}
				</div>
				<div class="col-lg-4"> 
					패스워드 : 
					<input type="text" name="pw" class="form-control input-sm" value="${notice_CDTO.pw}"/>
				</div>
				<div class="col-lg-4"> 
					작성 내용 :
					<input type="text" name="content" class="form-control input-sm" value="${notice_CDTO.content}"/>
				</div>
				<hr class="my-4">
				<div class="text-center">
					<input type="button" onclick="commentUpdatePro();"class="btn btn-outline-black" value="댓글 수정"/> 
				</div>
		</form>
	</div>
</body>
	<script src="../../resources/js/form-validation.js"></script>
</html>