function callData(page, method, option, word) {
  return $.ajax({
    url: "search",
    type: "post",
    dataType: "html",
    data: {
      page: page,
      method: method,
      option: option,
      word: word
    },
    success: function(data) {
      $("#searchResult").html(data);
    }
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
    $(".board-bottom").prepend($("<div class='btn prev-page'>&lt;&nbsp;이전</div>"));
  } else {
    $(".board-bottom").prepend($("<div class='btn prev-page hidden'>&lt;&nbsp;이전</div>"));
  }

  if (startPage !== 1) {
    $(".page-btns").append($("<div class='btn prev-page-bundle'>&lt;</div>"));
  }

  for (let i = startPage; i <= endPage; i++) {
	if (i === currentPage) {
      $(".page-btns").append($("<div class='btn page-btn current-btn'>" + i + "</div>"));
	  } else {
	    $(".page-btns").append($("<div class='btn page-btn'>" + i + "</div>"));
	  }
  }

  if (totalPages > endPage) {
    $(".page-btns").append($("<div class='btn next-page-bundle'>&gt;</div>"));
  }

  if (totalPages > currentPage) {
    $(".board-bottom").append($("<div class='btn next-page'>다음&nbsp;&gt;</div>"));
  } else {
    $(".board-bottom").append($("<div class='btn next-page hidden'>다음&nbsp;&gt;</div>"));
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
      callData(currentPage - 3, method, option, word).done(makePageBtns());
    } else if (currentPage % 3 === 2) {
      callData(currentPage - 2, method, option, word).done(makePageBtns());
    } else {
      callData(currentPage - 1, method, option, word).done(makePageBtns());
    }
  });
  
  $(".next-page-bundle").click(function() {
	    if (currentPage % 3 === 0) {
	      callData(currentPage + 1, method, option, word).done(makePageBtns());
	    } else if (currentPage % 3 === 2) {
	      callData(currentPage + 2, method, option, word).done(makePageBtns());
	    } else {
	      callData(currentPage + 3, method, option, word).done(makePageBtns());
	    }
	  });
}

$(function() {  
  callData(1, "default");

  const input = $("#searchInput");
  
  input.keypress(event, function(event) {
    if(event.keyCode === 13) {
      event.preventDefault();
      $("#closeBtn").click();
    }
  });
  
  $("#closeBtn").click(function() {
    const textInput = $("#searchInput").val();
    const option = $("input[name='searchradio']:checked").val();
    callData(1, "search", option, textInput);
  });

  $("#closeBtn2").click(function() {
    const optionValue = $("input[name='sort']:checked").val();
    callData(1, "sort", optionValue);
  });
   
  $(document).on("click","#deleteBtn",function() {
    
    var result = confirm("강의를 정말 삭제하시겠습니까?");
    
    if(result){
      location.replace("delete?id="+$(this).siblings("#deleteId").val());
    }else{
      
    }
   
  });
});

const modalBtns = [...document.querySelectorAll(".button")];
modalBtns.forEach(function(btn) {
  btn.onclick = function() {
    const modal = btn.getAttribute("data-modal");
    document.getElementById(modal).style.display = "block";
  };
});

const closeBtns = [...document.querySelectorAll(".close")];
const closeBtns2 = [...document.querySelectorAll("#closeBtn")];
const closeBtns3 = [...document.querySelectorAll("#closeBtn2")];
closeBtns.forEach(function(btn) {
  btn.onclick = function() {
    const modal = btn.closest(".modal");
    modal.style.display = "none";
  };
});

closeBtns2.forEach(function(btn) {
  btn.onclick = function() {
    const modal = btn.closest(".modal");
    modal.style.display = "none";
  };
});

closeBtns3.forEach(function(btn) {
  btn.onclick = function() {
    const modal = btn.closest(".modal");
    modal.style.display = "none";
  };
});

window.onclick = function(event) {
  if (event.target.className === "modal") {
    event.target.style.display = "none";
  }
};

