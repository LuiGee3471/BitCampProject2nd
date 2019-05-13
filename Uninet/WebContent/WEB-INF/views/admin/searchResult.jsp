<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="list" value="${requestScope.staffList}" />

<c:forEach var="staff" items="${list }">

  <tr>
    <td>${staff.staffName}</td>
    <td>${staff.staffId}</td>
    <td>${staff.email}</td>
    <td>${staff.phoneNumber}</td>
    <td><fmt:formatDate value="${staff.birthday}" pattern="yyyy-MM-dd"/></td>
    <td>${staff.deptName}</td>
    <td>
      <a href="modify?id=${staff.id}"> 
        <i class="fas fa-folder-plus"></i>
      </a>
    </td>
    <td>
      <a href="delete?id=${staff.id}"> 
        <i class="far fa-trash-alt"></i>
      </a>
    </td>
  </tr>
</c:forEach>
