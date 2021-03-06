function callData(page, method, option, word) {
  return $.ajax({
    url: "admin/search",
    type: "post",
    dataType: "html",
    data: {
      page: page,
      method: method,
      option: option,
      word: word,
    },
    success: function(data) {
      $("#searchResult").html(data);
    },
  }).done(makePageBtns);
}

function makePageBtns() {
  $(".page-btns").empty();
  $(".prev-page").remove();
  $(".next-page").remove();

  const method = $("#method").text();
  const option = $("#option").text();
  const word = $("#word").text();
  const currentPage = Number($("#currentPage").text());
  const totalPages = Number($("#totalPages").text());
  const startPage = Number($("#startPage").text());
  const endPage = Number($("#endPage").text());

  if (currentPage !== 1) {
    $(".board-bottom").prepend(
      $("<div class='btn prev-page'>&lt;&nbsp;이전</div>")
    );
  } else {
    $(".board-bottom").prepend(
      $("<div class='btn prev-page hidden'>&lt;&nbsp;이전</div>")
    );
  }

  if (startPage !== 1) {
    $(".page-btns").append($("<div class='btn prev-page-bundle'>&lt;</div>"));
  }

  for (let i = startPage; i <= endPage; i++) {
    if (i === currentPage) {
      $(".page-btns").append(
        $("<div class='btn page-btn current-btn'>" + i + "</div>")
      );
    } else {
      $(".page-btns").append($("<div class='btn page-btn'>" + i + "</div>"));
    }
  }

  if (totalPages > endPage) {
    $(".page-btns").append($("<div class='btn next-page-bundle'>&gt;</div>"));
  }

  if (totalPages > currentPage) {
    $(".board-bottom").append(
      $("<div class='btn next-page'>다음&nbsp;&gt;</div>")
    );
  } else {
    $(".board-bottom").append(
      $("<div class='btn next-page hidden'>다음&nbsp;&gt;</div>")
    );
  }

  handlePageBtnClick(currentPage, method, option, word);
}
function handlePageBtnClick(currentPage, method, option, word) {
  $(".page-btn").click(function() {
    const pageNo = $(this).text();
    callData(pageNo, method, option, word);
  });

  $(".prev-page").click(function() {
    if (!$(".prev-page").hasClass("hidden")) {
      callData(currentPage - 1, method, option, word);
    }
  });

  $(".next-page").click(function() {
    if (!$(".next-page").hasClass("hidden")) {
      callData(currentPage + 1, method, option, word);
    }
  });

  $(".prev-page-bundle").click(function() {
    if (currentPage % 3 === 0) {
      callData(currentPage - 3, method, option, word);
    } else if (currentPage % 3 === 2) {
      callData(currentPage - 2, method, option, word);
    } else {
      callData(currentPage - 1, method, option, word);
    }
  });

  $(".next-page-bundle").click(function() {
    if (currentPage % 3 === 0) {
      callData(currentPage + 1, method, option, word);
    } else if (currentPage % 3 === 2) {
      callData(currentPage + 2, method, option, word);
    } else {
      callData(currentPage + 3, method, option, word);
    }
  });
}

$(function() {
  callData(1, "default");

  const input = $("#searchInput");

  input.keypress(event, function(event) {
    if (event.keyCode === 13) {
      event.preventDefault();
      $("#btn").click();
    }
  });

  $("#btn").click(function() {
    const textInput = $("#searchInput").val();
    const option = $("#search option:selected").val();
    callData(1, "search", option, textInput);
  });

  $(document).on("click", "#deleteBtn", function() {
    const result = confirm("회원를 정말 삭제하시겠습니까?");

    if (result) {
      location.replace(
        "admin/delete?id=" +
          $(this)
            .siblings("#deleteId")
            .val()
      );
    } else {
    }
  });
  $("#search").on('change',function(){
    if($("#search option:selected").val()=="deptName") {
       $("#searchInput").remove();
       const str = "<select id='searchInput' name='searchInput' class='search'>" +
       		"<option value'학사지원처'>학사지원처</option>"
         +"<option value='예비군대대'>예비군대대</option>" +
          "<option value='교무지원처'>교무지원처</option>" +
          "<option value='입학지원처'>입학지원처</option>" +
          "<option value='학술정보부'>학술정보부</option>" +
          "<option value='전산업무부'>전산업무부</option>" +
         "</select>" 
       $("#search").after(str);
    }else if($("#search option:selected").val()=="name") {
     $("#searchInput").remove();
      const str = "<input type='text' id='searchInput' name='searchInput'"
           + "class='search' autocomplete='off'>";
      $("#search").after(str);
    }
  });
  
});
