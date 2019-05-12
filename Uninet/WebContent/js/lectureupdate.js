$(function() {
  var credit1 = [ "1", "2", "3", "4", "5", "6", "7", "8", "9" ];
  var credit2 = [ "12", "34", "56", "78", "910" ];
  var credit3 = [ "123", "456", "789" ];
  var changeItem;
  var selectCredit = "";

  var originSelect = $("#selectCredit option:selected").val();
  console.log(originSelect);

  if (originSelect == 1) {
    changeItem = credit1;
  } else if (originSelect == 2) {
    changeItem = credit2;
  } else if (originSelect == 3) {
    changeItem = credit3;
  }
  for (var count = 0; count < changeItem.length; count++) {

    var option = $("<option>" + changeItem[count] + "</option>");
    $("#changeTime").append(option);

  }

  $('#selectCredit').change(function() {
    selectCredit = $(this).val();
    console.log(selectCredit);
    if (selectCredit == 1) {
      changeItem = credit1;
    } else if (selectCredit == 2) {
      changeItem = credit2;
    } else if (selectCredit == 3) {
      changeItem = credit3;
    }

    $("#changeTime").empty();

    console.log("changeTime : " + changeTime);
    console.log("changesize : " + changeItem.length);
    for (var count = 0; count < changeItem.length; count++) {

      var option = $("<option>" + changeItem[count] + "</option>");
      $("#changeTime").append(option);

    }

  });

});
