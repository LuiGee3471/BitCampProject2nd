<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
<c:forEach var="lecture" items="${list}">
  <tr>
    <td>${lecture.lectureName}</td>
    <td>${lecture.credit}</td>
    <td>${lecture.time}</td>
    <td>${lecture.lectureType}</td>
    <td>${lecture.majorName}</td>
    <td>${lecture.profName }</td>
    <td><a href="updatePage?id=${lecture.id}"><i class="far fa-edit hover-big"></i></a></td>
    <td><a href="delete?id=${lecture.id}"><i class="far fa-trash-alt hover-big"></i></a></td>
  </tr>
</c:forEach>