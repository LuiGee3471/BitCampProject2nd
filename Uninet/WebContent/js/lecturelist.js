$(function() {
  $.ajax({
    url: "search",
    method: "post",
    dataType: "html",
    data: {
      page: 1,
      method: "default"   
    },
    success: function(data) {
      $("#searchResult").html(data);
    }
  }).done(function() {
    $(".page-btn").click(function() {
      const pageNo = $(this).text();
      console.log(pageNo)
      $(".page-btns").children(".current-btn").removeClass("current-btn");
      $(this).addClass("current-btn");
      $.ajax({
        url: "search",
        method: "post",
        dataType: "html",
        data: {
          page: pageNo,
          method: "default"
        },
        success: function(data) {
          $("#searchResult").html(data);
        }
      });
    })
  })
  
  
})

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