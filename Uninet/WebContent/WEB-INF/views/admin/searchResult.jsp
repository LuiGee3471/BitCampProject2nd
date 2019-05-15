<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="list" value="${requestScope.staffList}" />
<c:set var="method" value="${requestScope.method}" />
<c:set var="option" value="${requestScope.option}" />
<c:set var="word" value="${requestScope.word}" />
<c:set var="currentPage" value="${requestScope.currentPage}" />
<c:set var="totalPages" value="${requestScope.totalPages}" />
<%
	int totalPages = (int) request.getAttribute("totalPages");
	int currentPage = (int) request.getAttribute("currentPage");
	int startPage = 1;
	if (currentPage >= 4) {
		if (currentPage % 3 == 1) {
			startPage = currentPage;
		} else if (currentPage % 3 == 2) {
			startPage = currentPage - 1;
		} else {
			startPage = currentPage - 2;
		}
	}

	int endPage = 0;
	if (totalPages <= 3) {
		endPage = totalPages;
	} else {
		if (totalPages - startPage >= 3) {
			endPage = startPage + 2;
		} else {
			endPage = totalPages;
		}
	}

	System.out.println(startPage + " / " + endPage);
	pageContext.setAttribute("startPage", startPage);
	pageContext.setAttribute("endPage", endPage);
%>
<tr>
	<th>이름</th>
	<th>아이디</th>
	<th>email</th>
	<th>phone</th>
	<th>생일</th>
	<th>부서</th>
	<th colspan="2"></th>
</tr>
<c:forEach var="staff" items="${list }">
	<tr>
		<td>${staff.staffName}</td>
		<td>${staff.staffId}</td>
		<td>${staff.email}</td>
		<td>${staff.phoneNumber}</td>
		<td><fmt:formatDate value="${staff.birthday}"
				pattern="yyyy-MM-dd" /></td>
		<td>${staff.deptName}</td>
		<td><a href="modify?id=${staff.id}"> <i class="far fa-edit hover-big"></i>
		</a></td>
		 <td class="deleteBtn">
      <i class="far fa-trash-alt hover-big" id="deleteBtn" ></i>
      <input type="hidden" value ="${staff.id}" id="deleteId" >
    </td>
	</tr>
</c:forEach>
<tr>
	<td>
  <span class="hidden" id="method">${method}</span> 
  <span class="hidden" id="option">${option}</span>
	<span class="hidden" id="word">${word}</span> 
  <span class="hidden" id="currentPage">${currentPage}</span> 
  <span class="hidden" id="totalPages">${totalPages}</span> 
  <span class="hidden" id="startPage">${startPage}</span> 
  <span class="hidden" id="endPage">${endPage}</span></td>
</tr>
