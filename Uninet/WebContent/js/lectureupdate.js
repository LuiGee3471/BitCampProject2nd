$(function() {
  const credit1 = ['1', '2', '3', '4', '5', '6', '7', '8', '9'];
  const credit2 = ['12', '23', '34', '45', '56', '67', '78', '89',];
  const credit3 = ['123', '345', '567', '789'];
  let changeItem;
  let selectCredit = '';

  $("#timeFirst input").each(function(index, element) {
    if ($(element).attr("value") === day) {
      $(element).attr("checked", true);
    }
  });

  const originSelect = Number($('#selectCredit option:selected').val());

  if (originSelect === 1) {
    changeItem = credit1;
  } else if (originSelect === 2) {
    changeItem = credit2;
  } else if (originSelect === 3) {
    changeItem = credit3;
  }
  
  for (let count = 0; count < changeItem.length; count++) {
    let tag = `<option value="${changeItem[count]}">${changeItem[count]}</option>`;
    if (changeItem[count] === time) {
        tag = `<option value="${changeItem[count]}" selected>${changeItem[count]}</option>`;
    }
    const option = $(tag);
    $('#changeTime').append(option);
    /* $("#changeTime  option").attr("value", changeItem[count]); */
  }

  $('#selectCredit').change(function() {
    selectCredit = Number($(this).val());

    if (selectCredit === 1) {
      changeItem = credit1;
    } else if (selectCredit === 2) {
      changeItem = credit2;
    } else if (selectCredit === 3) {
      changeItem = credit3;
    }

    $('#changeTime').empty();

    for (let count = 0; count < changeItem.length; count++) {
      let tag = `<option value="${changeItem[count]}">${changeItem[count]}</option>`;
      if (changeItem[count] === time) {
          tag = `<option value="${changeItem[count]}" selected>${changeItem[count]}</option>`;
      }
      const option = $(tag);
      $('#changeTime').append(option);
      /* $("#changeTime  option").attr("value", changeItem[count]); */
    }
  });
  
  $('#updateBtn').click(function(event){
    if(!$('#lectureName').val()){
      alert('과목명을 입력해주세요');
      event.preventDefault();
    }else if(!$('#selectCredit').val()){
      alert('학점을 입력해주세요');
      event.preventDefault();
    }else if(!$('input[name=weekday]:checked').val()){
      alert('요일을 선택해주세요');
      event.preventDefault();
    }else if(!$('#lectureTypeId').val()){
      alert('종별을 선택해주세요');
      event.preventDefault();
    }else if(!$('#professorId').val()){
      alert("교수를 선택해 주세요");
      event.preventDefault();
      
    }
  });
  
});
