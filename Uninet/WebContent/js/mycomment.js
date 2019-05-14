$(".page-btn")
    .click(function () {
      const pageNo = Number($(this).text());
      const url = "mycomment?page=" + pageNo + "&option=" + option + "&boardtype=" + boardType + "&word=" + word;
      location.href = url;
    });

$(".next-page").click(function () {
  if (!$(this).hasClass("invisible")) {
    const url = "mycomment?page=" + (currentPage + 1) + "&option=" + option + "&boardtype=" + boardType + "&word=" + word;
    location.href = url;
  }
});

$(".prev-page").click(function () {
  if (!$(this).hasClass("invisible")) {
    const url = "mycomment?page=" + (currentPage - 1) + "&option=" + option + "&boardtype=" + boardType + "&word=" + word;
    location.href = url;
  }
});

$(".prv-btn").click(function () {
  const decision = currentPage % 3;
  let pageToMove;
  if (decision === 1) {
    pageToMove = currentPage - 1;
  } else if (decision === 2) {
    pageToMove = currentPage - 2;
  } else {
    pageToMove = currentPage - 3;
  }

  const url = "mycomment?page=" + pageToMove + "&option=" + option + "&boardtype=" + boardType + "&word=" + word;
  location.href = url;
});

$(".next-btn").click(function () {
  const decision = currentPage % 3;
  let pageToMove;
  if (decision === 1) {
    pageToMove = currentPage + 3;
  } else if (decision === 2) {
    pageToMove = currentPage + 2;
  } else {
    pageToMove = currentPage + 1;
  }

  const url = "mycomment?page=" + pageToMove + "&option=" + option + "&boardtype=" + boardType + "&word=" + word;
  location.href = url;
});