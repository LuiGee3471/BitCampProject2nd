$(function() {
  const credit1 = [ "1", "2", "3", "4", "5", "6", "7", "8", "9" ];
  const credit2 = [ "12", "34", "56", "78", "910" ];
  const credit3 = [ "123", "456", "789" ];
  let changeItem;
  let selectCredit = "";

  $("#selectCredit").change(function() {
    selectCredit = $(this).val();

    if (selectCredit == 1) {
      changeItem = credit1;
    } else if (selectCredit == 2) {
      changeItem = credit2;
    } else if (selectCredit == 3) {
      changeItem = credit3;
    }

    $("#changeTime").empty();

    for (let count = 0; count < changeItem.length; count++) {
      const option = $("<option>" + changeItem[count] + "</option>");
      $("#changeTime").append(option);
    }
  });
  $("#addBtn").click(function(event) {
    if (!$("#lectureName").val()) {
      alert("과목명을 입력해주세요");
      event.preventDefault();
    } else if (!$("#selectCredit").val()) {
      alert("학점을 입력해주세요");
      event.preventDefault();
    } else if (!$("input[name=weekday]:checked").val()) {
      alert("요일을 선택해주세요");
      event.preventDefault();
    } else if (!$("#lectureTypeId").val()) {
      alert("종별을 선택해주세요");
      event.preventDefault();
    } else if (!$("#professorId").val()) {
      alert("교수를 선택해 주세요");
      event.preventDefault();
    }
  });
});
