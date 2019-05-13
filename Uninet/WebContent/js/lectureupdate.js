$(function() {
  const credit1 = ['1', '2', '3', '4', '5', '6', '7', '8', '9'];
  const credit2 = ['12', '34', '56', '78', '910'];
  const credit3 = ['123', '456', '789'];
  let changeItem;
  let selectCredit = '';

  const originSelect = $('#selectCredit option:selected').val();
  console.log(originSelect);

  if (originSelect === 1) {
    changeItem = credit1;
  } else if (originSelect === 2) {
    changeItem = credit2;
  } else if (originSelect === 3) {
    changeItem = credit3;
  }
  for (let count = 0; count < changeItem.length; count++) {
    const option = $(
      `<option value="${changeItem[count]}">${changeItem[count]}</option>`
    );
    $('#changeTime').append(option);
    /* $("#changeTime  option").attr("value", changeItem[count]); */
  }

  $('#selectCredit').change(function() {
    selectCredit = $(this).val();
    console.log(selectCredit);
    if (selectCredit === 1) {
      changeItem = credit1;
    } else if (selectCredit === 2) {
      changeItem = credit2;
    } else if (selectCredit === 3) {
      changeItem = credit3;
    }

    $('#changeTime').empty();

    for (let count = 0; count < changeItem.length; count++) {
      const option = $(
        `<option value="${changeItem[count]}">${changeItem[count]}</option>`
      );
      $('#changeTime').append(option);
      /* $("#changeTime  option").attr("value", changeItem[count]); */
    }
  });
});
