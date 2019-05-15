<%@page import="kr.co.groot.dao.LectureDao"%>
<%@page import="kr.co.groot.dto.Lecture"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/common/head.jsp" />
<link rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.css" />
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/css/modal.css">
<link rel="stylesheet"
  href="<%=request.getContextPath()%>/css/chart.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="chartMove">
      <canvas id="container" width="400" height="100"></canvas>
      <div class="chart-select">
        <div class="chart-select-center">
          <select id="chart" name="chart" class="selsize">
            <option value="lecture">학과별 강의 수</option>
            <option value="professor">학과별 교수 인원</option>
            <option value="day">요일별 수업 개수</option>
          </select> <a class="chart-link" 
          href="<%=request.getContextPath()%>/main">&lt;돌아가기</a>
        </div>
      </div>
    </div>
  </div>
  <script>
      let myChart;
      $(function() {
      <%LectureDao dao = new LectureDao();%>
      const keylist = [];
      const valuelist = [];

      const json = <%=dao.countByLecture()%>;
      for (let key in json) {
        keylist.push(key);
        valuelist.push(json[key]);
      }

      const ctx = $('#container');

      const data = {
        datasets : [
          {
            backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                    'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                    'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                    'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                    'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                    'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                    'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                    'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                    'rgba(255 ,250 ,101,0.2)' ],
            borderColor : [ 'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                    'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                    'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                    'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                    'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                    'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                    'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                    'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                    'rgba(255 ,250 ,101, 1)' ],
            borderWidth : 1,
            data : valuelist,
          }, ],

          // These labels appear in the legend and in the tooltips when hovering different arcs
          labels : keylist,
        };

        if (myChart) {
          myChart.destroy();
        }

        myChart = new Chart(ctx, {
          type : 'bar',
          data : data,
          options : {
            animation : {
              onComplete : function() {
                const chartInstance = this.chart;
                const ctx = chartInstance.ctx;
                const height = chartInstance.controller.boxes[0].bottom;
                ctx.textAlign = "center";
                Chart.helpers.each(this.data.datasets.forEach(function(dataset,
                    i) {
                  const meta = chartInstance.controller.getDatasetMeta(i);
                  Chart.helpers.each(meta.data.forEach(function(bar, index) {
                    ctx.fillText(dataset.data[index], bar._model.x, height
                        - ((height - bar._model.y) / 2));
                  }), this)
                }), this);
              }
            },
            legend : {
              display : false
            },

            tooltips : {
              callbacks : {
                label : function(tooltipItem) {
                  return tooltipItem.yLabel;
                }
              }
            },

            scales : {
              yAxes : [ {
                ticks : {
                  beginAtZero : true
                }
              } ],
              xAxes : [ {
                barPercentage : 0.5,
                barThickness : 50,
                maxBarThickness : 500,
                minBarLength : 5,
                gridLines : {
                  offsetGridLines : true
                }
              } ]
            },

            title : {
              display : true,
              text : '학과별 강의 수',
              fontColor : '#4c4c4c',
              fontSize : 20
            }
          },
        });
      });

      $("#chart").change(
          function() {
            const selected = $("#chart").val();
            const keylist = [];
            const valuelist = [];
            const titleText = $(this).find(
                "option[value='" + $(this).val() + "']").text();

            if (selected == "lecture") {
              const json = <%=dao.countByLecture()%>;
              for ( let key in json) {
                keylist.push(key);
                valuelist.push(json[key]);
              }
            } else if (selected == "day") {
              const json = <%=dao.countByTime()%>;
              for ( let key in json) {
                keylist.push(key);
                valuelist.push(json[key]);
              }
            } else if (selected == "professor") {
              const json = <%=dao.countByProfessor()%>;
              for ( let key in json) {
                keylist.push(key);
                valuelist.push(json[key]);
              }
            }

            const ctx = $('#container');
            const data = {
              datasets : [
                  {
                    backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                        'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                        'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                        'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                        'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                        'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                        'rgba(255 ,250 ,101,0.2)', 'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)', 'rgba(126,255, 245, 0.2)',
                        'rgba(75 ,75 ,75, 0.2)', 'rgba(50 ,255 ,126, 0.2)',
                        'rgba(255 ,250 ,101,0.2)' ],
                    borderColor : [ 'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                        'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                        'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                        'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                        'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                        'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                        'rgba(255 ,250 ,101, 1)', 'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)', 'rgba(126, 255, 245, 1)',
                        'rgba(75 ,75 ,75, 1)', 'rgba(50, 255, 126, 1)',
                        'rgba(255 ,250 ,101, 1)' ],
                    borderWidth : 1,
                    data : valuelist,
                  }, ],

              // These labels appear in the legend and in the tooltips when hovering different arcs
              labels : keylist,
            };

            if (myChart) {
              myChart.destroy();
            }

            myChart = new Chart(ctx, {
              type : 'bar',
              data : data,
              options : {
                animation : {
                  onComplete : function() {
                    const chartInstance = this.chart;
                    const ctx = chartInstance.ctx;
                    const height = chartInstance.controller.boxes[0].bottom;
                    ctx.textAlign = "center";
                    Chart.helpers.each(this.data.datasets.forEach(function(
                        dataset, i) {
                      const meta = chartInstance.controller.getDatasetMeta(i);
                      Chart.helpers.each(meta.data
                          .forEach(function(bar, index) {
                            ctx.fillText(dataset.data[index], bar._model.x,
                                height - ((height - bar._model.y) / 2));
                          }), this)
                    }), this);
                  }
                },
                legend : {
                  display : false
                },
                tooltips : {
                  callbacks : {
                    label : function(tooltipItem) {
                      return tooltipItem.yLabel;
                    }
                  }
                },
                scales : {
                  yAxes : [ {
                    ticks : {
                      beginAtZero : true
                    }
                  } ],
                  xAxes : [ {
                    barPercentage : 0.5,
                    barThickness : 50,
                    maxBarThickness : 500,
                    minBarLength : 5,
                    gridLines : {
                      offsetGridLines : true
                    }
                  } ]
                },
                title : {
                  display : true,
                  text : titleText,
                  fontColor : '#4c4c4c',
                  fontSize : 20
                }
              },
            });
          });
    </script>
  <jsp:include page="/common/bottom.jsp" flush="false" />
</body>
</html>