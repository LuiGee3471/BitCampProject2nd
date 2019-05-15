$(document).ready(function() {
  $("#summernote").summernote({
    height: 200,
    minHeight: null,
    maxHeight: null,
    focus: true,
    lang: "ko-KR",
  });
  $("#currentPwd").keyup(function() {
    if ($("#currentPwd").val() != $("#pwd").val()) {
      $("#check").val("암호가 불일치 합니다.");
      $("#check").css("color", "red");
    } else {
      $("#check").val("암호가 일치합니다.");
      $("#check").css("color", "blue");
    }
  });
  $("#update").click(function(event) {
    const phoneRegExp = /^010\d{4}\d{4}$/;
    if ($("#currentPwd").val() == "") {
      alert("암호를 입력해주세요");
      event.preventDefault();
      return false;
    } else if ($("#currentPwd").val() != $("#pwd").val()) {
      alert("암호가 틀렸습니다.");
      event.preventDefault();
      return false;
    } else if (
      !$("#staffPhone")
        .val()
        .match(phoneRegExp)
    ) {
      alert("올바른 전화번호가 아닙니다.");
      event.preventDefault();
    }
  });
});
