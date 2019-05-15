<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="staff" value="${requestScope.staff }" />
<c:set var="departList" value="${requestScope.departList }" />
<jsp:include page="/common/head.jsp" flush="false" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/update.css">
<body>
	<jsp:include page="/common/top.jsp" flush="false" />
	<div class="container">
		<form action="modifyOk" method="post">
		<input type="hidden" name="selectId" value="${staff.id }">
			<span class="label">아이디 </span><input type="text" id="id" name="id"
				value="${staff.staffId}" class="text" readOnly ><br> <span class="label">이름
			</span><input type="text" id="name" name="name" value="${staff.staffName}" class="text"
				readOnly><br> <span class="label">이메일</span> <input type="email"
				id="email" name="email" value="${staff.email}" class="text" readOnly><br>
			<span class="label">핸드폰 번호</span> <input type="text" id="phoneNumber"
				name="phoneNumber" value="${staff.phoneNumber}"><br>
        
       <div class = "totalAdmin">
				<div class="citeAdmin">			
			<span class="label">사이트 관리자 </span> <br>
			<input type="checkbox" name="isAdmin" value="Y" id="isAdmin">
       <label for="isAdmin"><i class="far fa-check-square fa-2x"></i></label>	
			</div>
			<br>
			<div class="deptAdmin">			
			<span class="label">부서 관리자 </span> <br>
			<input type="checkbox" value="Y" id="isManager">
      <label for="isManager"><i class="far fa-check-square fa-2x"></i></label>		
			</div>
      </div>
			<br>
			<span class="label">부서이름</span> 
			 <select name = "deptname" id="deptname" class="deptname">
			 <option value="">부서 선택</option>
			 <c:forEach var="departList" items="${departList }">
			 <c:choose>
			 <c:when test="${staff.deptName ==departList.deptName}">
			 	<option value="${departList.id}" selected>${departList.deptName }</option>
			 </c:when>
			 <c:otherwise>
			 <option value="${departList.id}">${departList.deptName }</option>
			 </c:otherwise>
			 </c:choose>
			 </c:forEach>
			 </select>
			<input type="submit"
				value="수정" class="updateBtn" id="updateBtn"> 
				<a class="cancle-back" href="admin?page=1&option=default">취소하고 돌아가기</a> 	
		</form>
	</div>
	<jsp:include page="/common/bottom.jsp" flush="false" />
	 <script src="<%=request.getContextPath()%>/js/adminModify.js"></script>
</body>
</html>