$("#textbox").click(function () {
  $(this).addClass("unseen");
  $("#postform").removeClass("unseen");
});

$(".new-content").keyup(function() {
  const text = $(".new-content").val();
  if (text.length >= 500) {
    $(".warning").removeClass("unseen");
  } else {
    $(".warning").addClass("unseen");
  }
});

$(".page-btn")
    .click(function () {
      const pageNo = Number($(this).text());
      const url = "list?page=" + pageNo + "&option=<%=request.getAttribute('option')%>&boardtype=<%=request.getAttribute('boardType')%>&word=<%=request.getAttribute('word')%>";
      location.href = url;
    });

$(".next-page").click(function () {
  if (!$(this).hasClass("invisible")) {
    const url = "list?page=${currentPage + 1}&option=<%=request.getAttribute('option')%>&boardtype=<%=request.getAttribute('boardType')%>&word=<%=request.getAttribute('word')%>";
    location.href = url;
  }
});

$(".prv-btn").click(function () {
  const currentPage = ${currentPage};
  const decision = currentPage % 3;
  const pageToMove=null;
  if (decision === 1) {
    pageToMove = currentPage - 1;
  } else if (decision === 2) {
    pageToMove = currentPage - 2;
  } else {
    pageToMove = currentPage - 3;
  }

  const url = "list?page=" + pageToMove + "&option=<%=request.getAttribute('option')%>&boardtype=<%=request.getAttribute('boardType')%>&word=<%=request.getAttribute('word')%>";
  location.href = url;
});

$(".next-btn").click(function () {
  const currentPage = ${currentPage};
  const decision = currentPage % 3;
  const pageToMove=null;
  if (decision === 1) {
    pageToMove = currentPage + 3;
  } else if (decision === 2) {
    pageToMove = currentPage + 2;
  } else {
    pageToMove = currentPage + 1;
  }

  const url = "list?page=" + pageToMove + "&option=<%=request.getAttribute('option')%>&boardtype=<%=request.getAttribute('boardType')%>&word=<%=request.getAttribute('word')%>";
  location.href = url;
});