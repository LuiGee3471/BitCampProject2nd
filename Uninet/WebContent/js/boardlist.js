$("#textbox").click(function() {
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

$(".page-btn").click(function() {
  const pageNo = Number($(this).text());
  const url =
    "list?page=" +
    pageNo +
    "&option=" +
    option +
    "&boardtype=" +
    boardType +
    "&word=" +
    word;
  location.href = url;
});

$(".next-page").click(function() {
  if (!$(this).hasClass("invisible")) {
    const url =
      "list?page=" +
      (currentPage + 1) +
      "&option=" +
      option +
      "&boardtype=" +
      boardType +
      "&word=" +
      word;
    location.href = url;
  }
});

$(".prev-page").click(function() {
  if (!$(this).hasClass("invisible")) {
    const url =
      "list?page=" +
      (currentPage - 1) +
      "&option=" +
      option +
      "&boardtype=" +
      boardType +
      "&word=" +
      word;
    location.href = url;
  }
});

$(".prv-btn").click(function() {
  const decision = currentPage % 3;
  let pageToMove;
  if (decision === 1) {
    pageToMove = currentPage - 1;
  } else if (decision === 2) {
    pageToMove = currentPage - 2;
  } else {
    pageToMove = currentPage - 3;
  }

  const url =
    "list?page=" +
    pageToMove +
    "&option=" +
    option +
    "&boardtype=" +
    boardType +
    "&word=" +
    word;
  location.href = url;
});

$(".next-btn").click(function() {
  const decision = currentPage % 3;
  let pageToMove;
  if (decision === 1) {
    pageToMove = currentPage + 3;
  } else if (decision === 2) {
    pageToMove = currentPage + 2;
  } else {
    pageToMove = currentPage + 1;
  }

  const url =
    "list?page=" +
    pageToMove +
    "&option=" +
    option +
    "&boardtype=" +
    boardType +
    "&word=" +
    word;
  location.href = url;
});

$(".new-btn").click(function() {
  if (!$(".new-title").val()) {
    alert("제목을 입력해주세요!");
    event.preventDefault();
  } else if (!$(".new-content").val()) {
    alert("내용을 입력해주세요!");
    event.preventDefault();
  }
});
