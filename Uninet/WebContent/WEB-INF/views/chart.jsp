<%@page import="kr.co.groot.dao.LectureDao"%>
<%@page import="kr.co.groot.dto.Lecture"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>

</head>
<body>
    <canvas id="container" width="400" height="100"></canvas>
      <select id="chart" name="chart" class="selsize">
        <option value="lecture">학과</option>
        <option value="day">요일</option>
        <option value="professor">교수</option>
      </select> <a class="" href="list">&lt; 돌아가기</a>

<script>

var myChart;
$(function() {
  <% 
    LectureDao dao = new LectureDao(); 
  %>
  var keylist = [];
  var valuelist = [];
  
  var json = <%=dao.countByLecture()%>;
  for(var key in json) {
    keylist.push(key);
    valuelist.push(json[key]);
  }
  
  var ctx = $('#container');

  var data = {
        datasets: [
          {
            backgroundColor: [
              'tomato',
              'yellow',
              'skyblue',
              'red',
              'blue',
              'purple',
              'beige',
              'pink',
              'orange',
            ],
            data: valuelist,
          },
        ],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: keylist,
      };

      myChart = new Chart(ctx, {
        type: 'horizontalBar',
        data: data,
        options: {
          title: {
            display: true,
            text: "부서 별 사원 수"
          }
        },
      });

});
</script>
</body>
</html>