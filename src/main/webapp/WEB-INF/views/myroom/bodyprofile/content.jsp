<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../layout/header.jsp"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

		<title>Profile Page</title>		
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
		<script src="../../resources/js/myroom/bodyprofile.js" ></script>
		
		<link href="../../resources/css/myroom/bodyprofile.css" rel="stylesheet" >
		<link href="../../resources/css/myroom/myprofile.css" rel="stylesheet">
		
	
	<c:if test="${myProfileDTO == null}" >
		<ul class="list-group list-group-flush">
		    <li class="list-group-item">
		    	<h5>작성 된 바디 프로필이 없습니다.</h5>
			    <p>
			    	"작성하러가기" 버튼을 클릭한 후, <b>${sessionScope.id}</b>님 의 프로필을 작성해주세요.<br/><br/>
					[프로필 작성이 완료되면, 바디프로필 이용이 가능합니다.]
				</p>
			</li>
			<li class="list-group-item">
				<input type="button" value="작성하러가기" class="btn btn-outline-dark"
					onclick="document.location.href='/myroom/bodyprofile/myWrite'" />
			</li>
		</ul>
	</c:if>
	
	<c:if test="${myProfileDTO != null}" >
		<section class="section about-section gray-bg" id="about">
            <div class="container">
                <div class="row align-items-center flex-row-reverse">
                    <div class="col-lg-6">
                        <div class="about-text go-to">
                            <h3 class="dark-color">${myProfileDTO.b_id}님의 바디 프로필</h3>
                            <p>
                            	DDrawaZoom의 소중한 <mark>${myProfileDTO.b_id}</mark> 멤버 님만의 공간입니다.
                            </p>
                            <div class="row about-list">
                                <div class="col-md-6">
                                    <div class="media">
                                        <label>I D</label>
                                        <p>${myProfileDTO.b_id}</p>
                                    </div>
                                    <div class="media">
                                        <label>Nick</label>
                                        <p>${myProfileDTO.b_nick}</p>
                                    </div>
                                    <div class="media">
                                        <label>Name</label>
                                        <p>${myProfileDTO.b_name}</p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="media">
                                        <label>Birth</label>
                                        <p><fmt:formatDate pattern="yyyy-MM-dd" value="${myProfileDTO.b_birth}" type="date" dateStyle="long"/></p>
                                    </div>
                                    <div class="media">
                                        <label>Phone</label>
                                        <p>${myProfileDTO.b_tel}</p>
                                    </div>
                                    <div class="media">
                                        <label>Email</label>
                                        <p>${myProfileDTO.b_email}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
	                    <div class="col-lg-6">
	                        <div class="about-avatar">
	                            <img src="../../resources/image/upload/${myProfileDTO.img}" width="500px" height="500px" alt="img">
	                        </div>
	                    </div>
	                </div>
	                	<div class="counter">
		                    	<div class="row">
			                    	<c:forEach items="${bodyProfileDTO}" var="bodyProfileDTO">
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="500" data-speed="500">${bodyProfileDTO.b_height} cm</h6>
				                                <p class="m-0px font-w-600">Height(키)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="150" data-speed="150">${bodyProfileDTO.b_weight} kg</h6>
				                                <p class="m-0px font-w-600">Weight(몸무게)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="850" data-speed="850">${bodyProfileDTO.b_muscle} kg</h6>
				                                <p class="m-0px font-w-600">Muscle(근육 량)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_bodyfat}</h6>
				                                <p class="m-0px font-w-600">Body fat(체지방 량)</p>
				                            </div>
				                        </div>
				                         <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_chest}</h6>
				                                <p class="m-0px font-w-600">Chest(가슴 둘레)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_waist}</h6>
				                                <p class="m-0px font-w-600">Waist (허리 둘레)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_arm}</h6>
				                                <p class="m-0px font-w-600">Arm(팔뚝 둘레)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_thigh}</h6>
				                                <p class="m-0px font-w-600">Thigh(허벅지 둘레)</p>
				                            </div>
				                        </div>
				                        <div class="col-6 col-lg-4">
				                            <div class="count-data text-center">
				                                <h6 class="count h2" data-to="190" data-speed="190">${bodyProfileDTO.b_hip}</h6>
				                                <p class="m-0px font-w-600">Hip(엉덩이 둘레)</p>
				                            </div>
				                        </div>
			                      	</c:forEach>
	                    		</div>
	                		</div>
	            		</div>
	        	</section>
	         <div class="list_button">
			    	<input type="button" value="수정하러가기"  class="btn btn-outline-dark"
						onclick="document.location.href='/myroom/bodyprofile/myUpdate'"/>
						&nbsp;&nbsp;
					<input type="button"  value="오늘하루 기록하기"  class="btn btn-outline-dark"
						onclick="document.location.href='/myroom/bodyprofile/bodyWrite'"/>
			</div>
	  </c:if>
   
   
   
	<!-- 바디프로필 리스트 -->
		<table class="table" id="bodyprofileList" style="height: 130px ; vertical-align: middle;">
			<thead>
				<tr class="table-light">
					<th scope="col">
						순 서
					</th>
					<th scope="col">
						바디 사진
					</th>
					<th scope="col">
						측정 날짜
					</th>
					<th scope="col">
						키
					</th>
					<th scope="col">
						몸무게
					</th>
					<th scope="col">
						근육량
					</th>
					<th scope="col">
						체지방량
					</th>
					<th scope="col">
						체 형
					</th>
					<th scope="col">
						가슴 둘레
					</th>
					<th scope="col">
						허리 둘레
					</th>
					<th scope="col">
						팔뚝 둘레
					</th>
					<th scope="col">
						허벅지 둘레
					</th>
					<th scope="col">
						엉덩이 둘레
					</th>
					<th scope="col">
						버 튼
					</th>
				</tr>
			</thead>
			<c:forEach var="bodyProfileDTO" items="${bodyList}" >	
				<tbody>				
					<tr>
						<th scope="row">
							${number}
							<c:set var="number" value="${number + 1}"/>
						</th>
						<td>
							<img src="../../resources/image/upload/${bodyProfileDTO.b_img}" name="b_img" width="150px" height="150px"/>
						</td>
						<td>
							<fmt:formatDate value="${bodyProfileDTO.b_date}" type="date" dateStyle="medium"/>
						</td>
						<td>
							${bodyProfileDTO.b_height} cm
						</td>
						<td>
							${bodyProfileDTO.b_weight} kg
						</td>
						<td>
							${bodyProfileDTO.b_muscle} kg
						</td>
						<td>
							${bodyProfileDTO.b_bodyfat} kg
						</td>
						<td>
							${bodyProfileDTO.b_shape}
						</td>
						<td>
							${bodyProfileDTO.b_chest} cm
						</td>
						<td>
							${bodyProfileDTO.b_waist} cm
						</td>
						<td>
							${bodyProfileDTO.b_arm} cm
						</td>
						<td>
							${bodyProfileDTO.b_thigh} cm
						</td>
						<td>
							${bodyProfileDTO.b_hip} cm
						</td>
						<td style="width: 10px; font-size:10px;">
							<input type="button"  value="수 정"  id="updateButton" class="btn btn-outline-primary"
								onclick="window.location='/myroom/bodyprofile/bodyUpdate?b_num=${bodyProfileDTO.b_num}'"/>
								&nbsp;&nbsp;
							<input type="button" value="삭 제" id="deleteButton" class="btn btn-outline-danger"
								onclick="window.open('/myroom/bodyprofile/bodyDelete?b_num=${bodyProfileDTO.b_num}','delete','width=600,height=200');" />
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		<!-- 바디 프로필 차트 -->
		<div class="weightChart" style="position: relative; height:60vh; width:80vw; margin: 0 auto; margin-top: 100px;">
			<canvas id="weightChart" style="height:30vh; width:50vw; margin: 0 auto;"></canvas>
		</div>
		<br/><br/>
		<div class="muscleBodyChart" style="position: relative; height:60vh; width:80vw; margin: 0 auto; margin-top: 100px;">
			<canvas id="muscleBodyChart" style="height:30vh; width:50vw; margin: 0 auto;"></canvas>
		</div>
		<div class="bodySizeChart" style="position: relative; height:60vh; width:80vw; margin: 0 auto; margin-top: 100px;">
			<canvas id="bodySizeChart" style="height:30vh; width:50vw; margin: 0 auto;"></canvas>
		</div>
		

<%@ include file="../../layout/footer.jsp"%>