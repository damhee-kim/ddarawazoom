<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ include file="../../layout/header.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<title>likeVod 페이지</title>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src ="../../resources/js/myroom/like.js" type="text/javascript"></script>
		
		<link href="../../resources/css/myroom/like.css" rel="stylesheet" >
	
	</head>
	<body>
	
		<div class="likeVod_title">
			<h3>소중한 관심Vod 공간</h3>
		</div>
	
		<div class="container-fluid">
   		 	<div class="container">
				<div class="row row-cols-1 row-cols-sm-1 row-cols-md-3 g-3">
					<c:forEach var="VodDTO" items="${VodDTO}">
						<div class="col">
							<div class="card shadow-sm">
		         					<div class="card-body"> 
		         						<p class="card-text">No.${number} 
											<c:set var="number" value="${number - 1}"/>
												조회수${VodDTO.count} 
										</p>	
										<p class="card-text">
											<b>영상 번호 : </b> ${VodDTO.num}
										</p>
										<p class="card-text">
											<b>카테고리 : </b> ${VodDTO.category}
										</p>
										<p class="card-text">
											<b>제 목 : </b><br/>
											<a href="#"> ${VodDTO.title}</a>
										</p>
										<p class="card-text">
											<b>설 명 : </b><br/>
											<a href="#"> ${VodDTO.info}</a>
										</p>
	 								<div class="d-flex justify-content-between align-items-center">
		              					<div class="btn-group">
							                <button type="button" class="btn btn-sm btn btn-danger" disabled>${VodDTO.time}</button>&nbsp;&nbsp; 	 	 
							               	<button type="button" class="btn btn-sm btn btn-success" disabled>${VodDTO.difficulty}</button>&nbsp;&nbsp;
							               	<button type="button" class="btn btn-light" onclick="deleteLikeVod(${VodDTO.num});">관심 해제</button>
		              					</div>	     
						            </div>
						         </div>
			        		</div>
		        		</div>
					</c:forEach> 
				</div>		
	 		</div>
		</div>	 
			
	</body>
</html>

<%@ include file="../../layout/footer.jsp"%>