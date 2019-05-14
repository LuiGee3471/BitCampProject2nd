function makePageBtns(){
  const option = $("#option").text();
  const word = $("#word").text();
  const currentPage = Number($("#currentPage").text());
  const totalPages = Number($("#totalPages").text());
  const startPage = Number($("#startPage").text());
  const endPage = Number($("#endPage").text());
  const boardBottom = $(".board-bottom");
  const pageBtns = $(".page-btns");
  
  
  

  if (currentPage !== 1) {
    boardBottom.prepend("<div class='btn prev-page'>&lt;&nbsp;이전</div>");
  } else {
    boardBottom.prepend("<div class='btn prev-page hidden'>&lt;&nbsp;이전</div>");
  }
  if (currentPage > 3) {
    pageBtns.append("<div class='btn prv-btn'>&lt;</div>");
  }
  
  for (let i = startPage; i <= endPage; i++) {
    if (i === currentPage) {
      pageBtns.append("<div class='btn page-btn current-btn'>" + i + "</div>");
    } else {
      pageBtns.append("<div class='btn page-btn'>" + i + "</div>");
    }
  }
  
  if (totalPages > endPage) {
    pageBtns.append("<div class='btn next-btn'>&gt;</div>");
  }
  
  if (currentPage < totalPages) {
    boardBottom.append("<div class='btn next-page'>다음&nbsp;&gt;</div>");
  } else {
    boardBottom.append("<div class='btn next-page hidden'>다음&nbsp;&gt;</div>");
  }
}

function checkPrevBtnAndNextBtn() {
	  const currentPage = Number($("#currentPage").text());
	  const method = $("#method").text();
	  const option = $("#option").text();
	  const word = $("#word").text();
	  const totalPages = Number($("#totalPages").text());
	  $(".next-page").click(function () {
	    if (currentPage !== totalPages && !$(this).hasClass("hidden")) {
	      callResult(currentPage + 1, method, option, word);
	    }
	  });

	  $(".prev-page").click(function () {
	    if (currentPage !== 1 && !$(this).hasClass("hidden")) {
	      callResult(currentPage - 1, method, option, word);
	    }
	  });
	}

function callResult(page, method, option, word){
  console.log("check2");
  console.log("나는 js에서의 option : " + option);
  const ajax = {
      url: "search",
      method: "post",
      data:{
        page: page,
        method: method,
        option: option,
        word: word
      },
      dataType: "html",
      success: function(data) {
        $("#searchResult").html(data);
      }  
  };
  $.ajax(ajax).done(function() {
	  console.log("currentPage : " + currentPage);
	  console.log("나는 done이야 ㅎㅎㅎ");
    $(".page-btn").off("click");
    $(".board-bottom").empty();
    $(".board-bottom").append("<div class='page-btns'></div>");
    makePageBtns();
    checkPrevBtnAndNextBtn();
    $(".page-btn").click(function() {
      const pageNo = $(this).text();

      $(".page-btns").children(".current-btn").removeClass("current-btn");
      $(this).addClass("current-btn");
      $.ajax({
        url: "search",
        method: "post",
        dataType: "html",
        data: {
          page: pageNo,
          method: method,
          option: option,
          word: word
        },
        success: function(data) {
          $("#searchResult").html(data);
        }
      });
    })
  });
}

$(function() {
  callResult(1, "default");
  
  const input = $("#searchInput");
  
  input.keypress(event, function(event) {
	    if(event.keyCode === 13) {
	      event.preventDefault();
	      $("#btn").click();
	    }
	  });
	  
  
  $("#btn").click(function(){
    const textInput = $('#searchInput').val();
    const option = $("#search option:selected").val();
    
    callResult(1, "search", option, textInput);
  })
});