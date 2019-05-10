<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#btn').on("click", function() {
		const inputVal = $('#searchInput').val();
		const searchVal = $("input[name='orderBy']").val();
			if (!$('#input').val()) {
				alert('값을 입력해주세요');
			} else {
				$.ajax({
					url : "inputText",
					dataType : "html",
					data : {
						searchText : inputVal,
						orderBy : searchVal
					},
					success : function(data) {
						console.log(data);
						$('#searchResult').html(data);
					}
				})
			}
		});
	});
</script>
</head>
<body>
	<h3>내정보</h3>
	<ul>
		<li><h4>내정보</h4></li>
		<li><a href="<%=request.getContextPath()%>/mypage">기본 정보</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setinfo">개인
				정보 수정</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpassword">비밀번호
				변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/setpicture">프로필
				이미지 변경</a></li>
		<li><a href="<%=request.getContextPath()%>/mypage/admin">회원관리</a></li>
	</ul>
	<table>
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>email</th>
			<th>phone</th>
			<th>생일</th>
			<th>부서</th>
		</tr>
		<tbody id="searchResult">
		<c:forEach var="staffList" items="${requestScope.staffList }">
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
		</tbody>
	</table>
	<select id="search" name="orderBy">
		<option value="name">이름별 검색</option>
		<option value="dept">부서별 검색</option>
	</select>
	<input type="text" id="input" name="searchInput">
	<input type="button" id="btn" value="검색">
</body>
</html>