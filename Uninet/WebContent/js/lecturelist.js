$(function() {
  /* 검색 비동기 */
  $("#closeBtn").on("click", function() {
    // if ($("#searchInput").val()) {
    /* alert($("#searchInput").val() + " 검색합니다"); */
    const inputVal = $("#searchInput").val();
    const radioVal = $("input[name='searchradio']:checked").val();

    $.ajax({
      url: "inputtext",
      dataType: "html",
      data: {
        searchradio: radioVal,
        searchInput: inputVal
      },
      success: function(data) {
        $("#searchResult").html(data);
        console.log(data);
      },
      error: function(xhr) {
        console.log(xhr.status);
      }
    });
    // }
  });

  /* 검색 비동기 */

  $("#closeBtn2").on("click", function() {
    // if ($("#searchInput").val()) {
    /* alert($("#searchInput").val() + " 검색합니다"); */
    const sortVal = $("input[name='sort']:checked").val();
    console.log(sortVal);

    $.ajax({
      url: "lectureSort",
      dataType: "html",
      data: {
        sort: sortVal
      },
      success: function(data) {
        $("#searchResult").html(data);
        console.log(data);
      },
      error: function(xhr) {
        console.log(xhr.status);
      }
    });
    // }
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