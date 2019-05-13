$(function() {
  $.ajax({
    url : "search",
    method : "post",
    dataType : "html",
    data : {
      page : 1,
      method : "default"
    },
    success : function(data) {
      $("#searchResult").html(data);
    }
  }).done(function() {
    $(".page-btn").click(function() {
      const pageNo = $(this).text();
      console.log(pageNo)
      $(".page-btns").children(".current-btn").removeClass("current-btn");
      $(this).addClass("current-btn");
      $.ajax({
        url : "search",
        method : "post",
        dataType : "html",
        data : {
          page : pageNo,
          method : "default"
        },
        success : function(data) {
          $("#searchResult").html(data);
        }
      });
    })
  })

  $('#btn').on("click", function() {
    const pageNo = $(this).text();
    console.log(pageNo);
    const inputVal = $("#input").val();
    const searchVal = $("#search option:selected").val();
    console.log(inputVal);
    console.log(searchVal);
    if (!$('#input').val()) {
      alert('값을 입력해주세요');
    } else {
      $.ajax({
        url : "inputText",
        method : "post",
        dataType : "html",
        data : {
          page : pageNo,
          option : searchVal,
          word : inputVal
        },
        success : function(data) {
          $("#searchResult").html(data);
        }
      });
    }
  });
})

