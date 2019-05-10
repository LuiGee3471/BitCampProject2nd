<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="list" value = "${requestScope.staffList}" />
<c:forEach var="staff" items="${staffList }">
<tr>
				<td>${staffList.staffName }</td>
				<td>${staffList.staffId }</td>
				<td>${staffList.email }</td>
				<td>${staffList.phoneNumber }</td>
				<td>${staffList.birthday }</td>
				<td>${staffList.deptName }</td>
				<td><a href="modify?id=${staffList.id }">수정</a></td>
			</tr>
</c:forEach>
