<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 회원가입을 눌렀을 경우 db에 값이 저장된다 --%>
<c:if test="${result == 1 }">
	<script>
		window.location=""
		
	</script>
</c:if>


<c:if test="${result != 1 }">
	<script>
		alert('해당하는 아이디와 비밀번호가 존재하지 않습니다.')
		window.location="/ddarawazoom/login"
	</script>
</c:if>



   