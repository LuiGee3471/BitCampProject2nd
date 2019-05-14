<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${requestScope.list}" />
<c:set var="method" value="${requestScope.method}"/>
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
<c:forEach var="lecture" items="${list}">
  <tr>
    <td>${lecture.lectureName}</td>
    <td>${lecture.credit}</td>
    <td>${lecture.time}</td>
    <td>${lecture.lectureType}</td>
    <td>${lecture.majorName}</td>
    <td>${lecture.profName }</td>
    <td><a href="updatePage?id=${lecture.id}"><i
        class="far fa-edit hover-big"></i></a></td>
    <td><a href="delete?id=${lecture.id}"><i
        class="far fa-trash-alt hover-big"></i></a></td>
</tr>
</c:forEach>
<tr>
<td>
<span class = "hidden" id="method">${method}</span>
<span class = "hidden" id="option">${option}</span>
<span class = "hidden" id="word">${word}</span>
<span class = "hidden" id="currentPage">${currentPage}</span>
<span class = "hidden" id="totalPages">${totalPages}</span>
<span class = "hidden" id="startPage">${startPage}</span>
<span class = "hidden" id="endPage">${endPage}</span>
</td>
</tr>
